package com.nipponit.demofirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private FirebaseDatabase contactdb;
    private DatabaseReference RootcontactRefernce;
    private DatabaseReference ChildcontactReference;
    TextView lblname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        contactdb = FirebaseDatabase.getInstance();
        RootcontactRefernce = contactdb.getReference("contacts");
        RootcontactRefernce.child("name").setValue("");
        ChildcontactReference = RootcontactRefernce.child("name");

        //label for display connections for test
        lblname = (TextView)findViewById(R.id.lblname);

    }

    @Override
    protected void onStart() {
        super.onStart();

        ChildcontactReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                //remove redundancy
                lblname.setText(name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
