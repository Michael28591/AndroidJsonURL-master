package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReposList extends AppCompatActivity  implements CallBackMe{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);


        Intent intent = getIntent();
        String  url = intent.getStringExtra("url");
        JsonRetriever.RetrieveFromURL(this, url, this);
    }

    @Override
    public void CallThis(String jsonText) {

        try {
            JSONArray json = new JSONArray(jsonText);
            RepoListFeed[] myFeeds = new RepoListFeed[json.length()];
            for (int i = 0; i<json.length(); i++) {
                JSONObject myFirstFollower = json.getJSONObject(i);
                myFeeds[i] = new RepoListFeed(myFirstFollower.getString("name"), myFirstFollower.getString("private"),myFirstFollower.getString("description"), myFirstFollower.getString("language"));
            }

            ListView feedListView = (ListView)findViewById(R.id.reposList);
            RepoListAdapter feedAdapter = new RepoListAdapter(this, myFeeds);
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
    public void backToHome(View V) {
        Intent i = new Intent(this,Homepage.class);
        startActivity(i);
    }
    public void gotClicked(Feed feed)
    {
        //Toast.makeText(this, feed.message, Toast.LENGTH_SHORT).show();


    }
}
