package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Extra extends AppCompatActivity {

    TextView sVenue,sPitchInfo;
    DatabaseReference mRootRef;
    StadiumInfo stadiumInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        sVenue=findViewById(R.id.svenue);
        sPitchInfo=findViewById(R.id.spitchinfo);
        mRootRef=FirebaseDatabase.getInstance().getReference().child("Analytics").child("Cricket").child("StadiumList").child("sv");
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stadiumInfo=dataSnapshot.getValue(StadiumInfo.class);
                sVenue.setText(stadiumInfo.Venue);
                sPitchInfo.setText(stadiumInfo.PitchInfo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FragmentManager fm=getSupportFragmentManager();
        StadiumStatsFragment stadiumStatsFragment=new StadiumStatsFragment();
        fm.beginTransaction().replace(R.id.activityextra,stadiumStatsFragment).commit();
    }
}
