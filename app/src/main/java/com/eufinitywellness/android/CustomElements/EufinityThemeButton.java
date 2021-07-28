package com.eufinitywellness.android.CustomElements;

//Eufinity Wellness
//Script written by Areeb Rehman on 7/22/21
//Usage: Creates a custom button that can be used throughout the project(specifically the Log In and Sign Up buttons)

//Imports all the necessary resources for the script to run correctly
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

//Imports the resource folder in the project
import com.eufinitywellness.android.R;

//Declares the class and makes it inherit from the superclass View
public class EufinityThemeButton extends View {

    Paint borderPaint, textPaint, fillPaint; //The Paint class holds the style and color information about how to draw geometries, text and bitmaps.
    String buttonText = "ThemeButton"; //Button Text that gets displayed on button

    ValueAnimator onPressAnimator; //This class provides a simple timing engine for running animations which calculate animated values and set them on target objects.

    int padding = 10;

    public EufinityThemeButton(Context context, @Nullable AttributeSet attrs) { //The superclass View requires Context and AttributeSet for a new View
        super(context, attrs); //Feeds the parameters into the superclass
        initializeDrawingVariables(); //Draws the button on the screen

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.EufinityThemeButton,
                0,0);

        try {
            buttonText = a.getString(R.styleable.EufinityThemeButton_buttonText); //Sets the button text to what was sent to the View
        } finally {
            a.recycle(); //Recycles the typed array to prevent packet loss
        }

        setOnTouchListener(new OnTouchListener() { //This is what runs the animation when is detects that the button has been pressed
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) { //Detects when the button is pressed down
                    onPressAnimator.start(); //Plays the animation from the beginning
                } else if (event.getAction() == MotionEvent.ACTION_UP) { //Detects when the button has stopped being pressed
                    onPressAnimator.end(); onPressAnimator.reverse(); //Ends the current animation and then plays the animation in reverse
                    performClick();
                }



                return true;
            }
        });

        //The current details of the animator
        onPressAnimator = ValueAnimator.ofInt(0,100);
        onPressAnimator.setDuration(100);
        onPressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (int) onPressAnimator.getAnimatedValue();
                fillPaint.setColor(Color.argb(val,255,255,255));
//                textPaint.setColor(Color.rgb(255-val,255-val,255-val));
                invalidate();
            }
        });

    }

    private void initializeDrawingVariables() { //Draws the button according to the information given to the superclass
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStrokeWidth(5);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.WHITE);

        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(Color.argb(0,255,255,255));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(getResources().getFont(R.font.cooperhewitt_medium));
        textPaint.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) { //Runs as soon as the button is drawn on the screen and adds padding to the margins
        super.onDraw(canvas);

        canvas.drawRoundRect(padding,padding,getWidth()-padding,getHeight()-padding,getHeight(),getHeight(),fillPaint);
        canvas.drawRoundRect(padding,padding,getWidth()-padding,getHeight()-padding,getHeight(),getHeight(),borderPaint);
        canvas.drawText(buttonText,getWidth()/2, getHeight()/2 + 15,textPaint);

    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) { //Detects of the button has been pressed and sends it to the superclass
        super.setOnClickListener(l);
    }

}
