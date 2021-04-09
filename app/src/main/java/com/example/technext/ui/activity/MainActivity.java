package com.example.technext.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.technext.R;
import com.example.technext.databinding.ActivityMainBinding;
import com.example.technext.model.Blog;
import com.example.technext.ui.adapter.BlogPostAdapter;
import com.example.technext.viewmodel.BlogPostViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private BlogPostViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(BlogPostViewModel.class);

        viewModel.getBlogPost().observe(this, blogs -> {
            if (blogs.size() > 0) {
                initAdapter(blogs);
            } else {
                viewModel.getSaveResponse().observe(this, aBoolean -> {
                    if (aBoolean) {
                        viewModel.getBlogPost().observe(this, this::initAdapter);
                    }
                });
            }
        });

        binding.fab.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,AddBlogPost.class));
        });

    }


    private void initAdapter(List<Blog> blogList) {
        binding.progressbar.setVisibility(View.GONE);
        binding.rv.setVisibility(View.VISIBLE);
        BlogPostAdapter adapter = new BlogPostAdapter(this, blogList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rv.setLayoutManager(mLayoutManager);
        binding.rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}