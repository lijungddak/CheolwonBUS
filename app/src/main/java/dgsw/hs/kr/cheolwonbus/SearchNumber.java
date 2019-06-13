package dgsw.hs.kr.cheolwonbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchNumber extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_number);

        editText = findViewById(R.id.etBusNumber);
    }

    public void SearchBusNumber(View v){
        int busNum = Integer.parseInt(editText.getText().toString());


    }
}
