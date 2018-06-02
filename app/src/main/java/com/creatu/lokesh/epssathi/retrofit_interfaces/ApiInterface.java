package com.creatu.lokesh.epssathi.retrofit_interfaces;

import com.creatu.lokesh.epssathi.model_class.DisclaimerModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by lokesh on 3/17/18.
 */

public interface ApiInterface {

    @POST("api.php")
    Call<List<DisclaimerModelClass>> getDisclaimer();

}
