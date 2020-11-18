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

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.showcase.Contact;
import com.example.showcase.R;
import com.example.showcase.adapter.OnItemListener;
import com.example.showcase.adapter.PostAdapter;
import com.example.showcase.model.Post;
import com.example.showcase.presenter.Presenter;
import com.example.showcase.view.DetailPostFragment;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment implements OnItemListener, Contact.IView {

    private RecyclerView recyclerViewPost;
    private List<Post> postLists = new ArrayList<>();
    private Presenter presenter;
    private PostAdapter adapter;
    private ProgressDialog progress;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        presenter = new Presenter(this);
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
        presenter.getData();
    }

    @Override
    public void itemClick(Post post) {

        DetailPostFragment detailPostFragment = new DetailPostFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("post", post);
        detailPostFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, detailPostFragment, detailPostFragment.getClass().getSimpleName())
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
        progress.dismiss();
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
}