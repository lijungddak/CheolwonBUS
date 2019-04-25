package dgsw.hs.kr.cheolwonbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShowSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search);
    }

    public void ShowSearchNumber(View v){
        startActivity(new Intent(this, SearchNumber.class));
    }

    public void ShowSearchStop(View view) {
        startActivity(new Intent(this, SearchStop.class));
    }
}
