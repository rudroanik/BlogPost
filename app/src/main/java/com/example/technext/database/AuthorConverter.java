package com.example.technext.database;

import androidx.room.TypeConverter;

import com.example.technext.model.Author;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AuthorConverter {
    @TypeConverter
    public String fromBlogList(Author blogList) {
        if (blogList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Author>() {}.getType();
        String json = gson.toJson(blogList, type);
        return json;
    }

    @TypeConverter
    public Author toBlogList(String blog) {
        if (blog == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Author>() {}.getType();
        Author cartProductList = gson.fromJson(blog, type);
        return cartProductList;
    }
}
