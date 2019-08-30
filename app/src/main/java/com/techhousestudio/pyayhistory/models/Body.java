package com.techhousestudio.pyayhistory.models;

import java.util.List;

public class Body {
    public String name;
    public List<Article> articleList;

    public Body(String name, List<Article> articleList) {
        this.name = name;
        this.articleList = articleList;
    }
}
