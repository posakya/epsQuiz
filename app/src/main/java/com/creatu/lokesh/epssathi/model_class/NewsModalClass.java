package com.creatu.lokesh.epssathi.model_class;

/**
 * Created by lokesh on 5/15/18.
 */

public class NewsModalClass {

    public NewsModalClass()
    {
        // empty constructor
    }

    private String id;
    private String title;
    private String image;
    private String description;
    private String newsdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNewsdate() {
        return newsdate;
    }

    public String setNewsdate(String newsdate) {
        this.newsdate = newsdate;
        return newsdate;
    }
}
