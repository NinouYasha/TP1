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

    // Variable to store the minimum movement the ball is allowed to browse in dp
    private int minimumMovement;

    // Scale of the seek bar
    private final int SCALE_SEEK_BAR = 10;
    private final int OFFSET_SEEK_BAR = 1;

    // Value of 1 dp in mm
    private final double ONE_DP_TO_MM = 25.4/160;

    // onCreate is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting the graphic components
        ballView = findViewById(R.id.id_ballView);
        seekBar = findViewById(R.id.id_seekBar);
        tvChosenMinMovement = findViewById(R.id.id_tvChosenMinMovement);

        // Displaying the current number of mm chosen via the seek bar
        tvChosenMinMovement.setText("" + (int) ((seekBar.getProgress()*SCALE_SEEK_BAR + OFFSET_SEEK_BAR)*ONE_DP_TO_MM));

        // Getting the number of dp chosen via the seekbar
        minimumMovement = seekBar.getProgress() + OFFSET_SEEK_BAR;
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

                // Displaying to the user the number chosen converted in mm
                tvChosenMinMovement.setText("" + (int) (i*(SCALE_SEEK_BAR + OFFSET_SEEK_BAR)*ONE_DP_TO_MM));

                // Getting the number of dp chosen via the seekbar
                minimumMovement = seekBar.getProgress()*SCALE_SEEK_BAR + OFFSET_SEEK_BAR;
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
                        return true;

                    // A change has happened during a press gesture (between ACTION_DOWN and
                    // ACTION_UP)
                    case MotionEvent.ACTION_MOVE:

                        // If the pressure is detected distant of at least minimumMovement from the
                        // position of the ballView
                        // Using the circle equation (x - h)2 + (y - k)2 = r2 (with (x, y) a point
                        // of the circle, (h, k) the center of the circle and r the circle radius).
                        if((eventPosX - ballPosX)*(eventPosX - ballPosX) + (eventPosY - ballPosY)*(eventPosY - ballPosY) > (minimumMovement)*(minimumMovement)) {

                            // The x and y click position are set to the BallView class.
                            ballView.setPosition(eventPosY, eventPosX);
                            ballView.performClick();
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}