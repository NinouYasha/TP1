package com.example.tp1.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.example.tp1.R;
import com.example.tp1.view.BallView;

public class MainActivity extends AppCompatActivity {

    // View component to be displayed
    private BallView ballView;

    // Height of MainActivity
    private int mainActivityHeight;

    // onCreate is called when the activity starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This code gets the height of MainActivity
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mainActivityHeight = displayMetrics.heightPixels;

        // Getting the created view ballView
        ballView=findViewById(R.id.id_ballView);
    }

    // onTouchEvent is called when an motion event on the screen has been detected.
    // onTouchEvent is defined in MainActivity and not on the view because MainActivity is the
    // controller.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        // If a pressed gesture is detected
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {

            // The x and y click position are set to the BallView class.
            // event.getY() is the pixels from the top of the activity. The pixels from the top of
            // ballView are needed by ballView.setPosTopDpx.
            ballView.setPosLeftDpx((int) event.getX());
            ballView.setPosTopDpx((int) (event.getY() - (mainActivityHeight - ballView.getHeight())));
            ballView.performClick();
            return true;
        }
        else return false;
    }
}