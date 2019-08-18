package com.techhousestudio.pyayhistory.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.adapters.ArticleRecyclerViewAdapter;
import com.techhousestudio.pyayhistory.adapters.OnListFragmentInteractionListener;
import com.techhousestudio.pyayhistory.database.ArticleAppDatabase;
import com.techhousestudio.pyayhistory.models.Article;

import java.util.List;
import java.util.concurrent.Executors;

public class ArticleListFragment extends Fragment {

    ArticleAppDatabase appDatabase;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;


    public ArticleListFragment() {
    }

    public static ArticleListFragment newInstance(int columnCount) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        appDatabase = ArticleAppDatabase.getInstance(requireActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        RecyclerView articleList = (RecyclerView) view;

        articleList.addItemDecoration(new DividerItemDecoration(context, 1));


        if (mColumnCount <= 1) {
            articleList.setLayoutManager(new LinearLayoutManager(context));
        } else {
            articleList.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }



        ArticleRecyclerViewAdapter articleAdapter = new ArticleRecyclerViewAdapter();
        articleList.setAdapter(articleAdapter);

        LiveData<List<Article>> articles = appDatabase.ArticleDao().getAllArticle();
        articles.observe(requireActivity(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                articleAdapter.setArticleList(articles);
            }
        });


        return view;
    }


}
