package gson学习与反射.entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Top {
	private List<Top_data> data=new ArrayList<Top_data>();
	private String code;

	public List<Top_data> getData() {
		return data;
	}

	public void setData(List<Top_data> data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}