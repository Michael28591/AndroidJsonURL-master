package com.example.rez.connectiontesting;

/**
 * Created by admin on 2017-10-12.
 */

public class RepoListFeed {
    public String repoName;
    public String publicPrivate;
    public String desc;
    public String language;

    public RepoListFeed(String repo, String pubivate,String dec, String lanuage)
    {
        repoName = repo;
        publicPrivate = pubivate;
        desc = dec;
        language = lanuage;
    }
}
