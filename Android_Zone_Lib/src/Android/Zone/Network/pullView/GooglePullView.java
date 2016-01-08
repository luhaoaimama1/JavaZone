package Android.Zone.Network.pullView;
import java.util.List;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import Android.Zone.Network.core.BasePullView;

public abstract class GooglePullView<E, A> extends BasePullView<SwipeRefreshLayout,ListView, BaseAdapter,E, A> {
	boolean loadMoreOk=true;
	public GooglePullView(SwipeRefreshLayout pullView,ListView listView, BaseAdapter adapter,List<E> data) {
		super(pullView, listView,adapter, data);
	}

	@Override
	public void init2Listener( final OnRefresh2LoadMoreListener listener) {
		pullView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				baseNetworkQuest.firstPage();
				listener.onRefresh();
			}
		});
		
		listView.setOnScrollListener(new OnScrollListener() {
			int scrollState;
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				this.scrollState=scrollState;
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if(scrollState==OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
					if((firstVisibleItem+visibleItemCount)==totalItemCount){
						if (loadMoreOk) {
							baseNetworkQuest.nextPage();
							listener.loadMore(firstVisibleItem, visibleItemCount, totalItemCount);
							loadMoreOk=false;
						}
					}
				}
			}
		});
	}

	@Override
	public void onRefreshComplete() {
		pullView.setRefreshing(false);
	}

	@Override
	public void onloadMoreComplete() {
		loadMoreOk=true;
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChanged();
	}

}
