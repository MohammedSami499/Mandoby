package com.example.mandoby.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mandoby.R;

import java.util.ArrayList;
import java.util.List;

public class ClientPostsAdapter extends RecyclerView.Adapter<ClientPostsAdapter.PostViewHolder>{

    private List<Post> postsList = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.client_posts_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClientPostsAdapter.PostViewHolder holder, int position) {

        holder.post.setText("hi , i need a specific amount of chepsi bacts i'am in "+postsList.get(position).getGovernment()+"government in "+postsList.get(position).getArea());
        //holder.productImage.setImageResource(post.prodImage);
        holder.quantity.setText(postsList.get(position).getAmount());
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
        TextView post,quantity;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

          //  productImage = itemView.findViewById(R.id.img_product_picture);
            post = itemView.findViewById(R.id.post_head);
            quantity=itemView.findViewById(R.id.client_posts_quantity);

        }
    }
}
