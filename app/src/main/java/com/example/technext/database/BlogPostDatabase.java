package com.example.technext.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.technext.model.Blog;
import com.example.technext.model.BlogPost;
/**
 * Created by Anik Roy on 4/8/2021.
 */
@Database(entities = {Blog.class}, version = 2, exportSchema = false)
public abstract class BlogPostDatabase extends RoomDatabase {
    public abstract BlogPostDao blogPostDao();

}
