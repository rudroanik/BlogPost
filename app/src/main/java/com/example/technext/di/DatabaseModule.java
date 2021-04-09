package com.example.technext.di;

import android.content.Context;

import androidx.room.Room;

import com.example.technext.database.BlogPostDao;
import com.example.technext.database.BlogPostDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

/**
 * Created by Anik Roy on 4/8/2021.
 */
@InstallIn(ApplicationComponent.class)
@Module
public class DatabaseModule {
    @Singleton
    @Provides
    public BlogPostDatabase getDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), BlogPostDatabase.class, "techNext_database")
                .fallbackToDestructiveMigration().build();
    }

    @Provides
    public BlogPostDao getDao(BlogPostDatabase blogPostDatabase){
        return blogPostDatabase.blogPostDao();
    }
}
