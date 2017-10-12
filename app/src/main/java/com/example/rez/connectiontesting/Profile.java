package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity implements CallBackMe {

    ImageView profile_photo = null;
    TextView username = null;
    TextView     login = null;
    TextView     bio = null;
    TextView     company = null;
    TextView     blog = null;
    TextView     location = null;
    TextView    email = null;
    TextView   hire = null;
    TextView   repos = null;
    TextView   gists = null;
    TextView  followers = null;
    TextView following = null;

    String followersUrl="";
    String reposUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        login = (TextView) findViewById(R.id.login);
        profile_photo = (ImageView) findViewById(R.id.profile_photo);


        username =  (TextView) findViewById(R.id.username);
        login =  (TextView) findViewById(R.id.login);
        bio =  (TextView) findViewById(R.id.bio);
        company =  (TextView) findViewById(R.id.company);
        blog =  (TextView) findViewById(R.id.blog);
        location =  (TextView) findViewById(R.id.location);
        email =  (TextView) findViewById(R.id.email);
        hire =  (TextView) findViewById(R.id.hire);
        repos =  (TextView) findViewById(R.id.repos);
        gists =  (TextView) findViewById(R.id.gists);
        followers =  (TextView) findViewById(R.id.followers);
        following =  (TextView) findViewById(R.id.following);

        String url = "";
        String giturl = "";
        Intent i=getIntent();
        url=i.getStringExtra("url");
        if(url==null){
            giturl = "https://api.github.com/users/michael28591";
        } else {
            giturl = "https://api.github.com/users/"+url;
        }
        //This will retrieve the string json from the URL requested
        JsonRetriever.RetrieveFromURL(this, giturl, this); //First Param for Context, Last Param for Callback Function
        //First param is required for the library
        //Third param, allows to use any class that implements CallBackMe

    }
    public void backToHome(View V) {
        Intent i = new Intent(this,Homepage.class);
        startActivity(i);
    }

    public void FollowersClicked(View v){

        if(followersUrl==""){
            Toast.makeText(this, "please try again when page loaded", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i=new Intent(this, FollowersList.class);
            i.putExtra("url",followersUrl);
            startActivity(i);
        }

    }

    public void ReposClicked(View v){

        if(followersUrl==""){
            Toast.makeText(this, "please try again when page loaded", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i=new Intent(this, ReposList.class);
            i.putExtra("url",reposUrl);
            startActivity(i);
        }

    }
    @Override
    public void CallThis(String jsonText) {

        //Parse the Json here
        //Good examples: https://stackoverflow.com/questions/8091051/how-to-parse-json-string-in-android

        try {
            JSONObject json = new JSONObject(jsonText);

            //Set Login Text
            login.setText(json.getString("login"));



            username.setText(json.getString("name"));
            company.setText(json.getString("company"));
            blog.setText(json.getString("blog"));
            location.setText(json.getString("location"));
            email.setText(json.getString("email"));
            hire.setText(json.getString("hireable"));
           bio.setText(json.getString("bio"));
          repos.setText(json.getString( "public_repos"));
            gists.setText(json.getString("public_gists"));
           followers.setText(json.getString("followers"));
           following.setText(json.getString("following"));
            //Set Followers URL
            followersUrl = json.getString("followers_url");

            reposUrl = json.getString("repos_url");
            //Set Avatar Image From URL
            new DownloadImageTask(profile_photo)
                    .execute(json.getString("avatar_url"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
