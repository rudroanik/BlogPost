package com.example.technext.di;

import androidx.lifecycle.MutableLiveData;

import com.example.technext.http.ApiServices;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anik Roy on 4/8/2021.
 */
@InstallIn(ApplicationComponent.class)
@Module
public class NetworkModule {
    @Provides
    public String provideBaseUrl(){

        return "https://my-json-server.typicode.com/";
    }

    @Provides
    public Converter.Factory provideConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, Converter.Factory converterFactory){

        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(converterFactory).build();
    }

    @Provides
    public ApiServices provideApiInterface(Retrofit retro){
        return retro.create(ApiServices.class);
    }

    @Provides
    public MutableLiveData<Boolean> provideSave(){
        return new MutableLiveData<>();
    }


}
