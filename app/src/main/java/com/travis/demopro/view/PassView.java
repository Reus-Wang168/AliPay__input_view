package com.travis.demopro.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.Filter;

/**
 * Created by  on 2017/1/30.
 */

public class PassView extends EditText {
    private String TAG = this.getClass().getName();
    Paint mPaint;
    Paint contentPaint;
    Paint arcPaint;
    private int maxLineSize;
    private  int radius;
    private int mPadding;
    private  boolean isAddText;
    private  int circleRadius;
    //current text length
    private  int textLength;
    private float interpllatorTime;
    private  Animation painAnim;


    public PassView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PassView(Context context) {
        this(context,null);
    }

    public PassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        //mPaint.set
        contentPaint=new Paint();
        contentPaint.setAntiAlias(true);
        contentPaint.setStyle(Paint.Style.FILL);
        contentPaint.setColor(Color.WHITE);

        arcPaint=new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setColor(Color.BLACK);
        arcPaint.setStyle(Paint.Style.FILL);
        radius=dip2px(6);
        circleRadius=dip2px(12);
        maxLineSize=6;
        painAnim=new PainLastAnim();
        painAnim.setDuration(200);






    }
    public  int dip2px(float dpValue) {

        final float scale = this.getContext().getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale +0.5f);

    }

    private int getMaxLength() {
        InputFilter[] filters = getFilters();
        for (InputFilter filter: filters) {
            Class<? extends InputFilter> aClass = filter.getClass();
            if (aClass.getName().equals("com.text.")){

            }


        }

        return 0;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(getText().toString().length()>this.textLength){
            isAddText=true;
        }else {
            isAddText=false;
            invalidate();
        }
        Log.d(TAG,""+isAddText);
        if(textLength<=maxLineSize){
            if(painAnim!=null) {
                clearAnimation();

                startAnimation(painAnim);
            }else {
                Log.d(TAG,"has invalidate");
                invalidate();
            }


        }
        this.textLength=text.length();
        Log.d(TAG,textLength+"???");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectf=new RectF(mPadding,mPadding,getMeasuredWidth()-mPadding,getMeasuredHeight());
        canvas.drawRoundRect(rectf,radius,radius,contentPaint);
        float cx,cy;
        cy=getMeasuredHeight()/2;

        float half=getMeasuredWidth()/maxLineSize/2;
        mPaint.setStrokeWidth(0.5f);
        for (int i = 0; i <maxLineSize ; i++) {
            float x=getMeasuredWidth()/maxLineSize*i;
            canvas.drawLine(x,0,x,getMeasuredHeight(),mPaint);
        }
        for (int i = 0; i <maxLineSize ; i++) {
          //  Log.d(TAG,"this is"+textLength);
            float x = getMeasuredWidth() / maxLineSize * i+half;
            if(isAddText) {
                if(i<textLength-1) {
                    canvas.drawCircle(x, cy, circleRadius, arcPaint);
                }else if(i==textLength-1){
                    Log.d(TAG,"interpllatorTime is "+interpllatorTime);
                     //from  small to large
                    canvas.drawCircle(x,cy,circleRadius*interpllatorTime,arcPaint);
                }
            }else {
                //ondraw 在length 后 执行 因为删除了的时候 长度已经减少
                if(i<textLength) {
                    canvas.drawCircle(x, cy, circleRadius, arcPaint);
                }else if(i==textLength && textLength!=0) {
                    canvas.drawCircle(x, cy, circleRadius * (1 - interpllatorTime), arcPaint);
                }

            }
        }

    }
    class  PainLastAnim extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            PassView.this.interpllatorTime=interpolatedTime;
            //重绘制
            postInvalidate();
        }
    }
    public  interface  inputCallback{
        void enterOver();
    }
}
