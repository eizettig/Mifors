package com.zettig.mifors.Model.Api;

import com.zettig.mifors.Model.Data.Responce;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;


public interface ApiInterface {
    @GET
    Observable<Responce> getData(@Url String url);
}
