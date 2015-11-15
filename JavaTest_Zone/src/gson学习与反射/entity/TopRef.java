package gson学习与反射.entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TopRef {
	private Top_data top_data;
	private List<Top_data> data=new ArrayList<Top_data>();
	private List<String> dataStr=new ArrayList<String>();
	private Map<String,Top_data> map=new HashMap<String,Top_data>();
	private String code;
	private int intType;
	private float floatType;
	private long longType;
	private Integer integerT;
	private Float floatT;
	private Long longT;

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