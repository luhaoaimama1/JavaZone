package Android.Zone.Abstract_Class.recyclerAdapter.I;

import Android.Zone.Abstract_Class.recyclerAdapter.core.RecyclerHolder_Zone;
import android.view.View;

public interface OnItemLongClickListener {
	void onItemLongClick(View convertView, int position, RecyclerHolder_Zone holder);
}
