package com.creatu.lokesh.epssathi.model_class;

/**
 * Created by lokesh on 3/10/18.
 */

public class HomeModelClass {

    public HomeModelClass(){
        //empty constructor
    }


    private String home_text;
    private String home_title;

    public String getHome_text() {
        return home_text;
    }

    public void setHome_text(String home_text) {
        this.home_text = home_text;
    }

    public String getHome_title() {
        return home_title;
    }

    public void setHome_title(String home_title) {
        this.home_title = home_title;
    }

    public HomeModelClass(String home_text, String home_title) {
        this.home_text = home_text;
        this.home_title = home_title;
    }
}
