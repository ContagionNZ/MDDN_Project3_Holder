package com.example.steven.mddnproject3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new Client();
    }

    public void sendMessage(View view){
        EditText ip = (EditText)findViewById(R.id.enterIP);
        String ipAddress = ip.getText().toString();
        client .connectToServer(ipAddress);
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
    }

}
