package com.techhousestudio.pyayhistory.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.adapters.ArticleRecyclerViewAdapter;
import com.techhousestudio.pyayhistory.viewmodels.ArticleViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;

public class SearchableActivity extends AppCompatActivity {

    // widgets
    @BindView(R.id.searchLayout)
    CoordinatorLayout searchLayout;
    @BindView(R.id.emptyLayout)
    LinearLayout emptyLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.article_list)
    RecyclerView article_list;
    private String query;
    private ArticleRecyclerViewAdapter articleRecyclerViewAdapter;
    private ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        article_list.setLayoutManager(new LinearLayoutManager(this));
        articleRecyclerViewAdapter = new ArticleRecyclerViewAdapter();
        article_list.setAdapter(articleRecyclerViewAdapter);

        article_list.setItemAnimator(new ScaleInTopAnimator());

        queryFilterArticle(query);

        // Animation
        TransitionManager.beginDelayedTransition(searchLayout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setQuery(query, false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryFilterArticle(newText);
                return true;
            }
        });

        return true;
    }

    private void queryFilterArticle(String newText) {
        articleViewModel.getArticleFilterListList(newText).observe(SearchableActivity.this, articles -> {
            if (articles.size()<=0){
                emptyLayout.setVisibility(View.VISIBLE);
                article_list.setVisibility(View.GONE);
            }else {
                emptyLayout.setVisibility(View.GONE);
                article_list.setVisibility(View.VISIBLE);
            }
            articleRecyclerViewAdapter.submitList(articles);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
