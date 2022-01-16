package com.example.easy_coins2;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

//Наша точка выхода
public class Exit extends Dot {
    public Exit(Point point, int size) {
        super(size, point, getPaint());
    }

    private static Paint getPaint() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        return paint;
    }
}
