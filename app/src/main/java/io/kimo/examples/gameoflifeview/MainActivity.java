package io.kimo.examples.gameoflifeview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import io.kimo.gameoflifeview.view.GameOfLifeView;

public class MainActivity extends ActionBarActivity implements View.OnTouchListener {

    private GameOfLifeView gameOfLiveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameOfLiveView = new GameOfLifeView(this);
        gameOfLiveView.setOnTouchListener(this);
        setContentView(gameOfLiveView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        gameOfLiveView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameOfLiveView.resume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gameOfLiveView.reviveCellsAt(event.getX(), event.getY());
        return true;
    }
}
