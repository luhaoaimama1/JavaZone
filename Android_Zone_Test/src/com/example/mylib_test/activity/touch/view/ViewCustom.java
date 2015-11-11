package com.example.mylib_test.activity.touch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ViewCustom extends View {

	private static final String TAG = "ViewCustom----->";

	public ViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ViewCustom(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ViewCustom(Context context) {
		super(context);
	}

	 @Override
	    public boolean dispatchTouchEvent(MotionEvent event) {
	        switch (event.getAction()) {
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
	        return super.dispatchTouchEvent(event);
	    }
	 long down,move ;
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	    	super.onTouchEvent(event);
	        switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	        	down = System.currentTimeMillis();
	            Log.d(TAG, TAG + "onTouchEvent+ACTION_DOWN");
	            break;
	        case MotionEvent.ACTION_POINTER_DOWN:
	            Log.d(TAG, TAG + "onTouchEvent+ACTION_POINTER_DOWN");
	            break;
	        case MotionEvent.ACTION_POINTER_UP:
	            Log.d(TAG, TAG + "onTouchEvent+ACTION_POINTER_UP");
	            break;
	        case MotionEvent.ACTION_MOVE:
	        	move = System.currentTimeMillis();
	        	if((move-down)>=2000){
	        		System.err.println("woyue");
	        		getParent().requestDisallowInterceptTouchEvent(true);;
	        	}
	            Log.e(TAG, TAG + "onTouchEvent+ACTION_MOVE");
	            break;
	        case MotionEvent.ACTION_UP:
	            Log.d(TAG, TAG + "onTouchEvent+ACTION_UP");
	            break;
	        }
	        return true;
	    }
}
