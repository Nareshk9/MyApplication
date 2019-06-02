package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class AdminMatch extends AppCompatActivity {


    EditText t1_date,t2_date;
    EditText t1_toss,t2_toss;
    EditText t1_score,t2_score;
    EditText t1_wicketTaken,t2_wicketTaken;
    EditText t1_total6s,t2_total6s;
    EditText t1_total4s,t2_total4s;
    EditText t1_extras,t2_extras;
    EditText t1_op_partnership,t2_op_partnership;
    EditText t1_highestScorer,t2_highestScorer;
    EditText t1_highestWickets,t2_highestWickets;
    EditText t1_totalCatches,t2_totalCatches;
    EditText t1_result,t2_result;
    Button b1;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_match);

        b1=(Button) findViewById(R.id.saveToDatabase);

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
        t1_op_partnership=(EditText)findViewById(R.id.t1_op);
        t2_op_partnership=(EditText)findViewById(R.id.t2_op);
        t1_highestScorer=(EditText)findViewById(R.id.t1highestscore);
        t2_highestScorer=(EditText)findViewById(R.id.t2highestscore);
        t1_highestWickets=(EditText)findViewById(R.id.t1highestwickets);
        t2_highestWickets=(EditText)findViewById(R.id.t2highestwickets);
        t1_totalCatches=(EditText)findViewById(R.id.t1totalcatches);
        t2_totalCatches=(EditText)findViewById(R.id.t2totalcatches);
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

        Date dateT1= (Date) t1_date.getText();
        Date dateT2= (Date) t2_date.getText();
        String tossT1=t1_toss.getText().toString();
        String tossT2=t2_toss.getText().toString();
        String scoreT1=t1_score.getText().toString();
        String scoreT2=t2_score.getText().toString();
        Number wicketT1= (Number) t1_wicketTaken.getText();
        Number wicketT2=(Number) t2_wicketTaken.getText();
        Number total6sT1=(Number) t1_total6s.getText();
        Number total6sT2=(Number) t2_total6s.getText();
        Number total4sT2=(Number) t2_total4s.getText();
        Number total4sT1=(Number) t1_total4s.getText();
        Number extrasT1=(Number) t1_extras.getText();
        Number extrasT2=(Number) t2_extras.getText();
        Number opT1=(Number) t1_op_partnership.getText();
        Number opT2=(Number) t2_op_partnership.getText();
        Number hscorerT1=(Number) t1_highestScorer.getText();
        Number hscorerT2=(Number) t2_highestScorer.getText();
       String hwickettekenT1=t1_highestWickets.getText().toString();
       String hwickettakenT2=t2_highestWickets.getText().toString();
       Number totalcatchesT1=(Number) t1_totalCatches.getText();
       Number totalcatchesT2=(Number) t2_totalCatches.getText();
       String resultT1=t1_result.getText().toString();
       String resultT2=t2_result.getText().toString();

       MatchDetail lastmatch=new MatchDetail(dateT1,dateT2,tossT1,tossT2,scoreT1,scoreT2,wicketT1,wicketT2,
               total6sT1,total6sT2,total4sT1,total4sT2,extrasT1,extrasT2,opT1,opT2,hscorerT1,hscorerT2,
               hwickettekenT1,hwickettakenT2,totalcatchesT1,totalcatchesT2,resultT1,resultT2);

       mRootRef.child("Analytics").child("Cricket").push().setValue(lastmatch);
        Toast.makeText(this,"Added to Firebase",Toast.LENGTH_SHORT).show();
    }


}
