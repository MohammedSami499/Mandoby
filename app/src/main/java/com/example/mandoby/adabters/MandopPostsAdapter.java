package com.example.mandoby.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mandoby.R;
import com.example.mandoby.model.Post;

import java.util.ArrayList;
import java.util.List;

public class MandopPostsAdapter extends RecyclerView.Adapter<MandopPostsAdapter.PostViewHolder>{

    private List<Post> postsList = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mandop_posts_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MandopPostsAdapter.PostViewHolder holder, int position) {

        holder.post.setText("hi , i have a specific amount of chepsi bacts i'am in "+postsList.get(position).getGovernment()+"government in "+postsList.get(position).getArea());
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

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

          //  productImage = itemView.findViewById(R.id.img_product_picture);
            name = itemView.findViewById(R.id.mandop_posts_name);
            post = itemView.findViewById(R.id.mandop_post_body);
            quantity=itemView.findViewById(R.id.mandop_posts_quantity);
            date=itemView.findViewById(R.id.mandop_posts_date);
        }
    }
}
