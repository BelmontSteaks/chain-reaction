package com.mweber.chainreaction.chainreaction;

/**
 * Created by Matt on 4/17/2014.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;

public class DrawView extends ImageView {
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
        setBackgroundColor(0xFF000000);
    }

    @Override
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(120, 120, 120, 120, paint );

    }

}
