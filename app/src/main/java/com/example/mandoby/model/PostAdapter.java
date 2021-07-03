package com.example.mandoby.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mandoby.R;
import com.google.android.material.imageview.ShapeableImageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{


    Context context;
    ArrayList<Post> postsArrayList;

    public PostAdapter(Context context, ArrayList<Post> postsArrayList) {
        this.context = context;
        this.postsArrayList = postsArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.client_posts_list,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PostAdapter.MyViewHolder holder, int position) {

        Post post = postsArrayList.get(position);
        holder.post.setText(post.name);
        holder.productImage.setImageResource(post.prodImage);
    }

    @Override
    public int getItemCount() {

        return postsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView productImage;
        TextView post;
       /*TextView quantity;
        TextView contactRequest;
*/
        public MyViewHolder(@NonNull @NotNull View itemView, ShapeableImageView productImage , TextView post/*, TextView quantity, TextView contactRequest*/) {
            super(itemView);
            this.productImage = productImage;
            this.post = post;
  /*          this.quantity = quantity;
            this.contactRequest = contactRequest;
    */    }

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.img_product_picture);
            post = itemView.findViewById(R.id.post_head);

        }
    }
}
