package dgsw.hs.kr.cheolwonbus;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class SearchStop extends AppCompatActivity implements ItemClickListener {

    private EditText editText;
    private RecyclerView busList;
    private BusAdapter adapter;
    private ArrayList<BusBean> data;
    private ArrayList<BusBean> tmp;
    private BusDBHelper dbHelper;

    private final int REQ_FAVORITE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stop);
        dbHelper = new BusDBHelper(this,"busdb", null, 1);

        editText = findViewById(R.id.etBusStop);
        busList = findViewById(R.id.busList);

        data = (ArrayList<BusBean>) getIntent().getSerializableExtra("data");
        HashMap<String, Boolean> result = dbHelper.getAll();

        if(!(result.size() == data.size())){
            //db에 값이 존재하지 않을 때
            for (BusBean bus: this.data) {
                dbHelper.insert(bus.getRouteId());
            }
        }

        for (int i = 0; i < this.data.size(); i++) {
            this.data.get(i).setFavorite(result.get(this.data.get(i).getRouteId()));
        }

        adapter = new BusAdapter(data, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        busList.setLayoutManager(layoutManager);
        busList.setAdapter(adapter);
    }

    public void SearchBusStop(View v){
        String busStop = editText.getText().toString().trim();

        if(busStop == null || busStop.equals("")){
            adapter.changedData(data);
            adapter.notifyDataSetChanged();
            return;
        }

        ArrayList<BusBean> searchData = new ArrayList<>();
        for(BusBean busBean : data){
            if(busBean.getRoute().matches(".*" +busStop + ".*") || busBean.getStartStation().equals(busStop)
                    || busBean.getFirstViaStation().equals(busStop) || busBean.getReturnStation().equals(busStop)
                    || busBean.getSecondViaStation().equals(busStop) || busBean.getEndStation().equals(busStop)
                    || busBean.getRouteId().contains(busStop)){
                searchData.add(busBean);
            }
        }

        adapter.changedData(searchData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, DetailBus.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("bean",data.get(position));

        intent.putExtras(bundle);
        startActivityForResult(intent,REQ_FAVORITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQ_FAVORITE){
            if(resultCode == RESULT_OK){
                String routeId = data.getStringExtra("routeId");
                boolean isFavorite = data.getBooleanExtra("isFavorite", false);
                dbHelper.update(routeId, isFavorite);
                this.data.forEach(bus -> { if(bus.getRouteId().equals(routeId)) bus.setFavorite(isFavorite); });
            }
        }
    }
}
