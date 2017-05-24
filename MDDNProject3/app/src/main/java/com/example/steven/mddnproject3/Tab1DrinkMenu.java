package com.example.steven.mddnproject3;

/**
 * Created by Steven on 23/05/2017.
 */


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab1DrinkMenu extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1drinkmenu, container, false);
        Button b = (Button) rootView.findViewById(R.id.button);
        b.setOnClickListener(this);
        return rootView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                System.out.println("Sending order to server");
                MainActivity.client.sendOrder("Corona: 1");
                break;
        }
    }

    public void s(View view){
        System.out.println("sendOrder was called in tab1");
        //Client.sendOrder("Corona: 1");
        System.out.println("Message was sent");
    }
}
