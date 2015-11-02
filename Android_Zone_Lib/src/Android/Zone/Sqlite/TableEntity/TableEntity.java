package Android.Zone.Sqlite.TableEntity;

import Android.Zone.Sqlite.Annotation.Column;
import Android.Zone.Sqlite.Annotation.Table;

@Table(name = "ZoneTable")
public class TableEntity {
	private String id;
	@Column(column = "tableName")
	private String tableName;
	@Column(column = "columnProperty")
	private String columnProperty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnProperty() {
		return columnProperty;
	}

	public void setColumnProperty(String columnProperty) {
		this.columnProperty = columnProperty;
	}

}
