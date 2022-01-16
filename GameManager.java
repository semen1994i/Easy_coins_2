package com.example.easy_coins2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

//Будет знать все элементы, запускать их отрисовку
public class GameManager extends GestureDetector.SimpleOnGestureListener {

    private final List<Drawable> drawables = new ArrayList<>();
    private View view;
    private Exit exit;
    private Player player;
    private labirint labirint;
    private final Rect rect = new Rect();
    private int count = 0;

    public GameManager() {
        create(5);
    }

    //Добавляем элементы в список
    private void create(int size) {
        drawables.clear();
        labirint = new labirint(size);
        drawables.add(labirint);
        exit = new Exit(labirint.getEnd(), size);
        drawables.add(exit);
        player = new Player(labirint.getStart(), size);
        drawables.add(player);
    }

    //Реакция на действия пользователя
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int diffx, diffy;
        diffx = (int) (e2.getX() - e1.getX());
        diffy = (int) (e2.getY() - e1.getY());
        if ( Math.abs(diffx) > Math.abs(diffy)) {
             diffx = diffx > 0 ? 1 : -1;
             diffy = 0;
        } else {
            diffx = 0;
            diffy = diffy > 0 ? 1 : -1;
        }
        int stepX = player.getX();
        int stepY = player.getY();
        while(labirint.canPlayerGoTo(stepX + diffx, stepY + diffy)) {
            stepX += diffx;
            stepY += diffy;
            if(diffx != 0) {
                //Если перекрёсток
                if(labirint.canPlayerGoTo(stepX, stepY + 1)
                        || labirint.canPlayerGoTo(stepX, stepY - 1)) {
                    break;
                }
            }//Если перекрёсток
            if(diffy != 0) {
                //Если перекрёсток
                if(labirint.canPlayerGoTo(stepX + 1, stepY)
                        || labirint.canPlayerGoTo(stepX - 1, stepY)) {
                    break;
                }
            }
        }
        player.goTo(stepX, stepY);

        //Проверка завершения игры
        if (exit.getPoint().equals(player.getPoint())) {
            count++;
            int MAXCOUNT = 7;
            if(count < MAXCOUNT) {
                create(labirint.getSize() + 3);
            } else {
                congratulation();
            }
        }

        //Сообщаем, что произошли изменения
        view.invalidate();
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    public void congratulation() {
        drawables.clear();
        Drawable drawObj = new Drawable() {
            @Override
            public void draw(Canvas canvas, Rect rect) {
                Paint paint = new Paint();
                paint.setTextSize(80);
                paint.setColor(Color.GREEN);
                canvas.drawText("Вы нашли выход!!!", 70, 200, paint);
            }
        };
        drawables.add(drawObj);
    }

    public void draw(Canvas canvas) {
        for (Drawable drawableItem:
             drawables) {
            drawableItem.draw(canvas, rect);
        }
    }

    public void setView(View view) {
        this.view = view;
    }

    //Отрисовка лабиринта происходит по середине экрана
    public void setScreenSize(int width, int height) {
        int screenSize = Math.min(width, height);
        rect.set(
                (width - screenSize) / 2,
                (height - screenSize) / 2,
                (width + screenSize) / 2,
                (height + screenSize) / 2
        );
    }
}
