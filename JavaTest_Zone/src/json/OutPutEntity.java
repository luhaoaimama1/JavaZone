package json;

import java.util.HashMap;
import java.util.Map;

import json.JsonTest.FieldProperty;

public class OutPutEntity {
	public OutPutEntity(String className) {
		this.className=className;
	}
	public Map<String, FieldProperty> field = new HashMap<String, FieldProperty>();
	public Map<String, String> fieldClassName = new HashMap<String, String>();
	public String className;
}