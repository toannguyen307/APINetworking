package com.example.showcase.presenter;


import com.example.showcase.Contract;
import com.example.showcase.retrofit.APIManager;
import com.example.showcase.model.Post;
import com.example.showcase.retrofit.RequestAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPresenter implements Contract.IPresenter {

    private Contract.IView iView;

    public PostPresenter(Contract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void getData() {
        iView.showLoading();
        RequestAPI requestAPI = APIManager.getInstance().getRequestAPI();
        Call<List<Post>> call = requestAPI.listRepos();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                iView.showListData(response.body());
                iView.showHideLoading();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                iView.showHideLoading();
                iView.showError(new Exception(t));
            }
        });
    }
}
