package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectMatch extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_match);
        b1=(Button)findViewById(R.id.get_match_stats);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStats(v);
            }
        });

    }

    public void getStats(View view) {
        Intent intent =new Intent(this,AdminMatch.class);
        startActivity(intent);

    }
}
