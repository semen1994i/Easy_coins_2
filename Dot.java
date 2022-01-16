package com.example.easy_coins2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

//Базовый класс для объектов на карте
public class Dot implements Drawable {
    private final int size;
    protected Point point;
    protected Paint paint;

    public Dot(int size, Point point, Paint paint) {
        this.point = point;
        this.paint = paint;
        this.size = size;
    }

    //Какое место обрабатывается в данный момент
    public Point getPoint() {
        return point;
    }

    //Отрисовка объектов
    @Override
    public void draw(Canvas canvas, Rect rect) {
        float cellSize = (float) (rect.right - rect.left) / size;
        canvas.drawRect(
                rect.left + point.x * cellSize,
                rect.top + point.y * cellSize,
                rect.left + point.x * cellSize + cellSize,
                rect.top + point.y * cellSize + cellSize,
                paint);
    }
}
