package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FollowersActivity extends AppCompatActivity implements CallBackMe {

    TextView followerName;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        followerName = (TextView) findViewById(R.id.FollowerName);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        JsonRetriever.RetrieveFromURL(this, url, this);
    }

    @Override
    public void CallThis(String jsonText) {
        try {
            JSONArray json = new JSONArray(jsonText);

            JSONObject myFirstFollower = json.getJSONObject(0);

            followerName.setText(myFirstFollower.getString("login"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
