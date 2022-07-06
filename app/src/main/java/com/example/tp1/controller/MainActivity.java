package com.example.tp1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import com.example.tp1.R;
import com.example.tp1.view.BallView;

public class MainActivity extends AppCompatActivity {

    // View component to be displayed
    private BallView ballView;

    // Height of status bar
    private int statusBarHeight;

    // onCreate is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting the height of the status bar
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        else statusBarHeight = 0;

        // Getting the created view ballView
        ballView = findViewById(R.id.id_ballView);
    }

    // onTouchEvent is called when an motion event on the screen has been detected.
    // onTouchEvent is defined in MainActivity and not on the view because MainActivity is the
    // controller.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        // If a pressed gesture is detected (ACTION_DOWN => press / ACTION_UP => release)
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {

            // The x and y click position are set to the BallView class.
            ballView.setPosition((int) (event.getY() - statusBarHeight), (int) event.getX());
            ballView.performClick();
            return true;
        }
        return false;
    }
}