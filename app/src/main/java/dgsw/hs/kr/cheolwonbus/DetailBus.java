package dgsw.hs.kr.cheolwonbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class DetailBus extends AppCompatActivity {

    private RecyclerView timeList;
    private TimeAdapter timeAdapter;
    private ArrayList<String> lstTime;

    private TextView textViewRoute;
    private TextView textViewNode;
    private ToggleButton toggleButton;
    private TextView textViewRemainingTime;
    private TextView textViewNextTime;

    private String routeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bus);

        Intent intent = getIntent();
        BusBean busBean = (BusBean)intent.getSerializableExtra("bean");

        timeList = findViewById(R.id.timeList);
        textViewRoute = findViewById(R.id.textViewRoute);
        textViewNode = findViewById(R.id.textViewNode);
        toggleButton = findViewById(R.id.toggleButton);
        textViewRemainingTime = findViewById(R.id.textViewRemainingTime);
        textViewNextTime = findViewById(R.id.textViewNextTime);

        routeId = busBean.getRouteId();
        lstTime = busBean.getTimes();
        timeAdapter = new TimeAdapter(lstTime);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 5);
        timeList.setLayoutManager(layoutManager);
        timeList.setAdapter(timeAdapter);

        textViewRoute.setText(busBean.getRoute());
        textViewNode.setText(busBean.getStartStation() + "~" + busBean.getEndStation());
        toggleButton.setChecked(busBean.isFavorite());
        textViewRemainingTime.setText(busBean.getRemainTime());
        textViewNextTime.setText(busBean.getNextTime());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            if(keyCode == KeyEvent.KEYCODE_BACK){
                Intent intent = new Intent();
                intent.putExtra("routeId", routeId);
                intent.putExtra("isFavorite", toggleButton.isChecked());
                setResult(RESULT_OK, intent);

                finish();

                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
