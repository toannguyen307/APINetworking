package com.example.showcase.presenter;


import com.example.showcase.Contact;
import com.example.showcase.model.APIManager;
import com.example.showcase.model.Post;
import com.example.showcase.model.RequestAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements Contact.IPresenter {

    private Contact.IView iView;

    public Presenter(Contact.IView iView) {
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
