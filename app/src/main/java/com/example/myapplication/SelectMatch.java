package com.example.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class SelectMatch extends AppCompatActivity {

    Button b1,b2,b3,b4;
    Spinner s1,s2;
    String t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_match);
        b1=(Button)findViewById(R.id.get_stadium_stat);
        b2=(Button)findViewById(R.id.get_match_stats);
        b3=(Button) findViewById(R.id.addmatchtofirebase);
        b4=(Button)findViewById(R.id.addStadiumtofirebase);

        s1=(Spinner)findViewById(R.id.spinner);
        s2=(Spinner)findViewById(R.id.spinner2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStadiumStat(v);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStats(v);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminAddMatchData(v);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminAddStadium(v);


            }
        });

    }

    public void adminAddMatchData(View view) {
        Intent intent =new Intent(this,AdminMatch.class);
        Bundle bundle=new Bundle();
        t1=s1.getSelectedItem().toString();
        t2=s2.getSelectedItem().toString();
        bundle.putString("team1",t1);
        bundle.putString("team2",t2);
        //intent.putExtra("team1",t1);
        //intent.putExtra("team2",t2);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void getStats(View view) {
        /*FragmentManager fm=getSupportFragmentManager();
        TeamPreviousMatchFragment tmp=new TeamPreviousMatchFragment();*/
        Bundle b=new Bundle();
        Intent intent=new Intent(this,Extra2.class);
        t1=s1.getSelectedItem().toString();
        t2=s2.getSelectedItem().toString();
        b.putString("team1",t1);
        b.putString("team2",t2);
        intent.putExtras(b);
        /*tmp.setArguments(b);
        fm.beginTransaction().replace(R.id.selectmatchactivity,tmp).commit();*/
        //intent.putExtra("team1",t1);
        //intent.putExtra("team2",t2);
        startActivity(intent);
    }

    public void getStadiumStat(View view) {

        Intent intent=new Intent(this,Extra.class);
        startActivity(intent);
    }


    public void adminAddStadium(View view) {
        Intent intent=new Intent(this,StadiumOption.class);
        startActivity(intent);
    }
}
