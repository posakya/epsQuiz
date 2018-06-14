package com.creatu.lokesh.epssathi.model_class;

public class EpsDocumentCategoryModelClass {
    private String published_date;
    private String title;
    private String slug;

    public EpsDocumentCategoryModelClass(){
        // empty constructor
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
