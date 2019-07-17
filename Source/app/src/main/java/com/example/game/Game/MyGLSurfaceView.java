package com.example.game.Game;

import static com.example.game.Game.UserSettingsBinder.displayScale;
import static com.example.game.Game.ScreenDetector.xInPoints;
import static com.example.game.Game.ScreenDetector.yInPoints;

import android.content.Context;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;



public class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer renderer;

    //Scale
    float scale=1.0f;

    private ScaleGestureDetector scaleDetector;
    private Context context;
    public MyGLSurfaceView(Context context){
        super(context);
        this.context=context;
        new SoundReproducer(context);

        setEGLContextClientVersion(2);
        renderer=new MyGLRenderer(context);
        setRenderer(renderer);
        scaleDetector=new ScaleGestureDetector(context,new ScaleListener());

    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        scaleDetector.onTouchEvent(event);

        int action = event.getActionMasked();
        int index = event.getActionIndex();



        if (event.getPointerCount() > 1) {

            // Multi  touch event

          final float  xPos =  event.getX(index);
            final float   yPos =  event.getY(index);

            if (event != null) {
                switch (action) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (this.renderer != null) {
                            queueEvent(new Runnable() {
                                @Override
                                public void run() {
                                    new TouchHandler(context,xInPoints(xPos), yInPoints(yPos), false, false);

                                }
                            });

                        }
                        break;
                }
            }
        }
        else {
            // Single touch event
            if(event!=null) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if(this.renderer!=null) {
                            queueEvent(new Runnable() {
                                @Override
                                public void run() {
                                    new TouchHandler(context,xInPoints(event.getX()), yInPoints(event.getY()), true,false);


                                }
                            });
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(this.renderer!=null) {
                            queueEvent(new Runnable() {
                                @Override
                                public void run() {
                                    new TouchHandler(context,xInPoints(event.getX()), yInPoints(event.getY()), true,true);


                                }
                            });
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
                        if(this.renderer!=null)
                        {
                            queueEvent(new Runnable() {
                                @Override
                                public void run() {
                                    new TouchHandler(context,xInPoints(event.getX()),yInPoints(event.getY()),false,false);
                                }
                            });

                        }
                        break;
                }}
            }
            return true;

    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale*=detector.getScaleFactor();
            scale=Math.max(0.1f,Math.min(scale,21.0f));
            displayScale=(-1)*(scale-1);

            return true;
        }
    }





}
