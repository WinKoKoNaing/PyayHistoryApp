package com.techhousestudio.pyayhistory.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.models.Article;

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
