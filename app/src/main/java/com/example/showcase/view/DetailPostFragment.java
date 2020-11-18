package com.example.showcase.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.showcase.R;
import com.example.showcase.model.Post;


public class DetailPostFragment extends Fragment {
    private Post post;
    private TextView txtTitle, txtBody, txtUserId, txtId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            post = getArguments().getParcelable("post");
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtBody = view.findViewById(R.id.tvBody);
        txtTitle = view.findViewById(R.id.tvTitle);
        txtId = view.findViewById(R.id.tvId);
        txtUserId = view.findViewById(R.id.tvUserId);

        //
        txtTitle.setText(post.getTitle());
        txtId.setText(post.getId() + "");
        txtUserId.setText(post.getUserId() + "");
        txtBody.setText(post.getBody());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_post, container, false);
    }
}