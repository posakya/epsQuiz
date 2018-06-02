package com.creatu.lokesh.epssathi.retrofit_interfaces;

import com.creatu.lokesh.epssathi.model_class.NewsModal;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lokesh on 5/18/18.
 */

public interface NewsInterfaceRetrofit {
    @GET("news")
    Call<NewsModal> getNews();
}
