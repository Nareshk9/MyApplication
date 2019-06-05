package com.example.myapplication;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MatchStats extends AppCompatActivity {

    TextView team_1,team_2;
    TextView team1_date,team2_date;
    TextView team1_toss,team2_toss;
    TextView team1_score,team2_score;
    TextView team1_wickettaken,team2_wickettaken;
    TextView team1_6s,team2_6s;
    TextView team1_4s,team2_4s;
    TextView team1_extras,team2_extras;
    TextView team1_op,team2_op;
    TextView team1_hscorer,team2_hscorer;
    TextView team1_hwicketTaker,team2_hwicketTaker;
    TextView team1_totalcatches,team2_totalcatches;
    TextView team1_result,team2_result;

    DatabaseReference mref;
    MatchDetail mDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_stats);

        Bundle extras=getIntent().getExtras();
        team_1=(TextView) findViewById(R.id.team1);
        team_2=(TextView) findViewById(R.id.team2);
        if(extras!=null)
        {
            team_1.setText(extras.getString("team1"));
            team_2.setText(extras.getString("team2"));
        }
        String t1,t2;
        t1=team_1.getText().toString();
        t2=team_2.getText().toString();
        team1_date=(TextView)findViewById(R.id.teamA_mdate);
        team2_date=(TextView)findViewById(R.id.teamB_mdate);
        team1_toss=(TextView)findViewById(R.id.teamA_toss);
        team2_toss=(TextView)findViewById(R.id.teamB_toss);
        team1_score=(TextView)findViewById(R.id.teamA_score);
        team2_score=(TextView)findViewById(R.id.teamB_score);
        team1_wickettaken=(TextView)findViewById(R.id.teamA_wicketTaken);
        team2_wickettaken=(TextView)findViewById(R.id.teamB_wicketTaken);
        team1_6s=(TextView)findViewById(R.id.teamA_total6s);
        team2_6s=(TextView)findViewById(R.id.teamB_total6s);
        team1_4s=(TextView)findViewById(R.id.teamA_total4s);
        team2_4s=(TextView)findViewById(R.id.teamB_total4s);
        team1_extras=(TextView)findViewById(R.id.teamA_extras);
        team2_extras=(TextView)findViewById(R.id.teamB_extras);
        team1_op=(TextView)findViewById(R.id.teamA_op);
        team2_op=(TextView)findViewById(R.id.teamB_op);
        team1_hscorer=(TextView)findViewById(R.id.teamA_hscorer);
        team2_hscorer=(TextView)findViewById(R.id.teamB_hscorer);
        team1_hwicketTaker=(TextView)findViewById(R.id.teamA_hwickets);
        team2_hwicketTaker=(TextView)findViewById(R.id.teamB_hwickets);
        team1_totalcatches=(TextView)findViewById(R.id.teamA_tcatches);
        team2_totalcatches=(TextView)findViewById(R.id.teamB_tcatches);
        team1_result=(TextView)findViewById(R.id.teamA_result);
        team2_result=(TextView)findViewById(R.id.teamB_result);

        mref=FirebaseDatabase.getInstance().getReference().child("Analytic").child("Cricket").child("ENGPAK").child("Match1");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mDet=dataSnapshot.getValue(MatchDetail.class);
                team1_date.setText(mDet.t1_date);
                team2_date.setText(mDet.t2_date);
                team1_toss.setText(mDet.t1_toss);
                team2_toss.setText(mDet.t2_toss);
                team1_score.setText(mDet.t1_score);
                team2_score.setText(mDet.t2_score);
                team1_wickettaken.setText(mDet.t1_wicketTaken);
                team2_wickettaken.setText(mDet.t2_wicketTaken);
                team1_6s.setText(mDet.t1_total6s);
                team2_6s.setText(mDet.t2_total6s);
                team1_4s.setText(mDet.t1_total4s);
                team2_4s.setText(mDet.t2_total4s);
                team1_extras.setText(mDet.t1_extras);
                team2_extras.setText(mDet.t2_extras);
                team1_op.setText(mDet.t1_op);
                team2_op.setText(mDet.t2_op);
                team1_hscorer.setText(mDet.t1_highestScorer);
                team2_hscorer.setText(mDet.t2_highestScorer);
                team1_hwicketTaker.setText(mDet.t1_highestWickets);
                team1_hwicketTaker.setText(mDet.t2_highestWickets);
                team1_totalcatches.setText(mDet.t1_totalCatches);
                team2_totalcatches.setText(mDet.t2_totalCatches);
                team1_result.setText(mDet.t1_result);
                team2_result.setText(mDet.t2_result);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("UserListActivity", "Error occured");
            }
        });

    }
}
