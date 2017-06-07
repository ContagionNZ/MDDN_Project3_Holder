package com.example.steven.matkarta;

/**
 * Created by Steven on 23/05/2017.
 */


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tab3Total extends Fragment{


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference listReference;
    DatabaseReference listReference2;
    Context context;
    String response;
    String response2;
    LinearLayout ll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3total, container, false);
        context = this.getContext();
        listReference = mRootRef.child("Test one");
        listReference2 = mRootRef.child("Test Two");
        ll = (LinearLayout)rootView.findViewById(R.id.ll);
        ll.setOrientation(LinearLayout.VERTICAL);


        listReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                response = dataSnapshot.getValue(String.class);
                TextView tv = new TextView(context);
                tv.setText(response);
                ll.addView(tv);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                response2 = dataSnapshot.getValue(String.class);
                TextView tv = new TextView(context);
                tv.setText(response2);
                ll.addView(tv);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return rootView;

    }

}
