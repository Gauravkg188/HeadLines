package com.kg.Headlines;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kg.Headlines.NewsArticle.Headlines;



public class NewsViewModel extends AndroidViewModel {


    private NewsRepository newsRepository,newsRepository2;
    private LiveData<Headlines> data,data2;
    String API_KEY = "API_KEY";

    String country="in";
    String category="general";
    int pageSize=100;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository=new NewsRepository();

    }

    public LiveData<Headlines> getData(String query)
    {
        data=newsRepository.getArticles(country,query,pageSize,API_KEY);
        return data;
    }


    public LiveData<Headlines> getData2(String query)
    {
        data=newsRepository.getArticles2(query,API_KEY);
        return data;
    }
}
