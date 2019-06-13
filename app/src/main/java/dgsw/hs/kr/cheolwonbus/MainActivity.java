package dgsw.hs.kr.cheolwonbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ArrayList<BusBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            data= new Description().execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ShowSearchScreen(View v){
        Intent intent = new Intent(this, SearchStop.class);
        intent.putExtra("data",data);
        startActivity(intent);
    }

    public void ShowFavorite(View v){
        Intent intent = new Intent(this, Favorite.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}
