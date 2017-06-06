package com.example.steven.matkarta;

/**
 * Created by Steven on 23/05/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Tab1Drinks extends Fragment{

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference listReference; // = mRootRef.child("Restaurants");
    HashMap<String, HashMap<String,String>> response = new HashMap<String, HashMap<String, String>>();
    HashMap<String, String> drinks = new HashMap<String, String>();
    ScrollView scrollView;
    LinearLayout drinkLayout;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1drinks, container, false);
        final MergeAdapter mergeAdapter = new MergeAdapter();
        context = this.getContext();
        scrollView = (ScrollView) rootView.findViewById(R.id.drinkScroll);
        drinkLayout = (LinearLayout) rootView.findViewById(R.id.drinkLayout);
        Intent intent = getActivity().getIntent();
        String restaurantName = intent.getStringExtra("Restaurant_ID");
        System.out.println(restaurantName + "_Menu");
        listReference = mRootRef.child(restaurantName + "_Menu");
        listReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String,HashMap<String,String>>> t = new GenericTypeIndicator<HashMap<String, HashMap<String, String>>>() {};
                response = dataSnapshot.getValue(t);
                drinks = response.get("Drinks");

                for(String s : drinks.keySet()){
                    // create linearLayout which will represent a row
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                    // create the drink name
                    TextView drinkName = new TextView(context);
                    drinkName.setText(s);
                    linearLayout.addView(drinkName);
                    // create the drink price
                    TextView drinkPrice = new TextView(context);
                    drinkPrice.setText(drinks.get(s));
                    linearLayout.addView(drinkPrice);



                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }

}
