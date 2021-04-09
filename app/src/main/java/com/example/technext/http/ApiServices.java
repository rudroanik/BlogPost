package com.example.technext.http;

import com.example.technext.model.Blog;
import com.example.technext.model.BlogPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Anik Roy on 4/8/2021.
 */
public interface ApiServices {
    @GET("sohel-cse/simple-blog-api/db")
    Call<BlogPost> getBlogPost();

}
