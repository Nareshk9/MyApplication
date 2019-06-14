package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminStadium extends AppCompatActivity {

    Button b3;
    TextView t1,t2,t1_date,t2_date,t1_score,t2_score;
    TextView t1_wicket,t2_wicket,t1_6s,t2_6s,t1_4s,t2_4s;
    TextView t1_catches,t2_catches,t1_result,t2_result;

    DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stadium);
        b3= findViewById(R.id.button3);
        t1=findViewById(R.id.steam1);
        t2=findViewById(R.id.steam2);
        t1_date=findViewById(R.id.steamA_mdate);
        t2_date=findViewById(R.id.steamB_mdate);
        t1_score=findViewById(R.id.steamA_score);
        t2_score=findViewById(R.id.steamB_score);
        t1_wicket=findViewById(R.id.steamA_wicketTaken);
        t2_wicket=findViewById(R.id.steamB_wicketTaken);
        t1_6s=findViewById(R.id.steamA_total6s);
        t2_6s=findViewById(R.id.steamB_total6s);
        t1_4s=findViewById(R.id.steamA_total4s);
        t2_4s=findViewById(R.id.steamB_total4s);
        t1_catches=findViewById(R.id.steamA_tcatches);
        t2_catches=findViewById(R.id.steamB_tcatches);
        t1_result=findViewById(R.id.steamA_result);
        t2_result=findViewById(R.id.steamB_result);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStadiumData(v);
            }
        });

    }

    public void addStadiumData(View view) {

        String team1=t1.getText().toString();
        String team2=t2.getText().toString();
        String t1Date=t1_date.getText().toString();
        String t2Date=t2_date.getText().toString();
        String t1Score=t1_score.getText().toString();
        String t2Score=t2_score.getText().toString();
        Integer t1Wicket=Integer.parseInt(t1_wicket.getText().toString());
        Integer t2Wicket=Integer.parseInt(t2_wicket.getText().toString());
        Integer t16s=Integer.parseInt(t1_6s.getText().toString());
        Integer t26s=Integer.parseInt(t2_6s.getText().toString());
        Integer t14s=Integer.parseInt(t1_4s.getText().toString());
        Integer t24s=Integer.parseInt(t2_4s.getText().toString());
        Integer t1catches=Integer.parseInt(t1_catches.getText().toString());
        Integer t2Catches=Integer.parseInt(t2_catches.getText().toString());
        String t1Result=t1_result.getText().toString();
        String t2Result=t2_result.getText().toString();

        StadiumData sd=new StadiumData(team1,team2,t1Date,t2Date,t1Score,t2Score,t1Wicket,t2Wicket,t16s,t26s,t14s,t24s,
                t1catches,t2Catches,t1Result,t2Result);
        mRootRef.child("Analytics").child("Cricket").child("StadiumStats").child("s2").setValue(sd);
        Toast.makeText(this,"Added to Firebase",Toast.LENGTH_SHORT).show();

    }
}
