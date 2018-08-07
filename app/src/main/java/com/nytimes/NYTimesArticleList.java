package com.nytimes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nytimes.fragment.ArticleDetailFragment;

public class NYTimesArticleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nytimes_article_list);
        init();
    }
    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = getFragmentManager().findFragmentByTag(ArticleDetailFragment.class.getSimpleName());
        if (fragment != null && fragment.equals(ArticleDetailFragment.class.getSimpleName())) {
            fragmentManager.popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

}