package io.kimo.gameoflifeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;

import io.kimo.gameoflifeview.game.Cell;
import io.kimo.gameoflifeview.game.World;

/**
 * Custom surface view that displays each round of the game.
 */
public class GameOfLifeView extends SurfaceView implements Runnable {

    public static final int PROPORTION = 50;

    private Thread thread;
    private boolean isRunning = false;

    private int columnWidth,rowHeight, numberOfColumns, numberOfRows;
    private Point point;

    private World world;

    public GameOfLifeView(Context context) {
        super(context);
        init();
    }

    public GameOfLifeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameOfLifeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void run() {

        while (isRunning) {
            if (!getHolder().getSurface().isValid())
                continue;

            Canvas canvas = getHolder().lockCanvas();
            world.rotate();
            getHolder().unlockCanvasAndPost(drawCells(canvas));
        }
    }

    public void resume() {
        thread = null;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {

        isRunning = false;
        while (true) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public void reviveCellsAt(float x, float y) {

        int X = (int) (x/PROPORTION);
        int Y = (int) (y/PROPORTION);

        while(X >= world.getWidth())
            X--;

        while(Y >= world.getHeight())
            Y--;

        world.revive(X, Y);
    }

    private void init() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        point = new Point();
        display.getSize(point);

        numberOfColumns = point.x / PROPORTION;
        numberOfRows = point.y / PROPORTION;

        columnWidth = point.x / numberOfColumns;
        rowHeight = point.y / numberOfRows;

        world = new World(numberOfColumns, numberOfRows, true);

        resume();
    }

    private Canvas drawCells(Canvas canvas) {

        for (Cell cell : world.getCells()) {
            Rect r = new Rect();
            Paint p = new Paint();

            if(cell.isAlive) {
                r.set((cell.x * columnWidth)-1, (cell.y * rowHeight)-1,
                        (cell.x * columnWidth + columnWidth)-1, (cell.y * rowHeight + rowHeight)-1);
                p.setColor(Color.BLACK);
            }
            else {
                r.set((cell.x * columnWidth)-1, (cell.y * rowHeight)-1,
                        (cell.x * columnWidth + columnWidth)-1, (cell.y * rowHeight + rowHeight)-1);
                p.setColor(Color.WHITE);
            }
            canvas.drawRect(r, p);
        }

        return canvas;
    }

}
