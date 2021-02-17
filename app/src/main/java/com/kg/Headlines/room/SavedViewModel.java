package com.kg.Headlines.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SavedViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<SavedArticle>> articles;

    public SavedViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        articles=repository.getArticleList();

    }

    public void insert(SavedArticle article)
    {
        repository.insert(article);
    }


    public void delete(SavedArticle article){repository.delete(article);}
    public LiveData<List<SavedArticle>> getArticles() {
        return articles;
    }
}
