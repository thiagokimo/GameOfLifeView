package io.kimo.examples.gameoflifeview;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class Utils {

    public static void configureToolbar(ActionBarActivity activity, boolean isChild) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);

        if(toolbar != null) {
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(isChild);
        }
    }
}
