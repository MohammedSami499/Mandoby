package com.example.mandoby.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mandoby.R;
import com.example.mandoby.model.Post;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MandopPostsAdapter extends RecyclerView.Adapter<MandopPostsAdapter.PostViewHolder>{


    private List<Post> postsList = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MandopPostsAdapter.PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.client_posts_list,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MandopPostsAdapter.PostViewHolder holder, int position) {




        holder.post.setText("hi , i have a specific amount of "
                +postsList.get(position).getProductName()
                +" i'am in "
                +postsList.get(position).getGovernment()
                + " government in "
                +postsList.get(position).getArea());

        holder.quantity.setText(""+postsList.get(position).getAmount() + " "+postsList.get(position).getUnit());
        holder.name.setText(postsList.get(position).getName());
        holder.date.setText(postsList.get(position).getDate());
        holder.quantity.setText(""+postsList.get(position).getAmount() + " "+postsList.get(position).getUnit());
        Glide.with(holder.itemView).load(postsList.get(position).getImageurl()).into(holder.productImage);
    }
    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void setPostsList (List<Post> postsList){
        this.postsList = postsList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView post,quantity ,name,date ;
        Spinner unit;

        public PostViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.img_product_picture);
            name = itemView.findViewById(R.id.client_posts_name);
            post = itemView.findViewById(R.id.client_post_body);
            quantity=itemView.findViewById(R.id.client_posts_quantity);
            date=itemView.findViewById(R.id.client_posts_date);
            unit=itemView.findViewById(R.id.spinner_type);
        }
    }
}
