package Android.Zone.Abstract_Class.recyclerAdapter.core;

import java.util.List;

import Android.Zone.Abstract_Class.recyclerAdapter.I.OnItemClickListener;
import Android.Zone.Abstract_Class.recyclerAdapter.I.OnItemLongClickListener;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
/**
 * Created by Zone on 2015/12/16.
 */
public abstract class BaseRecyclerAdapter<T> extends Adapter<RecyclerHolder_Zone> {
	public List<T> data;
	public Context context;
	private OnItemClickListener clickListener;
	private OnItemLongClickListener longClicklistener;
	public BaseRecyclerAdapter(Context context,List<T> data) {
		this.context = context;
		this.data = data;
	}
	@Override
	public int getItemCount() {
		return data.size();
	}

	@Override
	public void onBindViewHolder(final RecyclerHolder_Zone holder, int position) {
		holder.itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (clickListener != null) 
					clickListener.onItemClick(holder.itemView, holder.getPosition(),holder);
				System.out.println("OnItemClick ok");
			}
		});
		holder.itemView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				if (longClicklistener != null){
					longClicklistener.onItemLongClick(holder.itemView, holder.getPosition(),holder);	
					System.out.println("OnItemLongClick ok");
					return true;
				} 
				return false;
			
			}
		});
		
		int pos = position;
		setData(holder, data.get(pos), pos);
	}
	public void setOnItemClickListener(OnItemClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public void removeOnItemClickListener() {
		this.clickListener = null;
	}

	public void setOnItemLongClickListener(
			OnItemLongClickListener longClicklistener) {
		this.longClicklistener = longClicklistener;
	}

	public void removeOnItemLongClickListener() {
		this.longClicklistener = null;
	}
	/**
	 * holder.getPosition() 在回调监听中好使 判断正确的位置
	 * holder.findviewByid可以找到控件
	 * @param holder holder.getPosition() 在监听中好使 即使是动画添加那种  findviewByid可以找到控件
	 * @param data     此itemIndex的数据集合 中的item...
	 * @param position 此位置  在监听 回调那种类型中不试用   因为位置变了 监听中还没变。。。
	 */
	public abstract  void setData(RecyclerHolder_Zone holder, T data, int position); //注意这里，只声明了这个方法，但没有具体实现。
	
}
