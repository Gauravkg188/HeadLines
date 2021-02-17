package com.kg.Headlines;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kg.Headlines.Api.ApiRetrofit;
import com.kg.Headlines.Api.NewsApiInterface;
import com.kg.Headlines.NewsArticle.Headlines;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {



    private static final String TAG =NewsRepository.class.getSimpleName();
    private NewsApiInterface apiRequest;

    public NewsRepository() {
        apiRequest = ApiRetrofit.getRetrofitInstance().create(NewsApiInterface.class);
    }

    public LiveData<Headlines> getArticles(String country,String query,int pageSize, String key) {
        final MutableLiveData<Headlines> data = new MutableLiveData<>();
        apiRequest.getHeadlines(country,query,pageSize,key)
                .enqueue(new Callback<Headlines>() {


                    @Override
                    public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<Headlines> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<Headlines> getArticles2(String query,String key) {
        final MutableLiveData<Headlines> data = new MutableLiveData<>();
        apiRequest.getSpecificData(query,key)
                .enqueue(new Callback<Headlines>() {


                    @Override
                    public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<Headlines> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }





}
