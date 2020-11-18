package com.example.showcase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showcase.model.Post;
import com.example.showcase.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> postList;
    private OnItemListener onItemListener;
    public PostAdapter(List<Post> postList, OnItemListener onItemListener) {
        this.postList = postList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_view,parent,false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(postList.get(position).getTitle());
        holder.txtId.setText("ID: "+postList.get(position).getId());
        holder.txtUserid.setText("UserID: "+postList.get(position).getUserId());
        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemListener !=null) {
                    onItemListener.itemClick(postList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtUserid, txtId;
        LinearLayout line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle= itemView.findViewById(R.id.tvTitle);
            txtId= itemView.findViewById(R.id.tvId);
            txtUserid= itemView.findViewById(R.id.tvUserId);
            line= itemView.findViewById(R.id.line);
        }
    }
}
