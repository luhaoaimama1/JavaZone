package 备忘录;

public class Originator {
	public Memento me;
	public Originator(Memento me) {
		this.me=me;
	}
	public Memento getMe() {
		return me;
	}
	public void setMe(Memento me) {
		this.me = me;
	}
	public void printState(){
		System.out.println(me.state);
	}
}
