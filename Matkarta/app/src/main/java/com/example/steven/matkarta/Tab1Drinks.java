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
import android.widget.ListView;
import android.widget.TextView;

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
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> prices = new ArrayList<String>();
    ListView drinkNames;
    ListView drinkPrices;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1drinks, container, false);
        context = this.getContext();
        drinkNames = (ListView) rootView.findViewById(R.id.drinkNames);
        drinkPrices = (ListView) rootView.findViewById(R.id.drinkPrices);
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
                names = new ArrayList<String>(drinks.keySet());
                prices = new ArrayList<String>(drinks.values());
//                adapter = new ArrayAdapter<String>(context, R.layout.list_button, names);
                System.out.println("----------------------------------------------");
                adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, names);
                adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, prices);
                System.out.println("----------------------------------------------");
                drinkNames.setAdapter(adapter);
                drinkPrices.setAdapter(adapter2);
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }

}
