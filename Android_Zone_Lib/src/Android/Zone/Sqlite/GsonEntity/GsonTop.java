package Android.Zone.Sqlite.GsonEntity;

import java.util.ArrayList;
import java.util.List;

public class GsonTop {

	List<GsonColumn> data = new ArrayList<GsonColumn>();

	public List<GsonColumn> getData() {
		return data;
	}

	public void setData(List<GsonColumn> data) {
		this.data = data;
	}

}
