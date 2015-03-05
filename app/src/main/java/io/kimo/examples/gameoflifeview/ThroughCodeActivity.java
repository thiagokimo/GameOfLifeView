package io.kimo.examples.gameoflifeview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import io.kimo.gameoflifeview.view.GameOfLifeView;

public class ThroughCodeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameOfLifeView gameOfLifeView = new GameOfLifeView(this);
        setContentView(gameOfLifeView);

        gameOfLifeView.resume();
    }
}
