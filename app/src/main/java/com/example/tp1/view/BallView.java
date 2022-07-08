package com.example.tp1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.tp1.R;

public class BallView extends View {

    // Pencil that will allow the picture to be drawn
    private Paint picturePainter = new Paint(Paint.ANTI_ALIAS_FLAG);

    // Bitmap that will be drawn
    private Bitmap ballPicture;

    // Picture postion from left and top of the view component
    private int posTopDpx;
    private int posLeftDpx;

    // Component view constructor with the physical layout
    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // Component view constructor without the physical layout
    public BallView(Context context) {
        super(context);
    }

    public void setPosition(int posTopDpx, int posLeftDpx) {
        this.posTopDpx = posTopDpx - ballPicture.getHeight()/2;
        this.posLeftDpx = posLeftDpx - ballPicture.getWidth()/2;
    }

    public int getPosTopDpx() {
        return posTopDpx + ballPicture.getHeight()/2;
    }

    public int getPosLeftDpx() {
        return posLeftDpx + ballPicture.getWidth()/2;
    }

    // onSizeChanged is called each time the size view changes, here only once because the activity
    // in which the view is displayed has been stacked in protrait mode (see manifest file).
    // onSizeChanged is called before onDraw.
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        // Loading picture into bitmap
        ballPicture = BitmapFactory.decodeResource(getResources(), R.drawable.bille);

        // Getting left and top position for a picture at the center of the view component
        posLeftDpx = (w - ballPicture.getWidth())/2;
        posTopDpx = (h - ballPicture.getHeight())/2;
    }

    // onDraw is called by the system each time the view component is displayed or updated
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Drawing the picture in the middle of the view component
        canvas.drawBitmap(ballPicture, posLeftDpx, posTopDpx, picturePainter);
    }

    // performClick method for a better accessibility
    @Override
    public boolean performClick(){
        super.performClick();

        // The old view redraws with the new view (onDraw method is called)
        invalidate();
        return true;
    }
}
