package Processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


/**
 * 字段信息，用于描述数据库中某一字段
 */
public class ColumnInfo {
	
	private String columnName; //字段名称
	private Class<?> type; //字段类型
	private boolean isID = false; //是否是主键
	private boolean nullable = true; //是否可以为空
	private int length = 32; //字段长度
	private boolean needPersist = false;  //该字段是否需要保存到数据库中
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public boolean isID() {
		return isID;
	}
	public void setID(boolean isID) {
		this.isID = isID;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public boolean isNeedPersist() {
		return needPersist;
	}
	public void setNeedPersist(boolean needPersist) {
		this.needPersist = needPersist;
	}
	
	/**
	 * 根据属性描述对象Field，解析字段信息
	 * 其解析的流程如下：
	 * 1.获取Field对象的名称，作为字段名称
	 * 2.获取Field对象的类型，作为字段的类型
	 * 3.获取该属性上声明的注解集合，并遍历这些注解
	 * 4.如果注解是@Column,则表明该属性应映射成数据库中的字段
	 * 5.如果注解是@ID，则表明该属性作为数据库中表的主键
	 * 6.最后判断该属性是否需要持久化，如需要返回解析后的字段信息对象，否则返回null
	 * @param field
	 * @return
	 */
	public static ColumnInfo parse(Field field){
		ColumnInfo column = new ColumnInfo();
		column.columnName = field.getName();
		column.type = field.getType();
		Annotation[] annotations = field.getAnnotations();
		for(Annotation annotation : annotations){
			if(annotation.annotationType().equals(Column.class)){ //如果注解是@Column
				column.needPersist = true; //设置成需要持久化存储
				Column columnAnno = (Column)annotation; 
				if(!columnAnno.value().equals("")){ //若value不为空，则将字段名设置成为注解value的参数值
					column.columnName = columnAnno.value();
				}
				column.nullable = columnAnno.nullable();
				if(columnAnno.length() != -1){ //若length不为空，则设置字段的长度值
					column.length = columnAnno.length();
				}
			}else if(annotation.annotationType().equals(ID.class)){
				column.needPersist = true;
				ID id = (ID)annotation;
				column.isID = true;
				if(!id.value().equals("")){ //如果用户设置了value值，则以value值作为字段名
					column.columnName = id.value();
				}
			}
		}
		if(column.needPersist){
			return column;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 输出成sql字符串
	 */
	@Override
	public String toString(){
		StringBuilder sql = new StringBuilder(columnName);
		
		if(this.type.equals(String.class)){
			sql.append(Symbol.BLANK + "VARCHAR(" + this.length + ")");	
		}else if(this.type.equals(Integer.class)){
			sql.append(Symbol.BLANK + "INT");
		}
		
		if(this.isID){
			sql.append(Symbol.BLANK + "PRIMARY KEY");
		}
		if(!this.isNullable()){
			sql.append(Symbol.BLANK + "NOT NULL");
		}
		sql.append(";");
		return sql.toString();
	}	
}
