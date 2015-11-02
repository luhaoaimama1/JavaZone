package view.testIng;

import com.example.mylib_test.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ColorView extends ImageView {
	private Bitmap bt;
	private Bitmap lin;
	private int[] pixels;
	public ColorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ColorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bt=BitmapFactory.decodeResource(context.getResources(), R.drawable.amb);
		lin = bt.copy(Config.RGB_565, true);
		pixels=new int[bt.getWidth()*bt.getHeight()];
	}

	public ColorView(Context context) {
		this(context,null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
//		Paint  p
 //		canvas.drawBitmap(hua, 0, 0,null);
	
		
	}
}
