package com.example.rez.connectiontesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 2017-10-12.
 */

public class RepoListAdapter extends ArrayAdapter<RepoListFeed> {


    public RepoListAdapter(Context context, RepoListFeed[] objects) {
        super(context, R.layout.reposlistfeed, objects);
        //Context is the context we are in... For our example it will be within a listview.
        //Layout: we are going to make a layout with feed in mind.
        //Objects: This will be whatever else we need to pass to it.
    }

    //This fun
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflator = LayoutInflater.from(getContext());  //Instantiates a layout XML file into its corresponding object
        //In our test case it would our ListView object. Context will change where you use.
        //Think of it as prepare data.

        View customFeedView = myInflator.inflate(R.layout.reposlistfeed, parent, false);

        /***Think of the inflator part as in it converts the xml layout into a widget!*****/
        ////In our case, it is adding that feed to the listview




        //Get our widgets
        TextView reponame = (TextView) customFeedView.findViewById(R.id.reponame);
        TextView publicprivate = (TextView) customFeedView.findViewById(R.id.privatepublic);
        TextView desc = (TextView) customFeedView.findViewById(R.id.description);
        TextView lang = (TextView) customFeedView.findViewById(R.id.language);

        //Get our values
        String name = getItem(position).repoName;
        String pub = getItem(position).publicPrivate;
        String des = getItem(position).desc;
        String lan = getItem(position).language;
        //GetItem gets the current item within the array.
             //As that item is a custom object we made.
        //We have access to message and picture.
        //Set our values
        reponame.setText(name);
        publicprivate.setText(pub);
        desc.setText(des);
        lang.setText(lan);


        return customFeedView;  //Sending the view back, in this case as a row.
    }


}
