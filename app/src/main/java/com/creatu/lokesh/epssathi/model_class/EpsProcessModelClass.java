package com.creatu.lokesh.epssathi.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EpsProcessModelClass {

    @SerializedName("results")
    @Expose
    private List<Results> results = null;

    public List<Results> getResults() {
        return results;
    }
}
