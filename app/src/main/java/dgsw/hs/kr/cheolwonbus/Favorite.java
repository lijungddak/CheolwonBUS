package dgsw.hs.kr.cheolwonbus;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity implements ItemClickListener {

    private BusDBHelper dbHelper;
    private RecyclerView busList;
    private BusAdapter adapter;
    private ArrayList<BusBean> busBeans = new ArrayList<>();

    private final int REQ_FAVORITE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        dbHelper = new BusDBHelper(this,"busdb", null, 1);

        ArrayList<String> result = dbHelper.getFavorite();
        ArrayList<BusBean> data = (ArrayList<BusBean>) getIntent().getSerializableExtra("data");

        for (BusBean bus: data) {
            for (String routeId: result) {
                if(bus.getRouteId().equals(routeId)){
                    bus.setFavorite(true);
                    busBeans.add(bus);
                }
            }
        }

        busList = findViewById(R.id.busList);

        adapter = new BusAdapter(busBeans, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        busList.setLayoutManager(layoutManager);
        busList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, DetailBus.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("bean",busBeans.get(position));

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

                for (BusBean bus: busBeans) {
                    if(bus.getRouteId().equals(routeId)){
                        busBeans.remove(bus);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
