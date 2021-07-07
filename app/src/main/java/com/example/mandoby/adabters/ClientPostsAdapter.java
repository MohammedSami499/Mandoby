package com.example.mandoby.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mandoby.R;
import com.example.mandoby.model.Post;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClientPostsAdapter extends RecyclerView.Adapter<ClientPostsAdapter.PostViewHolder>{

    private List<Post> postsList = new ArrayList<>();
    @NonNull
    @NotNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ClientPostsAdapter.PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.client_posts_list,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ClientPostsAdapter.PostViewHolder holder, int position) {
        holder.post.setText("hi , i need a specific amount of chepsi bacts i'am in "+postsList.get(position).getGovernment()+"government in "+postsList.get(position).getArea());
        //holder.productImage.setImageResource(post.prodImage);
        holder.quantity.setText(postsList.get(position).getAmount());
        holder.name.setText(postsList.get(position).getName());
        holder.date.setText(postsList.get(position).getDate());
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

        //  ShapeableImageView productImage;
        TextView post,quantity ,name,date;

        public PostViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //  productImage = itemView.findViewById(R.id.img_product_picture);
            name = itemView.findViewById(R.id.client_posts_name);
            post = itemView.findViewById(R.id.client_post_body);
            quantity=itemView.findViewById(R.id.client_posts_quantity);
            date=itemView.findViewById(R.id.client_posts_date);
        }
    }
}
