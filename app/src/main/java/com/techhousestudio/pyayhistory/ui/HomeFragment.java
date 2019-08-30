package com.techhousestudio.pyayhistory.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techhousestudio.pyayhistory.AppData;
import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.adapters.MasterRecyclerAdapter;
import com.techhousestudio.pyayhistory.database.ArticleAppDatabase;
import com.techhousestudio.pyayhistory.models.Article;
import com.techhousestudio.pyayhistory.viewmodels.ArticleViewModel;

import java.util.List;

import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;
import timber.log.Timber;

public class HomeFragment extends Fragment {

    private ArticleAppDatabase appDatabase;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;


    public HomeFragment() {
    }

    public static HomeFragment newInstance(int columnCount) {
        HomeFragment fragment = new HomeFragment();
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
        RecyclerView articleList = view.findViewById(R.id.master_list);

        articleList.addItemDecoration(new DividerItemDecoration(context, 1));


        if (mColumnCount <= 1) {
            articleList.setLayoutManager(new LinearLayoutManager(context));
        } else {
            articleList.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }


        MasterRecyclerAdapter articleAdapter = new MasterRecyclerAdapter(getContext());
        articleList.setNestedScrollingEnabled(true);
        articleList.setAdapter(articleAdapter);

        ArticleViewModel articleViewModel = ViewModelProviders.of(requireActivity()).get(ArticleViewModel.class);

        articleViewModel.getArticleList().observe(requireActivity(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {

//                articleAdapter.setObjectList(articles);
            }
        });
        articleAdapter.setObjectList(AppData.getMasterData());
//        articleList.setItemAnimator(new ScaleInBottomAnimator());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.i("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("onDestroy");
    }
}
