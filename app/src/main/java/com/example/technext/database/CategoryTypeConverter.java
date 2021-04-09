package com.example.technext.database;

import androidx.room.TypeConverter;
import com.example.technext.model.Blog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CategoryTypeConverter {
    @TypeConverter
    public String fromBlogList(List<String> blogList) {
        if (blogList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        String json = gson.toJson(blogList, type);
        return json;
    }

    @TypeConverter
    public List<String> toBlogList(String blog) {
        if (blog == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> cartProductList = gson.fromJson(blog, type);
        return cartProductList;
    }
}
