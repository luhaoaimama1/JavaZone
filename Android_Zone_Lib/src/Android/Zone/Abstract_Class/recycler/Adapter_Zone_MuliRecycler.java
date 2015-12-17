package Android.Zone.Abstract_Class.recycler;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
/**
 * /**
 * 多布局动画 插入是有bug的
 * Created by Zone on 2015/12/16.
 */
public abstract class Adapter_Zone_MuliRecycler<T> extends Adapter<RecyclerHolder_Zone>{
	public List<T> data;
	private int[] layout_ids;
	private Context context;
	private OnItemClickListener listener;
	public Adapter_Zone_MuliRecycler(Context context,List<T> data) {
		this.context = context;
		this.data = data;
		this.layout_ids=setLayoutIDs();
	}
	@Override
	public int getItemCount() {
		return data.size();
	}

	@Override
	public void onBindViewHolder( final RecyclerHolder_Zone holder, int position) {
		holder.itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) 
					listener.onItemClick(holder.itemView, holder.getPosition(),holder);
			}
		});
		
		int pos = position;
		setData(holder.map, data.get(pos), pos, holder);
	}
	/**
	 * 返回布局id
	 */
	@Override
	public int getItemViewType(int position) {
		int temp=getItemViewType_Zone(position, layout_ids);
		return temp;
	}
	/**
	 * 
	 * @param position
	 * @param layoutArr 
	 * @return  返回布局id
	 */
	public abstract int getItemViewType_Zone(int position, int[] layoutArr);
	@Override
	public RecyclerHolder_Zone onCreateViewHolder(ViewGroup parent,  int viewType) {
		getItemViewType(viewType);
		return new RecyclerHolder_Zone(LayoutInflater.from(context).inflate(viewType, parent, false));
	}
	

	
	public void setOnItemClickListener(OnItemClickListener listener){
		this.listener=listener;
	}
	public void removeOnItemClickListener(){
		this.listener=null;
	}
	public interface OnItemClickListener{
		   void onItemClick(View convertView, int position, RecyclerHolder_Zone holder);
	}
	
	
	public abstract  int[] setLayoutIDs();
	/**
	 * holder.getPosition() 在监听中好使 即使是动画添加那种
	 * @param viewMap   装载convertView的视图 故从中取出然后赋值即可
	 * @param data     此itemIndex的数据集合 中的item...
	 * @param position 此位置  在监听 回调那种类型中不试用   因为位置变了 监听中还没变。。。
	 * @param holder holder.getPosition() 在监听中好使 即使是动画添加那种
	 */
	public abstract  void setData(Map<Integer, View> viewMap, T data, int position, RecyclerHolder_Zone holder); //注意这里，只声明了这个方法，但没有具体实现。

}
