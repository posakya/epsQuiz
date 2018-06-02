package com.creatu.lokesh.epssathi.model_class;

/**
 * Created by lokesh on 4/4/18.
 */

public class InvestorClass {
    private String title;
    private String image;
    private String description;

    private InvestorClass() {
        //empty constructor
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
