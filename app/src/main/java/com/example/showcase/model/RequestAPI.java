package com.example.showcase.model;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestAPI {
    @GET("/posts")
    Call<List<Post>> listRepos ();
}
