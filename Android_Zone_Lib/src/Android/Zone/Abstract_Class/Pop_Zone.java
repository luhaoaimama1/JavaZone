package Android.Zone.Abstract_Class;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public abstract class Pop_Zone extends PopupWindow {
	protected Activity activity;
	protected View mMenuView;
	private int layout;
	private int dismissViewId;
	protected int showAtLocationViewId;
	
	private boolean background_Visibility=true;
	private int backgroud_color=0xb0000000;
	/**
	 * 仅仅调用show()即可  
	 * @param activity  在那个activity 弹出pop
	 * @param layoutId	pop展示的布局id
	 * @param showAtLocationViewId
	 * @param dismissViewId  pop布局中控件id viewrect范围之外 点击 即可dissming
	 */
	public Pop_Zone(Activity activity,int layoutId, int showAtLocationViewId,int dismissViewId) {
		super(activity);
		this.activity = activity;
		this.layout=layoutId;
		this.showAtLocationViewId=showAtLocationViewId;
		this.dismissViewId=dismissViewId;
	}
	/**
	 * 动态的调用initPop(R.layout.alert_dialog, R.id.pop_layout); 
	 * @param layout 加载的自定义布局
	 * @param dismissViewId 在那个控件id rect范围之外 点击 即可dissming
	 */
	private void initPop(int layout, final int dismissViewId) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(layout, null);

		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.FILL_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.FILL_PARENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		if (background_Visibility) {
			// 设置SelectPicPopupWindow弹出窗体动画效果
			//		this.setAnimationStyle(R.style.AnimBottom);
			// 实例化一个ColorDrawable颜色为半透明
			ColorDrawable dw = new ColorDrawable(backgroud_color);
			// 设置SelectPicPopupWindow弹出窗体的背景
			setBackgroundDrawable(dw);
			// 设置layout在PopupWindow中显示的位置
		}else{
			//这样能让 pop 返回键 dimiss
			setBackgroundDrawable(new BitmapDrawable()); 
		}
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					int x = (int) event.getX();
					int y = (int) event.getY();
					View view=mMenuView.findViewById(dismissViewId);
						int left=view.getLeft();
						int right=view.getRight();
						int top=view.getTop();
						int bottom=view.getBottom();
						Rect rect=new Rect(left,top,right,bottom);
						if(!rect.contains(x, y)){
							dismiss();
						}
				}
				return true;
			}
		});

	}
	/**
	 * 这样就制定规范了
	 */
	public void show(){
		initPop(layout,dismissViewId);
		findView(mMenuView);
		initData();
		setListener();
		showPop(showAtLocationViewId);
	}
	/**
	 * 例子：构造器中调用     super下面即可  默认可见性为true
	 * <br>super(activity, layoutId, showAtLocationViewId, dismissViewId);<br>
	 * setBackground_Visibility(false);
	 * @param background_Visibility
	 */
	protected void setBackground_Visibility(boolean background_Visibility) {
		this.background_Visibility=background_Visibility;
	}
	/**
	 * 同链接的一样   设置颜色即可   默认可见性为true
	 * {@link #setBackground_Visibility}
	 * @param backgroud_color
	 */
	protected void setBackgroundColor(int backgroud_color) {
		this.backgroud_color=backgroud_color;
	}
	/**
	 * 通过父类中的mMenuView找到pop内的控件
	 * <br>例如：tv_pop_cancel=(TextView) mMenuView.findViewById(R.id.tv_pop_cancel);
	 * @param mMenuView 
	 */
	protected abstract  void findView(View mMenuView) ;
	protected abstract  void initData() ;
	protected abstract  void setListener() ;
	/**
	 * 例子：this.showAtLocation(context.findViewById(R.id.main), Gravity.BOTTOM	| Gravity.CENTER_HORIZONTAL, 0, 0);
	 * <br>并可以更改pop的其他设置 
	 * <br>切记！！！！！ 更改设置必须在show这个例子之前
	 * @param showAtLocationViewId   例如:R.id.main  就是当前activity里面布局的id
	 */
	protected abstract void showPop(int showAtLocationViewId);

}
