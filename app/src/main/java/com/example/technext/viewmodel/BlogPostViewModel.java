package com.example.technext.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.technext.model.Blog;
import com.example.technext.model.BlogPost;
import com.example.technext.repository.BlogPostRepo;

import java.util.List;

/**
 * Created by Anik Roy on 4/8/2021.
 */
public class BlogPostViewModel extends AndroidViewModel {

    private BlogPostRepo blogPostRepo;

    @ViewModelInject
    public BlogPostViewModel(@NonNull Application application,BlogPostRepo blogPostRepo) {
        super(application);
        this.blogPostRepo = blogPostRepo;

    }

    public MutableLiveData<Boolean> getSaveResponse(){
        return blogPostRepo.saveBlogPost();

    }

    public void saveBlogPost(Blog blog){
         blogPostRepo.addBlogPost(blog);
    }

    public LiveData<List<Blog>> getBlogPost(){
        return blogPostRepo.getBlogList();
    }

    public MutableLiveData<Boolean> getAddResponse(){
        return blogPostRepo.getSaveResponse();
    }

    public void updateBlogPost(Blog blog){
        blogPostRepo.updateBlogPost(blog);
    }
}
