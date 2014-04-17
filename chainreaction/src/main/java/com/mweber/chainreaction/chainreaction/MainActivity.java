package com.mweber.chainreaction.chainreaction;

import android.app.Activity;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
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
    int level = 5;
    int ballNumber = level * 10;
    List <ImageView> balls = new ArrayList<ImageView>();

    int speedArray[] = new int[ballNumber];
    int locationX[] = new int[ballNumber];
    int locationY[] = new int[ballNumber];
    int imageXArray[] = new int[ballNumber];
    int imageYArray[] = new int[ballNumber];
    int directionX[] = new int[ballNumber];
    int directionY[] = new int[ballNumber];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;

        RelativeLayout canvas = new RelativeLayout(this);

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

            canvas.addView(balls.get(i));

        }

        final int FPS = 40;
        TimerTask updateGame = new UpdateGameTask();
        timer.scheduleAtFixedRate(updateGame, 0, 1000/FPS);



        setContentView(canvas);
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
                    }

                    /*for (int i=0; i < balls.size(); i++){


                        imageXArray[i] += (speedArray[i] * directionXArray[i]);
                        imageYArray[i] += (speedArray[i] * directionYArray[i]);

                        balls.get(i).setX(imageXArray[i]);
                        balls.get(i).setY(imageYArray[i]);

                        if (imageXArray[i] + balls.get(i).getWidth() > screenX || imageXArray[i] < 0){
                            directionXArray[i] = directionXArray[i] * -1;
                        }

                        if (imageYArray[i] + balls.get(i).getHeight() > screenY - (screenY/12) || imageYArray[i] < 0){
                            directionYArray[i] = directionYArray[i] * -1;
                        }
                    }*/
                }
            });

        }

    }

}
