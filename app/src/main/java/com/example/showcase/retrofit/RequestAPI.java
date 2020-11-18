package com.example.showcase.retrofit;

import com.example.showcase.model.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestAPI {
    @GET("/posts")
    Call<List<Post>> listRepos ();
}
