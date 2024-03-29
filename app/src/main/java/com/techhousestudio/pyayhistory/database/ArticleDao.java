package com.techhousestudio.pyayhistory.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.techhousestudio.pyayhistory.models.Article;

import java.util.List;

@Dao
public interface ArticleDao {
    @Query("Select * from Article")
    LiveData<List<Article>> getAllArticle();

    @Query("Select * from Article where title LIKE '%' || :query || '%'")
    LiveData<List<Article>> getFilterArticle(String query);

    @Insert
    void insertArticles(List<Article> articles);

    @Insert
    void insertArticle(Article article);
}
