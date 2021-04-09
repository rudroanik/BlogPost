package com.example.technext.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.technext.R;
import com.example.technext.databinding.ActivityAddBlogPostBinding;
import com.example.technext.model.Author;
import com.example.technext.model.Blog;
import com.example.technext.viewmodel.BlogPostViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddBlogPost extends AppCompatActivity {

    ActivityAddBlogPostBinding binding;
    List<String> category;
    private BlogPostViewModel blogPostViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_blog_post);
        blogPostViewModel = new ViewModelProvider(AddBlogPost.this).get(BlogPostViewModel.class);

        category = new ArrayList<>();


        binding.business.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (binding.business.isChecked()){
                category.add(binding.business.getText().toString());
            }else {
                category.remove(binding.business.getText().toString());
            }
        });
        binding.lifeStyle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (binding.lifeStyle.isChecked()){
                category.add(binding.lifeStyle.getText().toString());
            }else {
                category.remove(binding.lifeStyle.getText().toString());
            }
        });
        binding.sports.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (binding.sports.isChecked()){
                category.add(binding.sports.getText().toString());
            }else {
                category.remove(binding.sports.getText().toString());
            }
        });
        binding.Productivity.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (binding.Productivity.isChecked()){
                category.add(binding.Productivity.getText().toString());
            }else {
                category.remove(binding.Productivity.getText().toString());
            }
        });

        binding.save.setOnClickListener(v -> {


                Blog blog = new Blog(binding.title.getText().toString(),
                        binding.description.getText().toString(),
                        "",category,new Author(1,"John Doe","https://i.pravatar.cc/250","Content Writer"));

                blogPostViewModel.saveBlogPost(blog);


        });

        blogPostViewModel.getAddResponse().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    finish();
                }
            }
        });
    }
}