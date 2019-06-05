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

        Bundle extras=getIntent().getExtras();
        b1=(Button) findViewById(R.id.saveToDatabase);
        team1=findViewById(R.id.team1);
        team2=findViewById(R.id.team2);
        if(extras!=null)
        {
            team1.setText(extras.getString("team1"));
            team2.setText(extras.getString("team2"));
        }
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
        t1_totalCatches=(EditText)findViewById(R.id.t1totalcatches);
        t2_totalCatches=(EditText)findViewById(R.id.t2totalcatches);
        t1_result=(EditText)findViewById(R.id.t1_result);
        t2_result=(EditText)findViewById(R.id.t2_result);
        t1_highestWickets=findViewById(R.id.t1highestwickets);
        t2_highestWickets=findViewById(R.id.t2highestwickets);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTODatabase(v);

            }
        });
    }

    public void addTODatabase(View view) {
        String team_1=team1.getText().toString();
        String team_2=team2.getText().toString();
        String dateT1=  t1_date.getText().toString();
        String dateT2=  t2_date.getText().toString();
        String tossT1=t1_toss.getText().toString();
        String tossT2=t2_toss.getText().toString();
        String scoreT1=t1_score.getText().toString();
        String scoreT2=t2_score.getText().toString();
        Integer wicketT1= Integer.parseInt(t1_wicketTaken.getText().toString());
        Integer wicketT2=Integer.parseInt( t2_wicketTaken.getText().toString());
        Integer total6sT1=Integer.parseInt( t1_total6s.getText().toString());
        Integer total6sT2=Integer.parseInt( t2_total6s.getText().toString());
        Integer total4sT2=Integer.parseInt( t2_total4s.getText().toString());
        Integer total4sT1=Integer.parseInt( t1_total4s.getText().toString());
        Integer extrasT1=Integer.parseInt( t1_extras.getText().toString());
        Integer extrasT2=Integer.parseInt( t2_extras.getText().toString());
        Integer opT1=Integer.parseInt( t1_op_partnership.getText().toString());
        Integer opT2=Integer.parseInt( t2_op_partnership.getText().toString());
        Integer hscorerT1=Integer.parseInt( t1_highestScorer.getText().toString());
        Integer hscorerT2=Integer.parseInt( t2_highestScorer.getText().toString());
        String hwickettekenT1=t1_highestWickets.getText().toString();
        String hwickettakenT2=t2_highestWickets.getText().toString();
        Integer totalcatchesT1=Integer.parseInt( t1_totalCatches.getText().toString());
        Integer totalcatchesT2=Integer.parseInt( t2_totalCatches.getText().toString());
        String resultT1=t1_result.getText().toString();
        String resultT2=t2_result.getText().toString();
        MatchDetail lastmatch=new MatchDetail(team_1,team_2,dateT1,dateT2,tossT1,tossT2,scoreT1,scoreT2,wicketT1,wicketT2,
                total6sT1,total6sT2,total4sT1,total4sT2,extrasT1,extrasT2,opT1,opT2,hscorerT1,hscorerT2,
                hwickettekenT1,hwickettakenT2,totalcatchesT1,totalcatchesT2,resultT1,resultT2);
        Toast.makeText(this,tossT1,Toast.LENGTH_SHORT).show();
        mRootRef.child("Analytics").child("Cricket").child(team_1+team_2).child("Match1").setValue(lastmatch);
        Toast.makeText(this,"Added to Firebase",Toast.LENGTH_SHORT).show();
    }
}
