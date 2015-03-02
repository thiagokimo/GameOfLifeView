package io.kimo.examples.gameoflifeview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class BaseActivity extends ActionBarActivity {

    public static final int INVALIDADE_LAYOUT_XML = 0;

    private Toolbar toolbar;

    public abstract Fragment getMainFragment();
    public abstract int getLayoutResource();
    public abstract View getLayoutView();
    public abstract String getActivityTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutResourceCandidate = getLayoutResource();

        if(layoutResourceCandidate == INVALIDADE_LAYOUT_XML) {
            setContentView(getLayoutView());
        } else {
            setContentView(getLayoutResource());
        }

        setContentView(getLayoutResource());
        configureToolbar();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, getMainFragment())
                .commit();
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            setTitle(getActivityTitle());
        }
    }
}
