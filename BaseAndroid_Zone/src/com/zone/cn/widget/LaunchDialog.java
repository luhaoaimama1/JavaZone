package com.zone.cn.widget;

import com.zone.cn.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class LaunchDialog extends Dialog {
	private Context context;

	public LaunchDialog(Context context) {
		//设置全屏
		super(context, R.style.Dialog_Fullscreen);
		this.context = context;
	}

	public void showDialog() {
		View v = LayoutInflater.from(context).inflate(R.layout.a_launch, null);
		ImageView iv = (ImageView) v.findViewById(R.id.iv);
		AnimationSet animset = new AnimationSet(true);
		ScaleAnimation sa = new ScaleAnimation(1.5F, 1F, 1.5F, 1F,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa.setDuration(2500);
		animset.setInterpolator(new DecelerateInterpolator(1.2F));
		sa.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				dismiss();
			}

		});
		animset.addAnimation(sa);
		iv.startAnimation(animset);
		
		setContentView(v);
		Window window = getWindow();
		// //设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		// WindowManager.LayoutParams wl = window.getAttributes();
		// wl.x = 0;
		// wl.y = window.getWindowManager().getDefaultDisplay().getHeight();
		// //设置显示位置
		// onWindowAttributesChanged(wl);//设置点击外围解散
		// setCanceledOnTouchOutside(true);
		show();

	}

}
