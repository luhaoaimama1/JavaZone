package Android.Zone.Sqlite.Inner;

public class OperateStatueEntity {
	public OperateStatue statue;
	public String name;
	public String length;
	public OperateStatueEntity(OperateStatue statue,String name,String length) {
		this.statue=statue;
		this.name=name;
		this.length=length;
	}
	public OperateStatueEntity() {
	}
	public enum OperateStatue{
		Add,Length;
	}
}
