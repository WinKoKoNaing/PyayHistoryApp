package com.techhousestudio.pyayhistory.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.techhousestudio.pyayhistory.models.Article;

import java.util.concurrent.Executors;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ArticleAppDatabase extends RoomDatabase {
    public abstract ArticleDao ArticleDao();

    private static ArticleAppDatabase INSTANCE;

    public static ArticleAppDatabase getInstance(Context application) {
        if (INSTANCE == null) {
            synchronized (ArticleAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application.getApplicationContext(),
                            ArticleAppDatabase.class, "pyay-history")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            ArticleAppDatabase
                                                    .getInstance(application).ArticleDao()
                                                    .insertArticles(Article.articles);
                                        }
                                    });

                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
