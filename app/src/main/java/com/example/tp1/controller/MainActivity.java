package com.example.tp1.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tp1.R;
import com.example.tp1.view.BallView;

public class MainActivity extends AppCompatActivity {

    // ballView is the view including the ball and the area it can move in
    private BallView ballView;

    // Seek bar to allow the user to choose the smallest distance the ball can browse
    private SeekBar seekBar;

    // Chosen smallest distance the ball can browse
    private TextView tvChosenMinMovement;

    // Scale of the seek bar
    private final int SCALE_SEEK_BAR = 10;

    // onCreate is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting the graphic components
        ballView = findViewById(R.id.id_ballView);
        seekBar = findViewById(R.id.id_seekBar);
        tvChosenMinMovement = findViewById(R.id.id_tvChosenMinMovement);

        // Displaying the current number provided by the seek bar
        tvChosenMinMovement.setText("" + seekBar.getProgress()*SCALE_SEEK_BAR);
    }

    // onResume is called when the activity is ready
    @Override
    protected void onResume() {
        super.onResume();

        // Setting a callback that notifies clients when the progress level has been changed
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // The progress level has changed
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                // Displaying to the user the number chosen
                tvChosenMinMovement.setText("" + i*SCALE_SEEK_BAR);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Setting a touch callback that notifies clients when the ballView has been touched
        ballView.setOnTouchListener(new View.OnTouchListener() {

            // onTouch is called when an motion event on the view has been detected.
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                // Type of the event
                int eventType = event.getActionMasked();

                // A pressed gesture is detected or a change has happened during a press gesture
                if(eventType == MotionEvent.ACTION_DOWN || eventType == MotionEvent.ACTION_MOVE) {
                    int eventPosX = (int) event.getX();
                    int eventPosY = (int) event.getY();
                    int ballPosX = ballView.getPosLeftDpx();
                    int ballPosY = ballView.getPosTopDpx();

                    // Checking the event type
                    switch(eventType) {

                        // A pressed gesture is detected (ACTION_DOWN => press / ACTION_UP =>
                        // release)
                        case MotionEvent.ACTION_DOWN:

                            // The x and y click position are set to the BallView class.
                            ballView.setPosition(eventPosY, eventPosX);
                            ballView.performClick();
                            break;

                        // A change has happened during a press gesture (between ACTION_DOWN and
                        // ACTION_UP)
                        case MotionEvent.ACTION_MOVE:

                            // Getting the number displayed to the user. We do not simply get the number
                            // provided by the seek bar to avoid a desynchronisation with the text displayed
                            // and the smallest movement the ball browses (callback priorities unknown)
                            int minimumMovement = Integer.parseInt(tvChosenMinMovement.getText().toString());

                            // If the pressure is detected distant of at least minimumMovement from the position
                            // of the ballView
                            // using the circle equation (x - h)2 + (y - k)2 = r2 (with (x, y) a point of the
                            // circle, (h, k) the center of the circle and r the circle radius).
                            // TODO Mickael
                            // Here is the condition using abs, seems less complicated even if less clever
                            // if((Math.abs(ballPosX - eventPosX) > minimumMovement) ||(Math.abs(ballPosY - eventPosY) > minimumMovement))
                            if(Math.pow(eventPosX - ballPosX, 2) + Math.pow(eventPosY - ballPosY, 2) > Math.pow(minimumMovement, 2)) {

                                // The x and y click position are set to the BallView class.
                                ballView.setPosition(eventPosY, eventPosX);
                                ballView.performClick();
                            }
                            break;
                    }
                    return true;
                }
                return false;
            }
        });
    }
}