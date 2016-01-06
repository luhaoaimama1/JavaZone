package Android.Zone.Network.core;

import java.util.List;
import com.google.gson.Gson;
//一个listView即封装一个T
public abstract class BasePullView<T,K,M,E,A> {
	public  T pullView;
	public  M adapter;
	public  List<E> data;
	public  Class<A> clazz;
	private  A entity;
	public K listView;
	public BaseNetworkQuest baseNetworkQuest;
	public  BasePullView(T pullView,K listView,M adapter,List<E> data,Class<A> clazz) {
		this.pullView=pullView;
		this.listView=listView;
		this.adapter=adapter;
		this.clazz=clazz;
		this.data=data;
	}
	public  void relateBaseNetworkQuest(BaseNetworkQuest baseNetworkQuest){
		this.baseNetworkQuest=baseNetworkQuest;
	}
	public  A gsonParse(String msg){
		boolean resultIsRight=MsgCheck.errorChecked(msg);
		if(!resultIsRight)
			return null;
		Gson g=new Gson();
		return 	g.fromJson(msg, clazz);
	};
	
	public void clearData(){
		data.clear();
	}
	public void addAllData2Notify(){
		data.addAll(getData(entity));
		notifyDataSetChanged();
	}
	
	public abstract void init2Listener(OnRefresh2LoadMoreListener listener);
	public abstract void onRefreshComplete();
	public abstract void onloadMoreComplete();
	public abstract void notifyDataSetChanged();
	public abstract List<E> getData(A entity);
	
	public interface OnRefresh2LoadMoreListener{
		public void loadMore(int firstVisibleItem, int visibleItemCount, int totalItemCount) ;
		public void onRefresh() ;
	}
}
