package com.framgia.forder.data.source.remote.api.service;

import android.database.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface FOrderApi {

    // TODO: 11/04/2017 remove later
    @GET("/search/users")
    Observable<Void> searchGithubUsers(@Query("per_page") int limit,
            @Query("q") String searchTerm);
}
