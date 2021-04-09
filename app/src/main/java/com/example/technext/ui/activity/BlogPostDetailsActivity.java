package com.example.technext.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.technext.R;
import com.example.technext.databinding.ActivityBlogPostDetailsBinding;
import com.example.technext.model.Author;
import com.example.technext.model.Blog;

import java.util.Locale;

public class BlogPostDetailsActivity extends AppCompatActivity {

    ActivityBlogPostDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_blog_post_details);

       Blog blog = getIntent().getExtras().getParcelable("Blog");
       Author author = getIntent().getExtras().getParcelable("author");

        Glide.with(this)
                .load(blog.getCoverPhoto())
                .placeholder(R.drawable.ic_baseline_collections_24)
                .into(binding.coverPhoto);
        Glide.with(this)
                .load(author.getAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.profilePicture);

        binding.authorName.setText(author.getName());
        binding.authorProfession.setText(author.getProfession());
        binding.title.setText(blog.getTitle());
        binding.description.setText(blog.getDescription());

        if (blog.getCategories().size()>0){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < blog.getCategories().size(); i++) {
                stringBuilder.append(blog.getCategories().get(i));
                stringBuilder.append(",");
            }
            String commaSeparatedList = stringBuilder.substring(0, stringBuilder.length() - 1);
            binding.category.setText(commaSeparatedList);
        }else {
            binding.category.setVisibility(View.GONE);
        }

        binding.fabEdit.setOnClickListener(v -> {
            startActivity(new Intent(BlogPostDetailsActivity.this,EditActivity.class).putExtra("Blog",blog).putExtra("author",author));
            finish();
        });

    }
}