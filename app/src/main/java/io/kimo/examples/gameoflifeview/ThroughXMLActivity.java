package io.kimo.examples.gameoflifeview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import io.kimo.gameoflifeview.view.GameOfLifeView;

public class ThroughXMLActivity extends ActionBarActivity {

    private GameOfLifeView gameOfLifeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.with_xml);
        Utils.configureToolbar(this, true);
        setTitle("Through XML");

        gameOfLifeView = (GameOfLifeView) findViewById(R.id.game_of_life);
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
