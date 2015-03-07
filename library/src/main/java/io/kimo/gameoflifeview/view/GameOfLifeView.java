package io.kimo.gameoflifeview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;

import io.kimo.gameoflifeview.R;
import io.kimo.gameoflifeview.game.Cell;
import io.kimo.gameoflifeview.game.World;

/**
 * Custom surface view that displays each round of the game.
 */
public class GameOfLifeView extends SurfaceView implements Runnable {

    public static final int DEFAULT_PROPORTION = 50;
    public static final int DEFAULT_ALIVE_COLOR = Color.BLACK;
    public static final int DEFAULT_DEAD_COLOR = Color.WHITE;

    private Thread thread;
    private boolean isRunning = false;

    private int columnWidth = 1;
    private int rowHeight = 1;
    private int numberOfColumns = 1;
    private int numberOfRows = 1;

    private World world;

    private int proportion = DEFAULT_PROPORTION;
    private int aliveColor = DEFAULT_ALIVE_COLOR;
    private int deadColor = DEFAULT_DEAD_COLOR;

    public GameOfLifeView(Context context) {
        super(context);
        calculateWorldParams();
    }

    public GameOfLifeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.game_of_life_view, 0, 0);
        ensureCorrectAttributes(a);

        calculateWorldParams();
    }

    public GameOfLifeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.game_of_life_view, defStyle, 0);
        ensureCorrectAttributes(a);

        calculateWorldParams();
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

    public void start() {
        thread = null;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {

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

        int X = (int) (x/ proportion);
        int Y = (int) (y/ proportion);

        while(X >= world.getWidth())
            X--;

        while(Y >= world.getHeight())
            Y--;

        world.revive(X, Y);
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
        invalidate();
    }

    public int getAliveColor() {
        return aliveColor;
    }

    public void setAliveColor(int aliveColor) {
        this.aliveColor = aliveColor;
        invalidate();
    }

    public int getDeadColor() {
        return deadColor;
    }

    public void setDeadColor(int deadColor) {
        this.deadColor = deadColor;
        invalidate();
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    private void calculateWorldParams() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);

        numberOfColumns = point.x / proportion;
        numberOfRows = point.y / proportion;

        columnWidth = point.x / numberOfColumns;
        rowHeight = point.y / numberOfRows;

        world = new World(numberOfColumns, numberOfRows, true);
    }

    private Canvas drawCells(Canvas canvas) {

        for (Cell cell : world.getCells()) {
            Rect r = new Rect();
            Paint p = new Paint();

            if(cell.isAlive) {
                r.set((cell.x * columnWidth)-1, (cell.y * rowHeight)-1,
                        (cell.x * columnWidth + columnWidth)-1, (cell.y * rowHeight + rowHeight)-1);
                p.setColor(aliveColor);
            }
            else {
                r.set((cell.x * columnWidth)-1, (cell.y * rowHeight)-1,
                        (cell.x * columnWidth + columnWidth)-1, (cell.y * rowHeight + rowHeight)-1);
                p.setColor(deadColor);
            }
            canvas.drawRect(r, p);
        }

        return canvas;
    }

    private void ensureCorrectAttributes(TypedArray styles) {

        //ensuring proportion
        int styledProportion = styles.getInt(R.styleable.game_of_life_view_proportion, DEFAULT_PROPORTION);

        if(styledProportion > 0) {
            proportion = styledProportion;
        } else {
            throw new IllegalArgumentException("Proportion must be higher than 0.");
        }

        aliveColor = styles.getColor(R.styleable.game_of_life_view_aliveCellColor, DEFAULT_ALIVE_COLOR);
        deadColor = styles.getColor(R.styleable.game_of_life_view_deadCellColor, DEFAULT_DEAD_COLOR);

        styles.recycle();
    }
}
