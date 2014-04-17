package com.mweber.chainreaction.chainreaction;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * Created by Matt on 4/17/2014.
 */
public class Atom extends ImageView {

    Paint paint = new Paint();

    public Atom(Context context, int x, int y) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(120, 120, 120, 120, paint );

    }

}
