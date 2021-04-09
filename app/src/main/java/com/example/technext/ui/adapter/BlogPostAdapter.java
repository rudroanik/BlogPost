package com.example.technext.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.technext.R;
import com.example.technext.databinding.ItemBlogPostBinding;
import com.example.technext.model.Blog;
import com.example.technext.ui.activity.BlogPostDetailsActivity;

import java.util.List;

/**
 * Created by Anik Roy on 4/9/2021.
 */
public class BlogPostAdapter extends RecyclerView.Adapter<BlogPostAdapter.ViewHolder> {

    private Context context;
    private List<Blog> blogPostList;

    public BlogPostAdapter(Context context, List<Blog> blogPostList) {
        this.context = context;
        this.blogPostList = blogPostList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBlogPostBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_blog_post,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Blog blogPost = blogPostList.get(position);

        Glide.with(context)
                .load(blogPost.getCoverPhoto())
                .placeholder(R.drawable.ic_baseline_collections_24)
                .into(holder.binding.coverPhoto);

        holder.binding.title.setText(blogPost.getTitle());

        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(context.getApplicationContext(), BlogPostDetailsActivity.class)
                    .putExtra("Blog",blogPost).
                            putExtra("author",blogPost.getAuthor()));
        });

    }

    @Override
    public int getItemCount() {
        return blogPostList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemBlogPostBinding binding;
        public ViewHolder(ItemBlogPostBinding binding)
        {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
