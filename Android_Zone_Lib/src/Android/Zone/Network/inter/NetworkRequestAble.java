package Android.Zone.Network.inter;

import java.util.Map;

public interface NetworkRequestAble {
	//map type  post 默认 get  如果有file 默认 是啥自己想被
	//2 执行的时候 是否有dialog   
	public abstract void request(String urlString,Map<String,String> map);
	
	
}
