package com.kg.Headlines.room;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private DatabaseArticles databaseArticles;
    private DaoInterface daoInterface;
    private LiveData<List<SavedArticle>> articleList;

    public Repository(Application application)
    {
      databaseArticles=DatabaseArticles.getInstance(application);
      daoInterface=databaseArticles.daoInterface();
      articleList=daoInterface.getAllArticles();
    }

    public void insert(final SavedArticle article)
    {
        DatabaseArticles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
               daoInterface.insert(article);
                Log.d("hii","saved Success");
            }
        });
    }

    public void delete(final SavedArticle article)
    {
        DatabaseArticles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoInterface.delete(article);
            }
        });
    }

    public void deleteAllArticles()
    {
        DatabaseArticles.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoInterface.deleteAllArticles();
            }
        });
    }

    public LiveData<List<SavedArticle>> getArticleList() {
        return articleList;
    }
}
