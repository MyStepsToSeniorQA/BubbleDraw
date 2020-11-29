package com.example.bubbledraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;




public class BubbleView extends ImageView implements View.OnTouchListener {
    private Random rand = new Random();
    private ArrayList<Bubble> bubbleList;
    private int size = 50;
    private int delay = 33;
    private Paint myPaint = new Paint();
    private Handler h = new Handler();

    public BubbleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bubbleList = new ArrayList<Bubble>();
        testBubbles();

    }

    protected void onDraw(Canvas canvas) {
        for(Bubble b: bubbleList)
            b.draw(canvas);

    }

    public void testBubbles() {
        for(int n = 0 ; n < 100; n++){
            int x = rand.nextInt(600);
            int y = rand.nextInt(600);
            int s = rand.nextInt(size) + size;
            bubbleList.add(new Bubble(x,y,s));
        }
        invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    private class Bubble {
        private int x;
        private int y;
        private int size;
        private int color;
        private int xspeed, yspeed;
        private final int MAX_SPEED = 5;

        public Bubble(int newX, int newY, int newSize) {
            x = (newX / newSize) * newSize + newSize / 2;
            y = (newY / newSize) * newSize + newSize / 2;
            size = newSize;
            color = Color.argb(rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256));
            xspeed = yspeed = 2;

            if (xspeed == 0 & yspeed == 0) xspeed = yspeed = 1;

        }

        public void draw(Canvas canvas) {
            myPaint.setColor(color);
            canvas.drawOval(x - size/2, y - size/2, x + size/2,
                    y + size/2, myPaint);

        }

        public void update() {
            x += xspeed;
            y += yspeed;
            if (x - size / 2 <= 0 || x + size / 2 >= getWidth())
                xspeed = -xspeed;
            if (y - size / 2 <= 0 || y + size / 2 >= getHeight())
                yspeed = -yspeed;
        }
    }
}
