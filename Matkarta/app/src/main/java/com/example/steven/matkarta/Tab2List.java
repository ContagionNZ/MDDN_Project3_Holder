package com.example.steven.matkarta;

/**
 * Created by Steven on 23/05/2017.
 */


import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tab2List extends Fragment{

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference listReference = mRootRef.child("Restraunts");
    private ListView restrauntNames;
    ArrayList<String> response = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getContext();
        View rootView = inflater.inflate(R.layout.tab2list, container, false);
        restrauntNames = (ListView) rootView.findViewById(R.id.restrauntListView);

        listReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                response = dataSnapshot.getValue(t);
                System.out.println("--------------------------------------------------------------");
                System.out.println(response);
                System.out.println(restrauntNames);
                System.out.println(context);
                System.out.println("--------------------------------------------------------------");
                adapter = new ArrayAdapter<String>(context, R.layout.list_button, response);
                restrauntNames.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                for(String s: response){

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }

//    @Override
//    public void onStart(){
//        super.onStart();
//        Activity current = getActivity();
//        adapter = new ArrayAdapter<String>(current, android.R.layout.simple_list_item_1, response);
//
//        restrauntNames.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        //reponse =
//        listReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
//                response = dataSnapshot.getValue(t);
//
//                restrauntNames.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//                for(String s: response){
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

}
