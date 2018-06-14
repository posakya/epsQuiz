package com.creatu.lokesh.epssathi.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lokesh on 5/17/18.
 */


public class NewsModal
{
    @SerializedName("results")
    @Expose
    private List<Results> results = null;

    public List<Results> getResults() {
        return results;
    }


}