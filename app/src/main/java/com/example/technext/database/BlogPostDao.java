package com.example.technext.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.technext.model.Blog;
import com.example.technext.model.BlogPost;

import java.util.List;

/**
 * Created by Anik Roy on 4/8/2021.
 */
@Dao
public interface BlogPostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(Blog blog);

    @Query("SELECT * from Blog")
    LiveData<List<Blog>> getBlogPostList();

    @Update
    void updateData(Blog blog);
}
