package com.techhousestudio.pyayhistory.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.techhousestudio.pyayhistory.database.ArticleAppDatabase;
import com.techhousestudio.pyayhistory.database.ArticleDao;
import com.techhousestudio.pyayhistory.models.Article;

import java.util.List;
import java.util.concurrent.Executors;

public class ArticleViewModel extends AndroidViewModel {

    private ArticleDao articleDao;
    private LiveData<List<Article>> articleList;


    public ArticleViewModel(@NonNull Application application) {
        super(application);
        ArticleAppDatabase appDatabase = ArticleAppDatabase.getInstance(application);
        articleDao = appDatabase.ArticleDao();

    }

    public LiveData<List<Article>> getArticleList() {
        articleList = articleDao.getAllArticle();
        return articleList;
    }

    public LiveData<List<Article>> getArticleFilterListList(String query) {
        articleList = articleDao.getFilterArticle(query);
        return articleList;
    }


    public void insertArticle(Article a) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                articleDao.insertArticle(a);
            }
        });

    }
}
