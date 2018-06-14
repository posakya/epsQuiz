package com.creatu.lokesh.epssathi.model_class;

/**
 * Created by lokesh on 5/17/18.
 */


public class Results
{
    private String title;

    private String slug;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", slug = "+slug+"]";
    }
}

