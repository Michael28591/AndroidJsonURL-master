package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
    public void sendUserName(View v) {
        EditText sendusername = (EditText) findViewById(R.id.getusername);
        String usrname=sendusername.getText().toString();
        Intent i = new Intent(this,Profile.class);
        i.putExtra("url",usrname);
        startActivity(i);
    }
}
