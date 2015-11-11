package com.example.mylib_test.activity.touch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class LinearCustom extends LinearLayout{

	private static final String TAG = "LinearCustom----->";
	public LinearCustom(Context context) {
		super(context);
	}

	public LinearCustom(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public LinearCustom(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	  @Override
	    public boolean onInterceptTouchEvent(MotionEvent ev) {
	        switch (ev.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            Log.d(TAG, TAG + "onInterceptTouchEvent+ACTION_DOWN");
	            break;
	        case MotionEvent.ACTION_POINTER_DOWN:
	            Log.d(TAG, TAG + "onInterceptTouchEvent+ACTION_POINTER_DOWN");
	            break;
	        case MotionEvent.ACTION_POINTER_UP:
	            Log.d(TAG, TAG + "onInterceptTouchEvent+ACTION_POINTER_UP");
	            break;
	        case MotionEvent.ACTION_MOVE:
	            Log.e(TAG, TAG + "onInterceptTouchEvent+ACTION_MOVE");
	            break;
	        case MotionEvent.ACTION_UP:
	            Log.d(TAG, TAG + "onInterceptTouchEvent+ACTION_UP");
	            break;
	        }
	        return super.onInterceptTouchEvent(ev);
	    }
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("onTouchEvent onInterceptTouchEvent");
		switch (event.getAction()) {
		   case MotionEvent.ACTION_DOWN:
	            Log.d(TAG, TAG + "onTouchEvent+ACTION_DOWN");
	            break;
	        case MotionEvent.ACTION_POINTER_DOWN:
	            Log.d(TAG, TAG + "onTouchEvent+ACTION_POINTER_DOWN");
	            break;
	        case MotionEvent.ACTION_POINTER_UP:
	            Log.d(TAG, TAG + "onTouchEvent+ACTION_POINTER_UP");
	            break;
	        case MotionEvent.ACTION_MOVE:
	            Log.e(TAG, TAG + "onTouchEvent+ACTION_MOVE");
	            break;
	        case MotionEvent.ACTION_UP:
	            Log.d(TAG, TAG + "onTouchEvent+ACTION_UP");
	            break;
		}
		return super.onTouchEvent(event);
	}
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            Log.d(TAG, TAG + "dispatchTouchEvent+ACTION_DOWN");
            break;
        case MotionEvent.ACTION_POINTER_DOWN:
            Log.d(TAG, TAG + "dispatchTouchEvent+ACTION_POINTER_DOWN");
            break;
        case MotionEvent.ACTION_POINTER_UP:
            Log.d(TAG, TAG + "dispatchTouchEvent+ACTION_POINTER_UP");
            break;
        case MotionEvent.ACTION_MOVE:
            Log.e(TAG, TAG + "dispatchTouchEvent+ACTION_MOVE");
            break;
        case MotionEvent.ACTION_UP:
            Log.d(TAG, TAG + "dispatchTouchEvent+ACTION_UP");
            break;
        }
        return super.dispatchTouchEvent(ev);
    }

}
