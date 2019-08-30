package com.techhousestudio.pyayhistory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.models.Article;
import com.techhousestudio.pyayhistory.models.Body;
import com.techhousestudio.pyayhistory.models.Header;
import com.techhousestudio.pyayhistory.models.Master;

import java.util.List;

public class MasterRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Master> masterList;
    private Context context;

    public MasterRecyclerAdapter(Context context) {
        this.context = context;
//        super(diffCallback);
    }

    public void setObjectList(List<Master> masterList) {

        this.masterList = masterList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Master.HEADER:
                View ImagePager = inflater.inflate(R.layout.recycler_header_row, parent, false);
                viewHolder = new HeaderViewHolder(ImagePager);
                break;
            case Master.BODY:
                View view = inflater.inflate(R.layout.recycler_body_row, parent, false);
                viewHolder = new BodyViewHolder(view);
                break;
            case Master.FOOTER:
                View footerView = inflater.inflate(R.layout.recycler_body_row, parent, false);
                viewHolder = new BodyViewHolder(footerView);
                break;
            default:
                viewHolder = null;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (masterList != null) {
            switch (holder.getItemViewType()) {
                case Master.BODY:

                    BodyViewHolder bodyViewHolder = (BodyViewHolder) holder;
                    Body bodyList = (Body) masterList.get(position).object;
                    List<Article> articles = bodyList.articleList;

                    bodyViewHolder.tvTitle.setText(bodyList.name);
                    bodyViewHolder.tvTitle.setSelected(true);
                    bodyViewHolder.articleList.setLayoutManager(new LinearLayoutManager(context));
                    ArticleRecyclerAdapter articleRecyclerAdapter = new ArticleRecyclerAdapter(Article.LINEARLAYOUT);
                    bodyViewHolder.articleList.setAdapter(articleRecyclerAdapter);
                    articleRecyclerAdapter.submitList(articles);
                    break;
                case Master.HEADER:
                    Header headerList = (Header) masterList.get(position).object;


                    HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

                    headerViewHolder.tvTitle.setText(headerList.name);
                    headerViewHolder.tvTitle.setSelected(true);
                    ImageAdapter imageAdapter = new ImageAdapter(context, headerList.educationList);
                    headerViewHolder.tabs.setViewPager(headerViewHolder.imageViewPager);
                    headerViewHolder.imageViewPager.setAdapter(imageAdapter);

                    break;
                case Master.FOOTER:
                    BodyViewHolder footerViewHolder = (BodyViewHolder) holder;
                    Body footer = (Body) masterList.get(position).object;
                    List<Article> footerArticles = footer.articleList;

                    footerViewHolder.tvTitle.setText(footer.name);
                    footerViewHolder.tvTitle.setSelected(true);
                    footerViewHolder.articleList.setLayoutManager(new GridLayoutManager(context,2));
                    ArticleRecyclerAdapter footerArticleRecyclerAdapter = new ArticleRecyclerAdapter(Article.GRIDLAYOUT);
                    footerViewHolder.articleList.setAdapter(footerArticleRecyclerAdapter);
                    footerArticleRecyclerAdapter.submitList(footerArticles);
                    break;
            }
        }

    }

    @Override
    public int getItemViewType(int position) {

        switch (masterList.get(position).type) {
            case Master.HEADER:
                return Master.HEADER;
            case Master.BODY:
                return Master.BODY;
            case Master.FOOTER:
                return Master.FOOTER;
        }


        return -1;
    }

    @Override
    public int getItemCount() {
        if (masterList == null) {
            return 0;
        }
        return masterList.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        RecyclerView articleList;

        BodyViewHolder(View view) {
            super(view);
            articleList = view.findViewById(R.id.article_list);
            tvTitle = view.findViewById(R.id.tvTitle);
        }


    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        ViewPager imageViewPager;
        SpringDotsIndicator tabs;
        TextView tvTitle;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPager = itemView.findViewById(R.id.viewPager);
            tabs = itemView.findViewById(R.id.dots_indicator);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

//    private static DiffUtil.ItemCallback<Article> diffCallback = new DiffUtil.ItemCallback<Article>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
//            return oldItem.id == newItem.id;
//        }
//
//        @SuppressLint("DiffUtilEquals")
//        @Override
//        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
//            return oldItem.equals(newItem);
//        }
//    };
}
