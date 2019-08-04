package com.test.before.interview.Interactor;

import com.test.before.interview.Interactor.Request.DTL;
import com.test.before.interview.Interactor.Response.DataUser;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

    @GET("search/users")
    Observable<DTL<DataUser>> requestUser(@Query("q") String username, @Query("page") int page, @Query("per_page") int perPage);
}
