package Android.Zone.Abstract_Class.recycler;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Adapter_Zone_Recycler<T> extends Adapter<MyRecyclerHolder>{
	public List<T> data;
	private int layout_id;
	private Context context;
	public Adapter_Zone_Recycler(Context context,List<T> data,int layout_id) {
		this.context = context;
		this.data = data;
		this.layout_id=layout_id;
	}
	@Override
	public int getItemCount() {
		return data.size();
	}

	@Override
	public void onBindViewHolder( MyRecyclerHolder holder, int arg1) {
		int pos = arg1;
		setData(holder.map, data.get(pos), pos, holder);
	}

	@Override
	public MyRecyclerHolder onCreateViewHolder(ViewGroup parent,  int viewType) {
		return new MyRecyclerHolder(LayoutInflater.from(context).inflate(layout_id, parent, false));
	}
	/**
	 * holder.getPosition() 在监听中好使 即使是动画添加那种
	 * @param viewMap   装载convertView的视图 故从中取出然后赋值即可
	 * @param data     此itemIndex的数据集合 中的item...
	 * @param position 此位置  在监听 回调那种类型中不试用   因为位置变了 监听中还没变。。。
	 * @param holder holder.getPosition() 在监听中好使 即使是动画添加那种
	 */
	public abstract  void setData(Map<Integer, View> viewMap, T data, int position, MyRecyclerHolder holder); //注意这里，只声明了这个方法，但没有具体实现。

}
