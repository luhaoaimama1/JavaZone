package Android.Zone.Network.core;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
	public  void gsonParse(String msg){
		boolean resultIsRight=MsgCheck.errorChecked(msg);
		if(!resultIsRight)
			return ;
		Gson g=new Gson();
		try {
			entity=g.fromJson(msg, clazz);
		} catch (JsonSyntaxException e) {
//			e.printStackTrace();
			return ;
		}
	};
	
	public void clearData(){
		data.clear();
	}
	public void addAllData2Notify(){
		if (entity!=null) {
			data.addAll(getData(entity));
			notifyDataSetChanged();
		}
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
