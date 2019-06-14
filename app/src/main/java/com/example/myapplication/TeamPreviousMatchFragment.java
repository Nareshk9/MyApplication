package com.example.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.google.firebase.storage.FirebaseStorage.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamPreviousMatchFragment extends Fragment {


    private RecyclerView mRecycler;
    private MatchStatAdapter mAdapter;
    private LinearLayoutManager mManager;
    private DatabaseReference mFriendReference;

    public TeamPreviousMatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle b=this.getArguments();
        String t1_Id=b.getString("team1");
        String t2_Id=b.getString("team2");
        View view = inflater.inflate(R.layout.fragment_team_previous_match, container, false);
        mRecycler = view.findViewById(R.id.recyclerviewmatchstat);
        mFriendReference = FirebaseDatabase.getInstance().getReference()
                .child("Analytics").child("Cricket").child(t1_Id+t2_Id);
        mManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearSnapHelper linearSnapHelper=new SnapHelperByOne();
        linearSnapHelper.attachToRecyclerView(mRecycler);
        //mManager.setReverseLayout(true);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(mManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter = new MatchStatAdapter(getContext(), mFriendReference);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    private static class MatchStatViewHolhder extends RecyclerView.ViewHolder
    {
        Button matchno;
        ImageView ivTeam1,ivTeam2;
        TextView team_1,team_2,team_1Opp,team_2Opp;
        TextView team1_date,team2_date;
        TextView team1_toss,team2_toss;
        TextView team1_score,team2_score;
        TextView team1_wickettaken,team2_wickettaken;
        TextView team1_6s,team2_6s,team_1ROS,team_2ROS,team_1Noball,team_2Noball,team_1Wide,team_2Wide;
        TextView team1_4s,team2_4s;
        TextView team1_extras,team2_extras;
        TextView team1_op,team2_op;
        TextView team_1hc,team_2hc,team_1c,team_2c,team_1bUsed,team_2bUsed;
        TextView team1_totalcatches,team2_totalcatches;
        TextView team1_result,team2_result;

        public MatchStatViewHolhder(@NonNull View itemView) {
            super(itemView);
            matchno=itemView.findViewById(R.id.matchid);
            ivTeam1=itemView.findViewById(R.id.team1_image);
            ivTeam2=itemView.findViewById(R.id.team2_image);
            team_1=itemView.findViewById(R.id.team1);
            team_2=itemView.findViewById(R.id.team2);
            team_1Opp=itemView.findViewById(R.id.teamA_opp);
            team_2Opp=itemView.findViewById(R.id.teamB_opp);
            team1_date=itemView.findViewById(R.id.teamA_mdate);
            team2_date=itemView.findViewById(R.id.teamB_mdate);
            team1_toss=itemView.findViewById(R.id.teamA_toss);
            team2_toss=itemView.findViewById(R.id.teamB_toss);
            team1_score=itemView.findViewById(R.id.teamA_score);
            team2_score=itemView.findViewById(R.id.teamB_score);
            team1_wickettaken=itemView.findViewById(R.id.teamA_wicketTaken);
            team2_wickettaken=itemView.findViewById(R.id.teamB_wicketTaken);
            team1_6s=itemView.findViewById(R.id.teamA_total6s);
            team2_6s=itemView.findViewById(R.id.teamB_total6s);
            team1_4s=itemView.findViewById(R.id.teamA_total4s);
            team2_4s=itemView.findViewById(R.id.teamB_total4s);
            team1_extras=itemView.findViewById(R.id.teamA_extras);
            team2_extras=itemView.findViewById(R.id.teamB_extras);
            team_1ROS=itemView.findViewById(R.id.teamA_rs);
            team_2ROS=itemView.findViewById(R.id.teamB_rs);
            team_1Noball=itemView.findViewById(R.id.teamA_noBalls);
            team_2Noball=itemView.findViewById(R.id.teamB_noBalls);
            team_1Wide=itemView.findViewById(R.id.teamA_wideBalls);
            team_2Wide=itemView.findViewById(R.id.teamB_wideBalls);
            team_1hc=itemView.findViewById(R.id.teamA_hc);
            team_2hc=itemView.findViewById(R.id.teamB_hc);
            team_1c=itemView.findViewById(R.id.teamA_c);
            team_2c=itemView.findViewById(R.id.teamB_c);
            team_1bUsed=itemView.findViewById(R.id.teamA_bU);
            team_2bUsed=itemView.findViewById(R.id.teamB_bu);
            team1_totalcatches=itemView.findViewById(R.id.teamA_tcatches);
            team2_totalcatches=itemView.findViewById(R.id.teamB_tcatches);
            team1_result=itemView.findViewById(R.id.teamA_result);
            team2_result=itemView.findViewById(R.id.teamB_result);
        }


    }

    private class MatchStatAdapter extends RecyclerView.Adapter<MatchStatViewHolhder>
    {

        private Context mContext;
        private DatabaseReference mDatabaseReference;
        private ChildEventListener mChildEventListener;

        private List<String> MatchIds=new ArrayList<>();
        private List<MatchDetail> MatchList=new ArrayList<>();

        public MatchStatAdapter(final Context context,DatabaseReference ref)
        {
            mContext=context;
            mDatabaseReference=ref;
            ChildEventListener childEventListener=new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                    MatchDetail md=dataSnapshot.getValue(MatchDetail.class);
                    MatchIds.add(dataSnapshot.getKey());
                    MatchList.add(md);
                    notifyItemInserted(MatchList.size()-1);
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    MatchDetail md=dataSnapshot.getValue(MatchDetail.class);
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
            mChildEventListener=childEventListener;
        }

        @NonNull
        @Override
        public MatchStatViewHolhder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            //View view=null;
            LayoutInflater inflater=LayoutInflater.from(mContext);
            View view=inflater.inflate(R.layout.teamlayout,viewGroup,false);
            return new MatchStatViewHolhder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final MatchStatViewHolhder matchStatViewHolder, int i) {

            if(i==0)
            {
                matchStatViewHolder.matchno.setText("Last Match");
            }
            else if(i==1)
            {
                matchStatViewHolder.matchno.setText("Second Last Match");
            }
            else if(i==2)
            {
                matchStatViewHolder.matchno.setText("Third Last match");
            }
            final MatchDetail matchDetail=MatchList.get(i);
            StorageReference storageRef=FirebaseStorage.getInstance().getReference();
            storageRef.child("cricketTeamLogo/" + matchDetail.team1 + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    new DownLoadImageTask(matchStatViewHolder.ivTeam1).execute(uri.toString());

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });

            storageRef.child("cricketTeamLogo/" + matchDetail.team2 + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    new DownLoadImageTask(matchStatViewHolder.ivTeam2).execute(uri.toString());

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });

            matchStatViewHolder.team_1.setText(matchDetail.team1);
            matchStatViewHolder.team_2.setText(matchDetail.team2);
            matchStatViewHolder.team_1Opp.setText(matchDetail.team1Opp);
            matchStatViewHolder.team_2Opp.setText(matchDetail.team2Opp);
            matchStatViewHolder.team1_date.setText(matchDetail.t1_date);
            matchStatViewHolder.team2_date.setText(matchDetail.t2_date);
            matchStatViewHolder.team1_toss.setText(matchDetail.t1_toss);
            matchStatViewHolder.team2_toss.setText(matchDetail.t2_toss);
            matchStatViewHolder.team1_score.setText(matchDetail.t1_score);
            matchStatViewHolder.team2_score.setText(matchDetail.t2_score);
            matchStatViewHolder.team1_wickettaken.setText(String.valueOf(matchDetail.t1_wicketTaken));
            matchStatViewHolder.team2_wickettaken.setText(String.valueOf(matchDetail.t2_wicketTaken));
            matchStatViewHolder.team1_4s.setText(String.valueOf(matchDetail.t1_total4s));
            matchStatViewHolder.team2_4s.setText(String.valueOf(matchDetail.t2_total4s));
            matchStatViewHolder.team1_6s.setText(String.valueOf(matchDetail.t1_total6s));
            matchStatViewHolder.team2_6s.setText(String.valueOf(matchDetail.t2_total6s));
            matchStatViewHolder.team1_extras.setText(String.valueOf(matchDetail.t1_extras));
            matchStatViewHolder.team2_extras.setText(String.valueOf(matchDetail.t2_extras));
            matchStatViewHolder.team_1c.setText(String.valueOf(matchDetail.t1_c));
            matchStatViewHolder.team_2c.setText(String.valueOf(matchDetail.t2_c));
            matchStatViewHolder.team_1hc.setText(String.valueOf(matchDetail.t1_hc));
            matchStatViewHolder.team_2hc.setText(String.valueOf(matchDetail.t2_hc));
            matchStatViewHolder.team_1Wide.setText(String.valueOf(matchDetail.t1_wideBalls));
            matchStatViewHolder.team_2Wide.setText(String.valueOf(matchDetail.t2_wide_balls));
            matchStatViewHolder.team_1Noball.setText(String.valueOf(matchDetail.t1_noBalls));
            matchStatViewHolder.team_2Noball.setText(String.valueOf(matchDetail.t2_noBalls));
            matchStatViewHolder.team_1bUsed.setText(String.valueOf(matchDetail.t1_bU));
            matchStatViewHolder.team_2bUsed.setText(String.valueOf(matchDetail.t2_bU));
            matchStatViewHolder.team_1ROS.setText(String.valueOf(matchDetail.t1_ros));
            matchStatViewHolder.team_2ROS.setText(String.valueOf(matchDetail.t2_ros));
            matchStatViewHolder.team1_totalcatches.setText(String.valueOf(matchDetail.t1_totalCatches));
            matchStatViewHolder.team2_totalcatches.setText(String.valueOf(matchDetail.t2_totalCatches));
            matchStatViewHolder.team1_result.setText(matchDetail.t1_result);
            matchStatViewHolder.team2_result.setText(matchDetail.t2_result);
        }

        @Override
        public int getItemCount() {
            return MatchList.size();
        }
    }

    private static class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlOfImage = strings[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }
            catch(IOException e)
            {
            }
            return logo;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }



}
