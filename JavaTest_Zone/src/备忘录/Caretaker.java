package 备忘录;

import java.util.LinkedList;
import java.util.List;

public class Caretaker {
	
	public List<Memento> meList=new LinkedList<Memento>();
	
	public void setMemento(Memento e){
		if(meList.size()>99)
			meList.remove(0);
		meList.add(e);
	}
	//ctrl+z i
	public Memento restoreStep(Memento e,int step){
		int index=meList.indexOf(e);
		return meList.get(index-step);
	}
	//ctrl+Y i
	public Memento yRestoreStep(Memento e,int step){
		int index=meList.indexOf(e);
		if(index+step>99){
			throw new IllegalStateException("step may be error!");
		}
		return meList.get(index+step);
	} 
}
