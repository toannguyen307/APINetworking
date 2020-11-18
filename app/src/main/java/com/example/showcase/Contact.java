package com.example.showcase;

import com.example.showcase.model.Post;

import java.util.List;

public interface Contact {
    interface IPresenter{
        void getData();
    }

    interface IView{
        void showLoading();
        void showError(Exception e);
        void showHideLoading();
        void showListData(List<Post> list);
    }



}
