package Android.Zone.Utils;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentSwitchUtils {
	/**
	 * 有这个的话对应处理会很好把  现在switch 支持字符串匹配
	 */
	private Fragment nowFragment;
	private FragmentActivity frameActivity;
	private int frameId;
	private BackStatue allowBack=BackStatue.NO_BACK;
	private FragmentManager manager;
	
	public enum BackStatue{
		NO_BACK,BACK;
	}
	public Fragment getNowFragment(){
		return nowFragment;
	}
	public List<Fragment> getFragments(){
		if(manager.getFragments()!=null){
			return manager.getFragments();
		}
		return null;
	}

	/**
	 * 
	 * @param frameActivity
	 * @param frameId  被替换的frame
	 * @param allowBack  是否允许回退
	 */
	public FragmentSwitchUtils(FragmentActivity frameActivity, int frameId,BackStatue allowBack) {
		this.frameActivity = frameActivity;
		this.frameId = frameId;
		this.allowBack=allowBack;
	}
	/**
	 * 默认：不允许 回退 
	 * @param frameActivity
	 * @param frameId
	 */
	public FragmentSwitchUtils(FragmentActivity frameActivity, int frameId) {
		this(frameActivity,frameId,BackStatue.NO_BACK);
	}

	/**
	 * 没有动画的
	 * @param fragment
	 */
	public void switchPage(Class<?> fragment) {
		switchPage(fragment, -1, -1);
	}
	/**
	 * <strong>	tran.addToBackStack(null); 是否允许回退到上一个fragment  不写就直接退出activity</strong>
	 * null貌似是tag  未验证
	 * Tag：是 class.getName();
	 * @param fragment
	 * @param ani_in
	 * @param ani_out
	 */
	public void switchPage(Class<?> fragment,int ani_in,int ani_out) {
		if (!Fragment.class.isAssignableFrom(fragment)) {
			throw new IllegalArgumentException("类型不是frament 不能展示");
		}
		manager = frameActivity.getSupportFragmentManager();
		FragmentTransaction tran = manager.beginTransaction();
		if(ani_in!=-1){
			tran.setCustomAnimations(ani_in,ani_out);	
//			tran.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
		}
		String targetName = fragment.getName();
		if (nowFragment != null) {
			String nowName = nowFragment.getClass().getName();
			if (nowName.equals(targetName)) {
				// 还是这个页面
				return;
			}
			Fragment targetFm = null;
			if(manager.getFragments()!=null){
				for (Fragment item : manager.getFragments()) {
					if (item!=null&&targetName.equals(item.getTag())) {
						// 如果有直接控制 显隐
						tran.hide(nowFragment);
						targetFm = manager.findFragmentByTag(targetName);
						tran.show(targetFm);
					}
				}
			}
			if (targetFm == null) {
				// 没有则 生成 然后显示隐藏
				targetFm = Fragment.instantiate(frameActivity, targetName);
				tran.hide(nowFragment);
				tran.add(frameId, targetFm, targetFm.getClass().getName())
						.show(targetFm);
			}
			nowFragment = targetFm;
			
			//第一次不可以回退  即第二次以后 通过allowBack 是否添加回退
			switch (allowBack) {
			case BACK:
				tran.addToBackStack(null);
				break;
			case NO_BACK:
				
				break;
			default:
				break;
			}
		} else {
			// 当第一次的时候 即now 为空的情况
			nowFragment = Fragment.instantiate(frameActivity, targetName);
			tran.add(frameId, nowFragment, nowFragment.getClass().getName())
					.show(nowFragment);
		}
		tran.commit();
	}
}
