package fp.dam.pmdm.examen20230316;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Fragmento1 extends Fragment {

    public Fragmento1() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento1, container, false);
        MySurfaceView surfaceView = new MySurfaceView(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        surfaceView.setLayoutParams(layoutParams);
        ViewGroup surfaceContainer = view.findViewById(R.id.surfaceView);
        surfaceContainer.addView(surfaceView);
        return view;

    }

public class MySurfaceView extends SurfaceView implements Runnable{
    private final int mSquareSide = 57931;
    private final float mBallRadius = 1789.5f;
    private final float mBallSpeed = 17313f;
    private final Paint mPaint;
    private float mBallX = mSquareSide / 2f;
    private float mBallY = mSquareSide / 2f;
    private float mBallVelocityX = mBallSpeed;
    private float mBallVelocityY = mBallSpeed;
    private boolean mIsRunning = false;
    private SurfaceHolder mHolder;
    private int mNumSides = 3;

    private float mRotationAngle = 0f;
    private long mLastUpdateTime = 0L;

    public MySurfaceView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mHolder = getHolder();
        setOnTouchListener((OnTouchListener) this);
    }

    @Override
    public void run() {
        while (mIsRunning) {
            update();
            draw();
        }
    }

    public void resume() {
        mIsRunning = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        mIsRunning = false;
    }

    private void update() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - mLastUpdateTime;
        mLastUpdateTime = currentTime;
        mBallX += mBallVelocityX * (float) elapsedTime / 1000f;
        mBallY += mBallVelocityY * (float) elapsedTime / 1000f;
        if (mBallX + mBallRadius >= mSquareSide || mBallX - mBallRadius <= 0) {
            mBallVelocityX = -mBallVelocityX;
        }
        if (mBallY + mBallRadius >= mSquareSide || mBallY - mBallRadius <= 0) {
            mBallVelocityY = -mBallVelocityY;
        }
        float linearVelocity = (float) Math.sqrt(mBallVelocityX * mBallVelocityX + mBallVelocityY * mBallVelocityY);
        mRotationAngle += linearVelocity / mBallRadius * (float) elapsedTime / 1000f;
    }

    private void draw() {
        if (mHolder.getSurface().isValid()) {
            Canvas canvas = mHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            canvas.save();
            canvas.rotate(mRotationAngle, mBallX, mBallY);
            canvas.drawCircle(mBallX, mBallY, mBallRadius, mPaint);
            canvas.restore();
            mHolder.unlockCanvasAndPost(canvas);
        }
    }
}

}