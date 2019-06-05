package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SelectMatch extends AppCompatActivity {

    Button b1,b2;
    Spinner s1,s2;
    String t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_match);
        b1=(Button)findViewById(R.id.addmatchtdata);
        b2=(Button)findViewById(R.id.get_match_stats);
        s1=(Spinner)findViewById(R.id.spinner);
        s2=(Spinner)findViewById(R.id.spinner2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMatchData(v);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStats(v);
            }
        });

    }

    public void addMatchData(View view) {
        Intent intent =new Intent(this,AdminMatch.class);
        t1=s1.getSelectedItem().toString();
        t2=s2.getSelectedItem().toString();
        intent.putExtra("team1",t1);
        intent.putExtra("team2",t2);
        startActivity(intent);
    }


    public void getStats(View view) {
        Intent intent=new Intent(this,MatchStats.class);
        t1=s1.getSelectedItem().toString();
        t2=s2.getSelectedItem().toString();
        intent.putExtra("team1",t1);
        intent.putExtra("team2",t2);
        startActivity(intent);
    }

}
