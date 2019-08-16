package com.techhousestudio.pyayhistory.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Article {
    public String id, content;

    public Article(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public static List<Article> articles = Collections.singletonList(
            new Article("0", "Apple")
    );
}
