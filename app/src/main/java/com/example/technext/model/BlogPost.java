package com.example.technext.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anik Roy on 4/8/2021.
 */

public class BlogPost {
    private int id;

    @SerializedName("blogs")
    @Expose
    private List<Blog> blogs = null;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", blogs=" + blogs +
                '}';
    }
}
