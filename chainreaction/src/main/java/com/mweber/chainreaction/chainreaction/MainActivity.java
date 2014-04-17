package com.mweber.chainreaction.chainreaction;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    Timer timer = new Timer();
    int screenX, screenY;
    int level = 3;
    int ballNumber = level * 10;
    List <ImageView> balls = new ArrayList<ImageView>();
    List <DrawView> bangs = new ArrayList<DrawView>();

    int speedArray[] = new int[ballNumber];
    int locationX[] = new int[ballNumber];
    int locationY[] = new int[ballNumber];
    int imageXArray[] = new int[ballNumber];
    int imageYArray[] = new int[ballNumber];
    int directionX[] = new int[ballNumber];
    int directionY[] = new int[ballNumber];
    int clicks = 0;
    DrawView bang;
    Rect intBang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;

        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.test);

        bangs.add(new DrawView(this));
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (clicks < 1){
                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();

                    //Why is this needed?!?!
                    bangs.get(0).setImageResource(R.drawable.ic_launcher);

                    bangs.get(0).setX(x);
                    bangs.get(0).setY(y);

                    intBang = new Rect();
                    intBang.set(x, y, x+96, y+96);

                    clicks++;
                    layout.addView(bang);
                }


                return false;
            }
        });

        Random generator = new Random();

        for (int i=0; i < ballNumber; i++) {

            balls.add(new ImageView(this));
            balls.get(i).setImageResource(R.drawable.ic_launcher);

            speedArray[i] = (generator.nextInt(5)*2) - 5;
            locationX[i] = generator.nextInt(screenX-300) + 1;
            locationY[i] = generator.nextInt(screenY-300) + 1;
            directionX[i] = (generator.nextInt(1)*2)-1;
            directionY[i] = (generator.nextInt(1)*2)-1;

            balls.get(i).setX(locationX[i]);
            balls.get(i).setY(locationY[i]);

            imageXArray[i] = locationX[i];
            imageYArray[i] = locationY[i];

            layout.addView(balls.get(i));

        }

        final int FPS = 40;
        TimerTask updateGame = new UpdateGameTask();
        timer.scheduleAtFixedRate(updateGame, 0, 1000/FPS);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void quit (MenuItem item) {
        System.exit(0);
    }

    class UpdateGameTask extends TimerTask {



        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    bangs.add(new ImageView(this));
                    bangs.get(i).setImageResource(R.drawable.ic_launcher);

                    for (int i=0; i < balls.size(); i++){

                        imageXArray[i] += (speedArray[i] * directionX[i]);
                        imageYArray[i] += (speedArray[i] * directionY[i]);

                        balls.get(i).setX(imageXArray[i]);
                        balls.get(i).setY(imageYArray[i]);

                        if (imageXArray[i] + balls.get(i).getWidth() > screenX
                                || imageXArray[i] < 0){
                            directionX[i] = directionX[i] * -1;
                        }

                        if (imageYArray[i] + balls.get(i).getHeight() > screenY - (screenY/12)
                                || imageYArray[i] < 0){
                            directionY[i] = directionY[i] * -1;
                        }

                        int ballLeftBound = imageXArray[i];
                        int ballRightBound = imageXArray[i] + balls.get(i).getWidth();
                        int ballTopBound = imageYArray[i];
                        int ballBottomBound = imageYArray[i] + balls.get(i).getHeight();

                        Rect thisBall = new Rect();
                        thisBall.set(ballLeftBound, ballTopBound, ballRightBound, ballBottomBound);

                        if(clicks > 0){
                            for (int j=0; j < bangs.size(); j++){
                                intBang.set(bangs.get(j).getX(), bangs.get(j).getY(), )
                                if (thisBall.intersect(intBang)){
                                    balls.get(i).setVisibility(View.GONE);
                                    bangs.add(new DrawView(MainActivity.this));

                                }
                            }

                        }
                    }
                }
            });

        }

    }

}
