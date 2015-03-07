package io.kimo.examples.gameoflifeview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import io.kimo.gameoflifeview.view.GameOfLifeView;

public class ThroughCodeActivity extends ActionBarActivity {

    private GameOfLifeView gameOfLifeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameOfLifeView = new GameOfLifeView(this);
        setContentView(gameOfLifeView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameOfLifeView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameOfLifeView.stop();
    }
}
