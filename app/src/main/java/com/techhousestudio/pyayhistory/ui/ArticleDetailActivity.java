package com.techhousestudio.pyayhistory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.models.Article;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleDetailActivity extends AppCompatActivity {

    private ImageView ivArticleImage;
    private TextView tvTitle, tvContent, tvDateTime;
    private Toolbar toolbar;
    private Article article;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        article = getIntent().getExtras().getParcelable("article");

        initViews();

        bindData(article);
    }

    private void bindData(Article article) {
//        tvTitle.setText(article.title);
        tvTitle.setVisibility(View.GONE);
        getSupportActionBar().setTitle(article.title);
        tvContent.setText(article.content);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        tvDateTime.setText(dateFormat.format(new Date()) + " in " + article.category);

        Glide.with(this).load(article.imageUri).placeholder(R.drawable.pyay).into(ivArticleImage);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivArticleImage = findViewById(R.id.ivArticleImage);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        tvDateTime = findViewById(R.id.tvDateTime);

    }
}
