package 备忘录;

public class Test {
	public static void main(String[] args) {
		Memento me=new Memento();
		me.state="哈哈";
		Memento me2=new Memento();
		me2.state="哈哈2";
		Memento me3=new Memento();
		me3.state="哈哈3";
		
		
		
		Caretaker ca=new Caretaker();
		ca.setMemento(me);
		ca.setMemento(me2);
		ca.setMemento(me3);
	
		//懒点 直接走到第三步
		Originator ori=new Originator(me3);
		System.out.print("当前状态：");
		ori.printState();//当前状态
		
		
		//开始还原
		ori.setMe(ca.restoreStep(ori.getMe(), 1));
		ori.printState();//哈哈2 就成功了~
		
		ori.setMe(ca.yRestoreStep(ori.getMe(), 1));
		ori.printState();//哈哈3 你懂的
		
	}

}
