package Processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;



/**
 * 表信息，对应数据库中的一张表
 */
public class TableInfo {
	private String tableName;  //表的名称
	private Class<?> clazz; //该表对应的实体类型信息类
	private boolean needPersist = false; //是否需要持久化存储
	private Map<String,ColumnInfo> columns = new HashMap<String,ColumnInfo>(); //该表中的所有字段信息

	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public Map<String, ColumnInfo> getColumns() {
		return columns;
	}
	public void setColumns(Map<String, ColumnInfo> columns) {
		this.columns = columns;
	}
	
	public void addColumn(String key,ColumnInfo column){
		this.columns.put(key, column);
	}
	public boolean isNeedPersist() {
		return needPersist;
	}
	public void setNeedPersist(boolean needPersist) {
		this.needPersist = needPersist;
	}
	
	
	public static TableInfo parse(Class<?> clazz){
		TableInfo table = new TableInfo();
		table.clazz = clazz;
		table.tableName = table.clazz.getSimpleName();
		Annotation[] annotations = table.clazz.getAnnotations();
		for(Annotation annotation : annotations){
			if(annotation.annotationType().equals(Entity.class)){ //如果包含@Entity注解，则表明此实体需要持久化存储
				table.needPersist = true; //持久化存储标志，设为true
				Entity entity = (Entity)annotation;
				if(!entity.value().equals("")){
					table.tableName = entity.value();
				}
				break;
			}
		}
		if(table.needPersist){  //如果需要持久化存储，遍历生成字段信息
			Field[] fields = table.clazz.getDeclaredFields();
			for(Field field : fields){
				ColumnInfo column = ColumnInfo.parse(field);
				if(column != null){
					table.columns.put(field.getName(), column);
				}
				
			}
			return table;
		}
		else //不需要持久化存储，则返回null
		{
			return null;
		}
	}
	
	/**
	 * 使用表信息对象生成SQL创建语句
	 */
	@Override
	public String toString(){
		StringBuilder sql = new StringBuilder();
		sql.append(Symbol.LINE);
		sql.append("CREATE TABLE ");
		sql.append(this.tableName + Symbol.BLANK);
		sql.append("(");
		for(ColumnInfo column : this.columns.values()){
			sql.append(Symbol.LINE);
			sql.append(Symbol.TAB);
			sql.append(column.toString());
		}
		sql.append(Symbol.LINE);
		sql.append(");");
		return sql.toString();
	}
}