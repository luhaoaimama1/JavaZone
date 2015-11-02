package Android.Zone.Abstract_Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 不支持多布局
 * @author Zone
 */
public abstract class CopyOfAdapter_Zone<T> extends BaseAdapter {
	private List<T> data;
	private int layout_id;
	private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局
	private int[] idArray;// 得到一个LayoutInfalter对象用来导入布局
	
	private List<Integer> idList=new ArrayList<Integer>();
	
	/**
	 * @param context
	 * @param data
	 * @param layout_id
	 */
	public CopyOfAdapter_Zone(Context context, List<T> data,int layout_id) {
		this.data = data;
		this.layout_id=layout_id;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder;
		if (convertView == null) {
			//convertView 会重复利用  arg0则不会 所以数据 每次都加载就好了 所以  有关convertView的都是初始化用
			holder = new ViewHolder();
			convertView = mInflater.inflate(layout_id, null);
			//把id 都找出来
			if(!ViewGroup.class.isInstance(convertView)){
				if(convertView.getId()!=-1){
					idList.add(convertView.getId());
				}
			}else{
				if(convertView.getId()!=-1){
					idList.add(convertView.getId());
				}
				isVgComeOn(convertView);	
			}
			
			idArray=new int[idList.size()];
			for (int i = 0; i < idList.size(); i++) {
				idArray[i]=idList.get(i);
			}
			for (int i = 0; i < idArray.length; i++) {
				//给 convertView的每个控件 放入map 中易于取出
				holder.map.put(idArray[i], convertView.findViewById(idArray[i]));
				}
			//给View添加监听 
			convertView.setTag(holder);
		} else {
			holder = ((ViewHolder) convertView.getTag());
		}
		//布局都已经弄好了  就是往view里 填数据   holder里有view 有index 有该组的数据
		T dataIndex = data.get(position);
		setData(holder.map,dataIndex,position);
		return convertView;
	}

	private void isVgComeOn(View convertView) {
		if(ViewGroup.class.isInstance(convertView)){
			ViewGroup vg = (ViewGroup)convertView;
			for (int i = 0; i < vg.getChildCount(); i++) {
				View child = vg.getChildAt(i);
				int cId = child.getId();
				if(cId!=-1){
					//看了源码发现-1 是没有id的说
					idList.add(cId);
				}
				//如果这个view是 viewGroup继续找
				isVgComeOn(child);
			}
			
		}
	}


	/**
	 * @author   仅仅是存视图的
	 * @author Map<String,View> map
	 */
	public  class ViewHolder {
		public	 Map<Integer,View> map=new  HashMap<Integer, View>();
	}

	/**
	 * 
	 * @param viewMap   装载convertView的视图 故从中取出然后赋值即可
	 * @param data     此itemIndex的数据集合 中的item...
	 * @param position		
	 */
	public abstract  void setData(Map<Integer, View> viewMap, T data, int position); //注意这里，只声明了这个方法，但没有具体实现。

}
