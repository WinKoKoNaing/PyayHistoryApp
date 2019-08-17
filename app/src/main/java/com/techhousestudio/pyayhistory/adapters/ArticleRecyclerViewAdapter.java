package com.techhousestudio.pyayhistory.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.models.Article;
import com.techhousestudio.pyayhistory.ui.ArticleDetailActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder> {

    private final List<Article> articleList;

    public ArticleRecyclerViewAdapter(List<Article> items) {
        articleList = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_article_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.tvContent.setText(article.content);
        holder.tvCategory.setText(article.category);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        holder.tvDateTime.setText(dateFormat.format(article.created_at));

        Glide.with(holder.itemView.getContext())
                .load(article.imageUri)
                .placeholder(R.drawable.pyay)
                .transform(new CenterCrop(), new RoundedCorners(20))
                .into(holder.ivImageUri);


        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), article.title, Toast.LENGTH_SHORT).show();

            Intent goDetail = new Intent(view.getContext(), ArticleDetailActivity.class);

            goDetail.putExtra("article", article);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) view.getContext(),
                                Pair.create(holder.ivImageUri, "article-image"),
                                Pair.create(holder.tvContent, "article-content"),
                                Pair.create(holder.tvDateTime, "article-datetime"));
//
                view.getContext().startActivity(goDetail, options.toBundle());
//                view.getContext().startActivity(goDetail,
//                        ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle());
            } else {
                view.getContext().startActivity(goDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        TextView tvCategory, tvContent, tvDateTime;
        ImageView ivImageUri;


        ViewHolder(View view) {
            super(view);
            mView = view;
            tvCategory = view.findViewById(R.id.tvCategory);
            tvContent = view.findViewById(R.id.tvContent);
            tvDateTime = view.findViewById(R.id.tvDateTime);
            ivImageUri = view.findViewById(R.id.ivArticleImage);
        }


    }
}
