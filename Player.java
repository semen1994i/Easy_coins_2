package com.example.easy_coins2;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Player extends Dot{

    public Player(Point start, int size) {
        super(size, start, getPaint());
    }

    private static Paint getPaint() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        return paint;
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public void goTo(int x, int y) {
        point.x = x;
        point.y = y;
    }
}
