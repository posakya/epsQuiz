package com.creatu.lokesh.epssathi.retrofit_interfaces;


import com.creatu.lokesh.epssathi.model_class.NewsModal;
import com.creatu.lokesh.epssathi.model_class.Results;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by lokesh on 5/17/18.
 */

public interface NewsInterface
{
    @GET("news")
    Observable<NewsModal> getNews();
}
