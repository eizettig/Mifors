package com.zettig.mifors.Model.Api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;



public class ApiModule {

    private ApiModule(){
    }
    public static ApiInterface getApiInterface(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://google.com")
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
