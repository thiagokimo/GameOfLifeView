package io.kimo.examples.gameoflifeview.activity;

import android.support.v4.app.Fragment;
import android.view.View;

import io.kimo.examples.gameoflifeview.BaseActivity;

public class DefaultGameOfLifeViewActivity extends BaseActivity {

    @Override
    public Fragment getMainFragment() {
        return null;
    }

    @Override
    public int getLayoutResource() {
        return INVALIDADE_LAYOUT_XML;
    }

    @Override
    public View getLayoutView() {
        return null;
    }

    @Override
    public String getActivityTitle() {
        return "Default Game of Life View";
    }
}
