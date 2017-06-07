package com.example.steven.matkarta;

/**
 * Created by Steven on 23/05/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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
    DatabaseReference order1;
    HashMap<String, HashMap<String,String>> response = new HashMap<String, HashMap<String, String>>();
    HashMap<String, String> drinks = new HashMap<String, String>();
    ArrayList<String>drinkList;
    ScrollView scrollView;
    LinearLayout drinkLayout;
    Context context;
    ArrayList<TextView> counters = new ArrayList<TextView>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1drinks, container, false);
        context = this.getContext();
        scrollView = (ScrollView) rootView.findViewById(R.id.drinkScroll);
        drinkLayout = (LinearLayout) rootView.findViewById(R.id.drinkLayout);
        drinkLayout.setOrientation(LinearLayout.VERTICAL);
        Intent intent = getActivity().getIntent();
        String restaurantName = intent.getStringExtra("Restaurant_ID");
        System.out.println(restaurantName + "_Menu");
        order1 = mRootRef.child("Test one");
        listReference = mRootRef.child(restaurantName + "_Menu");
        listReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String,HashMap<String,String>>> t = new GenericTypeIndicator<HashMap<String, HashMap<String, String>>>() {};
                response = dataSnapshot.getValue(t);
                drinks = response.get("Drinks");
                drinkLayout.removeAllViews();
                drinkList = new ArrayList<String>(drinks.keySet());
                // this for loop reads in the drink items row by row
                for(String s : drinkList){
                    // create linearLayout which will represent a row
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                    // create the drink name
                    TextView drinkName = new TextView(context);
                    drinkName.setText(s);
                    linearLayout.addView(drinkName);
                    DisplayMetrics dm = getResources().getDisplayMetrics();
                    float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, dm);
                    drinkName.getLayoutParams().width = (int)dpInPx;
                    dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, dm);
                    drinkName.getLayoutParams().height = (int)dpInPx;
                    drinkName.setTextSize(20);
                   // drinkName.setGravity(Gravity.CENTER_VERTICAL);

                    // create the drink price
                    TextView drinkPrice = new TextView(context);
                    drinkPrice.setText(drinks.get(s));
                    drinkPrice.setTextSize(20);
                    linearLayout.addView(drinkPrice);
                    dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, dm);
                    drinkPrice.getLayoutParams().width = (int)dpInPx;
                 //   drinkPrice.setGravity(Gravity.CENTER_VERTICAL);

                    // create the minus button
                    ImageButton minusButton = new ImageButton(context);
                    minusButton.setImageResource(R.drawable.minius_mexico);
                    minusButton.setBackgroundResource(0);
                    linearLayout.addView(minusButton);
                    dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, dm);
                    minusButton.getLayoutParams().width = (int) dpInPx;
                    minusButton.getLayoutParams().height = (int) dpInPx;

                    minusButton.requestLayout();

                    // create the counter
                    final TextView counter = new TextView(context);
                    //counter.setInputType(InputType.TYPE_CLASS_NUMBER);
                    counter.setText("0");
                    counter.setTextSize(20);
                    linearLayout.addView(counter);
                    dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, dm);
                    counter.getLayoutParams().width = (int) dpInPx;
                    counter.setGravity(Gravity.CENTER_HORIZONTAL);
                    counters.add(counter);

                    // create the plus button
                    ImageButton plusButton = new ImageButton(context);
                    plusButton.setImageResource(R.drawable.add_mexico);
                    plusButton.setBackgroundResource(0);
                    linearLayout.addView(plusButton);
                    dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, dm);
                    plusButton.getLayoutParams().width = (int) dpInPx;
                    plusButton.getLayoutParams().height = (int) dpInPx;
                    plusButton.requestLayout();


                    // Set the buttonClickListeners
                    plusButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int current = Integer.parseInt(counter.getText().toString());
                            int increment = current + 1;
                            counter.setText(String.valueOf(increment));
                        }
                    });

                    minusButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int current = Integer.parseInt(counter.getText().toString());
                            int increment = current - 1;
                            if(increment < 0) increment = 0;
                            counter.setText(String.valueOf(increment));
                        }
                    });


                    drinkLayout.addView(linearLayout);
                }

                Button order = (Button)rootView.findViewById(R.id.button2);
                Button clear = (Button)rootView.findViewById(R.id.button3);

                order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < counters.size(); i++){
                            if(!(counters.get(i).getText().equals("0"))) {
                                sb.append(drinkList.get(i) + ": " + counters.get(i).getText() + "\n");
                            }

                        }
                        String order = sb.toString();
                        order1.setValue(order);

                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Clear");
                        System.out.println(counters.size());
                        for(TextView tv : counters){
                            tv.setText("0");
                        }
                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }

}
