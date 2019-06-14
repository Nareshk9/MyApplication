package com.example.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StadiumOption extends AppCompatActivity {

    Button b1;
    EditText t1,t2;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_option);

        b1=findViewById(R.id.addStadium);
        t1=findViewById(R.id.etvenue);
        t2=findViewById(R.id.etpitchinfo);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminAddStadium(v);
            }
        });
    }

    void adminAddStadium(View view)
    {
        String venue=t1.getText().toString();
        String pitchInfo=t2.getText().toString();
        StadiumInfo stadiumInfo=new StadiumInfo(venue,pitchInfo);
        dbRef.child("Analytics").child("Cricket").child("StadiumList").child(venue).setValue(stadiumInfo);
        Toast.makeText(this,"Added To Firebase",Toast.LENGTH_SHORT);
        Intent intent=new Intent(this,AdminStadium.class);
        startActivity(intent);
    }
}
