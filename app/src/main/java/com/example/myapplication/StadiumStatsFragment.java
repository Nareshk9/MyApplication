package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class StadiumStatsFragment extends Fragment {
    private RecyclerView mRecycler;
    private DatabaseReference mFriendReference;
    private LinearLayoutManager mManager;
    private StadiumStatAdapter mAdapter;


    public StadiumStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stadium_stats, container, false);
        mRecycler = view.findViewById(R.id.recyclerviewmatchstat);
        mFriendReference = FirebaseDatabase.getInstance().getReference().child("Analytics").child("Cricket").child("StadiumStats");
        mManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearSnapHelper linearSnapHelper = new SnapHelperByOne();
        linearSnapHelper.attachToRecyclerView(mRecycler);
        //mManager.setReverseLayout(true);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(mManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter = new StadiumStatAdapter(getContext(), mFriendReference);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private static class StadiumStatViewholder extends RecyclerView.ViewHolder {

        Button matchNo;
        TextView t1, t2, t1_date, t2_date, t1_score, t2_score;
        TextView t1_wicket, t2_wicket, t1_6s, t2_6s, t1_4s, t2_4s, t1_catches, t2_catches;
        TextView t1_result, t2_result;


        public StadiumStatViewholder(@NonNull View itemView) {
            super(itemView);

            matchNo=itemView.findViewById(R.id.matchid);

            t1 = itemView.findViewById(R.id.team1);
            t2 = itemView.findViewById(R.id.team2);
            t1_date = itemView.findViewById(R.id.teamA_mdate);
            t2_date = itemView.findViewById(R.id.teamB_mdate);
            t1_score = itemView.findViewById(R.id.teamA_score);
            t2_score = itemView.findViewById(R.id.teamB_score);
            t1_wicket = itemView.findViewById(R.id.teamA_wicketTaken);
            t2_wicket = itemView.findViewById(R.id.teamB_wicketTaken);
            t1_6s = itemView.findViewById(R.id.teamA_total6s);
            t2_6s = itemView.findViewById(R.id.teamB_total6s);
            t1_4s = itemView.findViewById(R.id.teamA_total4s);
            t2_4s = itemView.findViewById(R.id.teamB_total4s);
            t1_catches = itemView.findViewById(R.id.teamA_tcatches);
            t2_catches = itemView.findViewById(R.id.teamB_tcatches);
            t1_result = itemView.findViewById(R.id.teamA_result);
            t2_result = itemView.findViewById(R.id.teamB_result);
        }
    }

        private class StadiumStatAdapter extends Adapter<StadiumStatViewholder> {

            private Context mContext;
            private DatabaseReference mDatabaseReference;
            private ChildEventListener mChildEventListener;

            private List<String> StadiumIds = new ArrayList<>();
            private List<StadiumData> StadiumList = new ArrayList<>();

            public StadiumStatAdapter(final Context context, DatabaseReference ref) {
                mContext = context;
                mDatabaseReference = ref;
                ChildEventListener childEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                        StadiumData md = dataSnapshot.getValue(StadiumData.class);
                        StadiumIds.add(dataSnapshot.getKey());
                        StadiumList.add(md);
                        notifyItemInserted(StadiumList.size() - 1);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        StadiumData md = dataSnapshot.getValue(StadiumData.class);
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {


                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                ref.addChildEventListener(childEventListener);
                mChildEventListener = childEventListener;
            }


            @NonNull
            @Override
            public StadiumStatViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //View view=null;
                LayoutInflater inflater = LayoutInflater.from(mContext);
                View view = inflater.inflate(R.layout.stadiumlayout, viewGroup, false);
                return new StadiumStatViewholder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull StadiumStatViewholder stadiumStatViewholder, int i) {

                if (i == 0) {
                    stadiumStatViewholder.matchNo.setText("Last Match");
                } else if (i == 1) {
                    stadiumStatViewholder.matchNo.setText("Second Last Match");
                } else if (i == 2) {
                    stadiumStatViewholder.matchNo.setText("Third Last match");
                }

                final StadiumData stadiumData = StadiumList.get(i);

                stadiumStatViewholder.t1.setText(stadiumData.t1Name);
                stadiumStatViewholder.t2.setText(stadiumData.t2Name);
                stadiumStatViewholder.t1_date.setText(stadiumData.t1Date);
                stadiumStatViewholder.t2_date.setText(stadiumData.t2Date);
                stadiumStatViewholder.t1_score.setText(stadiumData.t1Score);
                stadiumStatViewholder.t2_score.setText(stadiumData.t2Score);
                stadiumStatViewholder.t1_wicket.setText(String.valueOf(stadiumData.t1WicketTaken));
                stadiumStatViewholder.t1_wicket.setText(String.valueOf(stadiumData.t2WicketTaken));
                stadiumStatViewholder.t1_4s.setText(String.valueOf(stadiumData.t14s));
                stadiumStatViewholder.t2_4s.setText(String.valueOf(stadiumData.t24s));
                stadiumStatViewholder.t1_6s.setText(String.valueOf(stadiumData.t16s));
                stadiumStatViewholder.t2_6s.setText(String.valueOf(stadiumData.t26s));
                stadiumStatViewholder.t1_catches.setText(String.valueOf(stadiumData.t1Catches));
                stadiumStatViewholder.t2_catches.setText(String.valueOf(stadiumData.t2Catches));
                stadiumStatViewholder.t1_result.setText(stadiumData.t1Result);
                stadiumStatViewholder.t2_result.setText(stadiumData.t2Result);
            }


            @Override
            public int getItemCount() {
                return StadiumList.size();
            }
        }


    }