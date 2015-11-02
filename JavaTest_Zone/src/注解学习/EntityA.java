package 注解学习;

@Table(name="a")
public class EntityA {
	//TODO 是否就需要一个newName呢？  既然有注解了 实体类就可以随便改名了 只要注解不改即可
	//默认长度注解    为100吧  可以设置默认长度
	@TestA(newName="kebi" ,orderName="aa")
	public String name;
	@TestA(newName="kebi2222" ,orderName="a22a")
	public String nameb;

	public String abc;

}
