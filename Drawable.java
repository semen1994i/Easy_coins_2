package com.example.easy_coins2;

import android.graphics.Canvas;
import android.graphics.Rect;

//Через данный интерфейс реализована отрисовка всего
public interface Drawable {
    void draw(Canvas canvas, Rect rect);
}
