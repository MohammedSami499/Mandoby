package com.example.mandoby.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mandoby.R;
import com.example.mandoby.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.userName.setText(posts.get(position).getName());
        holder.date.setText(posts.get(position).getDate());
        holder.productType.setText(posts.get(position).getProductType());
        holder.units.setText(posts.get(position).getUnit());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setList(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }


    public class PostHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView date;
        TextView productType;
        TextView units;
        ImageView img_profile;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tv_user_name);
            date = itemView.findViewById(R.id.tv_date);
            productType = itemView.findViewById(R.id.tv_product_type);
            units = itemView.findViewById(R.id.amount);
        }
    }
}
