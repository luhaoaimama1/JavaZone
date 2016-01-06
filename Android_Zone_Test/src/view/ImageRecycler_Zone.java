package view;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import Android.Zone.Abstract_Class.recyclerAdapter.AdapterRecycler_Zone;
import Android.Zone.Abstract_Class.recyclerAdapter.core.RecyclerHolder_Zone;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class ImageRecycler_Zone extends RecyclerView {

	private Context context;
	private int margin;
	private MarginType marginType;
	private AddType addType;

	public ImageRecycler_Zone(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ImageRecycler_Zone(Context context) {
		this(context, null);
	}

	public ImageRecycler_Zone(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;
	}
	List<String> datas=new ArrayList<String>();
	private void setGrid(AddType addType ,int HCount,int VCount,int margin,MarginType marginType) {
		if(HCount<=0||VCount<=0)
			throw  new IllegalArgumentException(" HCount  must >0 and VCount must >0");
		if(HCount==1){
			setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));	
		}else{
			setLayoutManager(new StaggeredGridLayoutManager(VCount,StaggeredGridLayoutManager.VERTICAL));
		}
		this.margin=margin;
		this.marginType=marginType;
		this.addType=addType;
		for (int i = 0; i < HCount; i++) {
			for (int j = 0; j < VCount; j++) {
				datas.add("");
			}
		}
	}
	private void setGrid(int HCount,int VCount) {
		setGrid(AddType.Hide2Show,HCount, VCount, 0, MarginType.NONE);
	}
	public AdapterRecycler_Zone<String> initAdapter(final int layoutID,final int imageId){
		AdapterRecycler_Zone<String> adapter = new AdapterRecycler_Zone<String>(context, datas) {

			@Override
			public int setLayoutID() {
				return layoutID;
			}

			@Override
			public void setData(RecyclerHolder_Zone holder, String data, int position) {
				View view = holder.itemView;
				switch (marginType) {
				case ALL:
					
					break;
				case HIDE_LEFT2RIGHT:
					
					break;
				case HIDE_ROUND:
					
					break;
				case HIDE_UP2DOWN:
					
					break;
				case NONE:
					break;
				default:
					break;
				}
				ImageView iv=(ImageView) holder.findViewById(imageId);
				ImageLoader.getInstance().displayImage(datas.get(holder.getPosition()), iv);
			}
		};
		setAdapter(adapter);
		return adapter;
	}
	

	public enum MarginType{
		NONE,ALL,HIDE_ROUND,HIDE_LEFT2RIGHT,HIDE_UP2DOWN;
	}
	public enum AddType{
		Add,Hide2Show;
	}

}
