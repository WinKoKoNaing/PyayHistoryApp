package com.techhousestudio.pyayhistory.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.models.Article;
import com.techhousestudio.pyayhistory.ui.ArticleDetailActivity;

import java.text.SimpleDateFormat;

public class ArticleRecyclerAdapter extends ListAdapter<Article, RecyclerView.ViewHolder> {

    int type = 0;

    public ArticleRecyclerAdapter(int type) {

        super(diffCallback);
        this.type = type;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Article.LINEARLAYOUT:
                return new ArticleLinearViewHolder(inflater.inflate(R.layout.recycler_article_linear_row, parent, false));
            case Article.GRIDLAYOUT:
                return new ArticleGridViewHolder(inflater.inflate(R.layout.recycler_article_grid_row, parent, false));
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        Article article = getItem(position);
        switch (holder.getItemViewType()) {
            case Article.LINEARLAYOUT:
                ArticleLinearViewHolder linearViewHolder = (ArticleLinearViewHolder) holder;
                linearViewHolder.tvContent.setText(article.content);
                linearViewHolder.tvCategory.setText(article.category);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
                linearViewHolder.tvDateTime.setText(dateFormat.format(article.created_at));

                Glide.with(holder.itemView.getContext())
                        .load(article.imageUri)
                        .placeholder(R.drawable.pyay)
                        .transform(new CenterInside(), new RoundedCorners(20))
                        .into(linearViewHolder.ivImageUri);


                holder.itemView.setOnClickListener(view -> {
                    Toast.makeText(view.getContext(), article.title, Toast.LENGTH_SHORT).show();

                    Intent goDetail = new Intent(view.getContext(), ArticleDetailActivity.class);

                    goDetail.putExtra("article", article);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                ActivityOptions options = ActivityOptions
//                        .makeSceneTransitionAnimation((Activity) view.getContext(),
//                                Pair.create(holder.ivImageUri, "article-image"),
//                                Pair.create(holder.tvContent, "article-content"),
//                                Pair.create(holder.tvDateTime, "article-datetime"));

//
                        // single
                        ActivityOptions options = ActivityOptions
                                .makeSceneTransitionAnimation((Activity) view.getContext(), linearViewHolder.ivImageUri, "article-image");
                        view.getContext().startActivity(goDetail, options.toBundle());

//                view.getContext().startActivity(goDetail,
//                        ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle());
                    } else {
                        view.getContext().startActivity(goDetail);
                    }
                });
                break;
            case Article.GRIDLAYOUT:
                ArticleGridViewHolder articleGridViewHolder = (ArticleGridViewHolder) holder;

                Glide.with(holder.itemView.getContext())
                        .load(article.imageUri)
                        .placeholder(R.drawable.pyay)
                        .transform(new CenterInside(), new RoundedCorners(10))
                        .into(articleGridViewHolder.ivImageUri);

                articleGridViewHolder.tvTitle.setText(article.title);

                holder.itemView.setOnClickListener(view -> {
                    Toast.makeText(view.getContext(), article.title, Toast.LENGTH_SHORT).show();

                    Intent goDetail = new Intent(view.getContext(), ArticleDetailActivity.class);

                    goDetail.putExtra("article", article);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                ActivityOptions options = ActivityOptions
//                        .makeSceneTransitionAnimation((Activity) view.getContext(),
//                                Pair.create(holder.ivImageUri, "article-image"),
//                                Pair.create(holder.tvContent, "article-content"),
//                                Pair.create(holder.tvDateTime, "article-datetime"));

//
                        // single
                        ActivityOptions options = ActivityOptions
                                .makeSceneTransitionAnimation((Activity) view.getContext(), articleGridViewHolder.ivImageUri, "article-image");
                        view.getContext().startActivity(goDetail, options.toBundle());

//                view.getContext().startActivity(goDetail,
//                        ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle());
                    } else {
                        view.getContext().startActivity(goDetail);
                    }
                });
                break;
        }

    }

//    @Override
//    public int getItemCount() {
//        if (articleList == null) {
//            return 0;
//        }
//        return articleList.size();
//    }


    @Override
    public int getItemViewType(int position) {
        switch (type) {
            case Article.GRIDLAYOUT:
                return Article.GRIDLAYOUT;
            case Article.LINEARLAYOUT:
                return Article.LINEARLAYOUT;
        }
        return -1;
    }

    class ArticleLinearViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        TextView tvCategory, tvContent, tvDateTime;
        ImageView ivImageUri;


        ArticleLinearViewHolder(View view) {
            super(view);
            mView = view;
            tvCategory = view.findViewById(R.id.tvCategory);
            tvContent = view.findViewById(R.id.tvContent);
            tvDateTime = view.findViewById(R.id.tvDateTime);
            ivImageUri = view.findViewById(R.id.ivArticleImage);
        }


    }

    class ArticleGridViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivImageUri;

        public ArticleGridViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImageUri = itemView.findViewById(R.id.ivArticleImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);

        }
    }


    private static DiffUtil.ItemCallback<Article> diffCallback = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.id == newItem.id;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.equals(newItem);
        }
    };
}