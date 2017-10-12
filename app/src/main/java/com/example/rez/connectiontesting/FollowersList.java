package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FollowersList extends AppCompatActivity  implements CallBackMe{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_list);


        Intent intent = getIntent();
      String  url = intent.getStringExtra("url");

        JsonRetriever.RetrieveFromURL(this, url, this);
    }
public void backToHome(View V) {
    Intent i = new Intent(this,Homepage.class);
    startActivity(i);
}

    @Override
    public void CallThis(String jsonText) {

        try {
            JSONArray json = new JSONArray(jsonText);
            Feed[] myFeeds = new Feed[json.length()];
            for (int i = 0; i<json.length(); i++) {
                JSONObject myFirstFollower = json.getJSONObject(i);
                myFeeds[i] = new Feed(myFirstFollower.getString("login"), myFirstFollower.getString("avatar_url"));
            }

            ListView feedListView = (ListView)findViewById(R.id.listview);
            CustomFeedAdapter feedAdapter = new CustomFeedAdapter(this, myFeeds);
            feedListView.setAdapter(feedAdapter);   //Set the data for this ListView

            feedListView.setOnItemClickListener(    //Creating these on the fly.
                    new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Feed myFeed = (Feed)parent.getItemAtPosition(position);
                            gotClicked(myFeed);
                        }
                    }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void gotClicked(Feed feed)
    {
        //Toast.makeText(this, feed.message, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Profile.class);
        i.putExtra("url",feed.message);
        startActivity(i);

    }
}
