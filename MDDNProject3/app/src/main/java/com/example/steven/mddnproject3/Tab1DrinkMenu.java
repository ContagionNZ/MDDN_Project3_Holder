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
        return rootView;

    }

    @Override
    public void onClick(View v) {

    }
}
