package com.creatu.lokesh.epssathi.response;

import com.creatu.lokesh.epssathi.constant.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lokesh on 3/17/18.
 */

public class ApiClient {


    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {

        if (retrofit == null){
          retrofit = new Retrofit.Builder().baseUrl(Constant.Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;

    }



}
