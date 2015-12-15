package Android.Zone.Abstract_Class.refreshData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 添加后 记得在ondestory里摧毁
 * @author Zone
 *
 */
public class DataUpdate_Observable {

	private Map<Class<?>, List<DataUpdate_IObserver>> observersMap = new HashMap<Class<?>, List<DataUpdate_IObserver>>();
	
	public void addIObserver (DataUpdate_IObserver observer,Class<?> cls){
		if(observersMap.get(cls)==null){
			List<DataUpdate_IObserver> observers = new ArrayList<DataUpdate_IObserver>();
			observers.add(observer);
			observersMap.put(cls, observers);
		}else{
			observersMap.get(cls).add(observer);
		}
	}
	
	public void delAllIObserver (){
		observersMap.clear();
	}
	/**
	 * 这个会删除 所有的关于这个类的所有监听
	 * @param observer
	 */
	public void removeIObserver(DataUpdate_IObserver observer){
		for (Entry<Class<?>, List<DataUpdate_IObserver>> item : observersMap.entrySet()) {
			if(item.getValue().contains(observer))
				item.getValue().remove(observer);
			if(item.getValue().size()==0){
				observersMap.remove(item.getKey());
			}
		}
	}
	
	public void notify(Object o,Class<?> cls){
		if(!cls.isInstance(o)){
			throw new IllegalArgumentException("o 不是 cls的一个实例");
		}
		List<DataUpdate_IObserver> list = observersMap.get(cls);
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				DataUpdate_IObserver iObserver = list.get(i);
				iObserver.updateObj(o);
		    }
		}
	}
}
