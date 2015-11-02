package Android.Zone.Sqlite.Inner;

public class UpdateColumn{
	public String  column_old;//注解
	public String column_target;//注解  和这个新名字对比
	public String targetLength;
	public UpdateColumn() {
	}
	public UpdateColumn(String  column_old,String  column_target,String  targetLength) {
		this.column_old=column_old;
		this.column_target=column_target;
		this.targetLength=targetLength;
	}
}