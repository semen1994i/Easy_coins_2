package com.example.easy_coins2;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class labirintView extends View {
    private final GameManager gameManager;

    public labirintView(Context context, GameManager gameManager){
        super(context);
        this.gameManager = gameManager;
        gameManager.setView(this);
    }

    @Override
    protected void onDraw(Canvas canvas){
        gameManager.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gameManager.setScreenSize(w, h);
    }
}
