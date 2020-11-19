package com.example.showcase.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showcase.Contract;
import com.example.showcase.R;
import com.example.showcase.adapter.OnItemListener;
import com.example.showcase.adapter.PostAdapter;
import com.example.showcase.model.Post;
import com.example.showcase.presenter.PostPresenter;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment implements OnItemListener, Contract.IView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerViewPost;
    private List<Post> postLists = new ArrayList<>();
    private PostPresenter postPresenter;
    private PostAdapter adapter;
    private ProgressDialog progress;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (postPresenter == null) {
            postPresenter = new PostPresenter(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_700, R.color.custom);
        swipeRefreshLayout.setOnRefreshListener(this);
        //swipeRefreshLayout.setRefreshing(true);

        recyclerViewPost = view.findViewById(R.id.rvPost);
        recyclerViewPost.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //   layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewPost.addItemDecoration(itemDecoration);
        recyclerViewPost.setLayoutManager(layoutManager);
        adapter = new PostAdapter(postLists, this);
        recyclerViewPost.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        postPresenter.getData();
    }

    @Override
    public void itemClick(Post post) {
        DetailPostFragment detailPostFragment = new DetailPostFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("post", post);
        detailPostFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailPostFragment, DetailPostFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showLoading() {
        progress = new ProgressDialog(getContext());
        progress.setMessage("Please wait...");
        progress.setCancelable(true);
        progress.show();
    }

    @Override
    public void showError(Exception e) {
        customDialog();
    }

    @Override
    public void showHideLoading() {
        swipeRefreshLayout.setRefreshing(false);
        if(progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    public void showListData(List<Post> list) {
        postLists.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public void customDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Please Check Internet Connection")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
        builder.show();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                postPresenter.getData();
            }
        }, 1000);
    }
}