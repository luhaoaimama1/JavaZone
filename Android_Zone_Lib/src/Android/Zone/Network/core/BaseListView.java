package Android.Zone.Network.core;

import java.util.List;

import android.view.View;
//一个listView即封装一个T
public abstract class BaseListView<T> {
	public  T listView;
	public static final String LIMIT="limit";
	public static final String OFFSET="offset";
	private  int limit=10;
	private int pageNumber=0;
	public <E> BaseListView(T listView,List<E> data) {
		this.listView=listView;
	}
	public abstract void initListener();
	//这v是判断是那个list 刷新的 把  也可不加把
	public  void loadMore(View v){
		pageNumber++;
	};
	public  void onRefresh(View v){
		 pageNumber=0;
	};
	public abstract void onRefreshComplete();
	public abstract void loadMoreComplete();
	
	public int getPageNumber() {
		return pageNumber;
	}
	public  int getLimit() {
		return limit;
	}
}
