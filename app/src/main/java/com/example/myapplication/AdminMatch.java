package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminMatch extends AppCompatActivity {
    TextView team1,team2;
    EditText matchNo;
    EditText t1_opp,t2_opp,t1_date,t2_date;
    EditText t1_toss,t2_toss;
    EditText t1_score,t2_score;
    EditText t1_wicketTaken,t2_wicketTaken;
    EditText t1_total6s,t2_total6s;
    EditText t1_total4s,t2_total4s;
    EditText t1_extras,t2_extras,t1_ros,t2_ros,t1_mOver,t2_mOver;
    EditText t1_wideBalls,t2_wideBalls,t1_noBalls,t2_noBalls;
    EditText t1_c,t2_c,t1_hc,t2_hc;
    EditText t1_totalCatches,t2_totalCatches,t1_BU,t2_BU;
    EditText t1_result,t2_result;
    Button b1;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_match);

        Bundle extras=getIntent().getExtras();
        b1=(Button) findViewById(R.id.saveToDatabase);
        matchNo=findViewById(R.id.matchNo);
        team1=findViewById(R.id.team1);
        team2=findViewById(R.id.team2);
        if(extras!=null)
        {
            team1.setText(extras.getString("team1"));
            team2.setText(extras.getString("team2"));
        }
        t1_opp= findViewById(R.id.t1_against);
        t2_opp=findViewById(R.id.t2_against);
        t1_date=(EditText)findViewById(R.id.t1_date);
        t2_date=(EditText)findViewById(R.id.t2_date);
        t1_toss=(EditText) findViewById(R.id.t1_toss);
        t2_toss=(EditText)findViewById(R.id.t2_toss);
        t1_score=(EditText)findViewById(R.id.t1_score);
        t2_score=(EditText)findViewById(R.id.t2_score);
        t1_wicketTaken=(EditText)findViewById(R.id.t1wickets);
        t2_wicketTaken=(EditText)findViewById(R.id.t2wickets);
        t1_total6s=(EditText)findViewById(R.id.t1Total6s);
        t2_total6s=(EditText)findViewById(R.id.t2total6s);
        t1_total4s=(EditText)findViewById(R.id.t1total4s);
        t2_total4s=(EditText)findViewById(R.id.t2total4s);
        t1_extras=(EditText)findViewById(R.id.t1_extras);
        t2_extras=(EditText)findViewById(R.id.t2_extras);
        t1_ros=findViewById(R.id.t1_rs);
        t2_ros=findViewById(R.id.t2_rs);
        t1_mOver=findViewById(R.id.t1_mOver);
        t2_mOver=findViewById(R.id.t2_mOver);
        t1_wideBalls=findViewById(R.id.t1_wideBalls);
        t2_wideBalls=findViewById(R.id.t2_wideBalls);
        t1_noBalls=findViewById(R.id.t1_noBalls);
        t2_noBalls=findViewById(R.id.t2_noBalls);
        t1_hc=findViewById(R.id.t1_hc);
        t2_hc=findViewById(R.id.t2_hc);
        t1_c=findViewById(R.id.t1_c);
        t2_c=findViewById(R.id.t2_c);
        t1_totalCatches=(EditText)findViewById(R.id.t1totalcatches);
        t2_totalCatches=(EditText)findViewById(R.id.t2totalcatches);
        t1_BU=findViewById(R.id.t1_bU);
        t2_BU=findViewById(R.id.t2_bu);
        t1_result=(EditText)findViewById(R.id.t1_result);
        t2_result=(EditText)findViewById(R.id.t2_result);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTODatabase(v);

            }
        });
    }


    public void addTODatabase(View view) {
        String match_no=matchNo.getText().toString();
        String team_1=team1.getText().toString();
        String team_2=team2.getText().toString();
        String t1opp=t1_opp.getText().toString();
        String t2opp=t2_opp.getText().toString();
        String dateT1=  t1_date.getText().toString();
        String dateT2=  t2_date.getText().toString();
        String tossT1=t1_toss.getText().toString();
        String tossT2=t2_toss.getText().toString();
        String scoreT1=t1_score.getText().toString();
        String scoreT2=t2_score.getText().toString();
        Integer wicketT1= Integer.parseInt(t1_wicketTaken.getText().toString());
        Integer wicketT2=Integer.parseInt( t2_wicketTaken.getText().toString());
        Integer total4sT1=Integer.parseInt( t1_total4s.getText().toString());
        Integer total4sT2=Integer.parseInt( t2_total4s.getText().toString());
        Integer total6sT1=Integer.parseInt( t1_total6s.getText().toString());
        Integer total6sT2=Integer.parseInt( t2_total6s.getText().toString());
        Integer t1Mover=Integer.parseInt(t1_mOver.getText().toString());
        Integer t2Mover=Integer.parseInt(t2_mOver.getText().toString());
        Integer t1Wide=Integer.parseInt(t1_wideBalls.getText().toString());
        Integer t2Wide=Integer.parseInt(t2_wideBalls.getText().toString());
        Integer t1noBall=Integer.parseInt(t1_noBalls.getText().toString());
        Integer t2noBall=Integer.parseInt(t2_noBalls.getText().toString());
        Integer t1HC=Integer.parseInt(t1_hc.getText().toString());
        Integer t2Hc=Integer.parseInt(t2_hc.getText().toString());
        Integer t1C=Integer.parseInt(t1_c.getText().toString());
        Integer t2c=Integer.parseInt(t2_c.getText().toString());

        Integer extrasT1=Integer.parseInt( t1_extras.getText().toString());
        Integer extrasT2=Integer.parseInt( t2_extras.getText().toString());
        Integer totalcatchesT1=Integer.parseInt( t1_totalCatches.getText().toString());
        Integer totalcatchesT2=Integer.parseInt( t2_totalCatches.getText().toString());
        Integer t1ROS=Integer.parseInt(t1_ros.getText().toString());
        Integer t2ROS=Integer.parseInt(t2_ros.getText().toString());
        Integer t1bUsed=Integer.parseInt(t1_BU.getText().toString());
        Integer t2bUsed=Integer.parseInt(t2_BU.getText().toString());
        String resultT1=t1_result.getText().toString();
        String resultT2=t2_result.getText().toString();
        MatchDetail lastMatch=new MatchDetail(team_1,team_2,t1opp,t2opp,dateT1,dateT2,tossT1,tossT2,scoreT1,scoreT2,wicketT1,wicketT2,t1ROS,t2ROS,
                total6sT1,total6sT2,total4sT1,total4sT2,extrasT1,extrasT2,t1Mover,t2Mover,t1Wide,t2Wide,t1noBall,t2noBall,t1C,t2c,t1HC,t2Hc,t1bUsed,
                t2bUsed,totalcatchesT1,totalcatchesT2,resultT1,resultT2);
        Toast.makeText(this,"Adding",Toast.LENGTH_SHORT).show();
        mRootRef.child("Analytics").child("Cricket").child(team_1+team_2).child(match_no).setValue(lastMatch);
        Toast.makeText(this,"Added to Firebase",Toast.LENGTH_SHORT).show();

    }
}
