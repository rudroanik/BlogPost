package com.example.technext.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.technext.database.BlogPostDao;
import com.example.technext.http.ApiServices;
import com.example.technext.model.Blog;
import com.example.technext.model.BlogPost;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anik Roy on 4/8/2021.
 */
public class BlogPostRepo {
    private final ApiServices apiServices;
    private MutableLiveData<Boolean> saveToDb;
    private BlogPostDao blogPostDao;


    @Inject
    public BlogPostRepo(ApiServices apiServices, MutableLiveData<Boolean> save, BlogPostDao dao) {
        this.apiServices = apiServices;
        saveToDb = save;
        blogPostDao = dao;
    }

    public MutableLiveData<Boolean> saveBlogPost() {

        Call<BlogPost> call = apiServices.getBlogPost();
        call.enqueue(new Callback<BlogPost>() {
            @Override
            public void onResponse(Call<BlogPost> call, Response<BlogPost> response) {
                if (response.isSuccessful()) {
                    BlogPost blogPost = response.body();


                    Completable.fromAction(() -> {
                        for (Blog blog : blogPost.getBlogs()) {
                            blogPostDao.insertData(blog);

                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            saveToDb.postValue(true);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            saveToDb.postValue(false);
                        }
                    });


                } else {
                    saveToDb.postValue(false);

                }
            }

            @Override
            public void onFailure(Call<BlogPost> call, Throwable t) {
                saveToDb.postValue(false);
            }
        });

        return saveToDb;
    }

    public LiveData<List<Blog>> getBlogList() {

        return blogPostDao.getBlogPostList();
    }

    public void addBlogPost(Blog blog) {
        Completable.fromAction(() -> {

            blogPostDao.insertData(blog);


        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                saveToDb.postValue(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                saveToDb.postValue(false);
            }
        });

    }

    public MutableLiveData<Boolean> getSaveResponse(){
        return saveToDb;
    }


    public void updateBlogPost(Blog blog) {
        Completable.fromAction(() -> {

            blogPostDao.updateData(blog);


        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                saveToDb.postValue(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                saveToDb.postValue(false);
            }
        });

    }

}
