package com.example.bubbledraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class BubbleView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener {
    private Random rand = new Random();
    private ArrayList<Bubble> bubbleList;
    private int size = 50;
    private int delay = 15;
    private Paint myPaint = new Paint();
    private Handler h = new Handler();
    private boolean drawMode = false;

    public BubbleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bubbleList = new ArrayList<>();
        //testBubbles();
        setOnTouchListener(this);
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            for (Bubble b : bubbleList) {
                b.update();
            }
            invalidate();
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        for (Bubble b : bubbleList) {
            b.draw(canvas);
        }
        h.postDelayed(r, delay);
    }

    public void testBubbles() {
        for(int i = 0; i < 100; i++) {
            int x = rand.nextInt(600);
            int y = rand.nextInt(600);
            int s = rand.nextInt(size) + size;
            bubbleList.add(new Bubble(x, y, s));
        }
        invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            int x = (int) motionEvent.getX(i);
            int y = (int) motionEvent.getY(i);
            int s = rand.nextInt(size) + size;

            if (!(x < size || x > getWidth() - size || y < size || y > getHeight() - size)) {
                bubbleList.add(new Bubble(x, y, s));
            }
        }
        return true;
    }

    public void clearCanvas() {
        bubbleList = new ArrayList<>();
        invalidate();
    }

    private class Bubble {
        private int x;
        private int y;
        private int size;
        private int color;
        private int xSpeed, ySpeed;
        private final int MAX_SPEED = 15;

        public Bubble(int newX, int newY, int newSize) {
            x = newX;
            y = newY;
            size = newSize;
            color = Color.argb(rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256));
            xSpeed = rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
            ySpeed = rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
            if (xSpeed == 0 && ySpeed == 0) {
                xSpeed = 1;
                ySpeed = 1;
            }
        }

        public void draw(Canvas canvas) {
            myPaint.setColor(color);
            canvas.drawOval(x - size/2, y - size/2,
                    x + size/2, y + size/2, myPaint);
        }

        public void update() {
            if (!drawMode) {
                x += xSpeed;
                y += ySpeed;
                if (x - size / 2 <= 0 || x + size / 2 >= getWidth()) {
                    xSpeed = -xSpeed;
                }
                if (y - size / 2 <= 0 || y + size / 2 >= getHeight()) {
                    ySpeed = -ySpeed;
                }
            }
        }
    }

    public void setDrawMode(boolean drawMode) {
        this.drawMode = drawMode;
    }
}