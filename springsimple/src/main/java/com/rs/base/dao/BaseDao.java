package com.rs.base.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


public class BaseDao<T> {

	/** 实现类日志对象 */
	protected final Logger log = Logger.getLogger(getClass());

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	/** 设置一些操作的常量 */
	protected static final String SQL_INSERT = "insert";
	protected static final String SQL_UPDATE = "update";
	protected static final String SQL_DELETE = "delete";

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	protected BaseDao() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<T>) type.getActualTypeArguments()[0];
		System.out.println("Dao实现类是：" + entityClass.getName());
	}

	protected int saveEntity(T entity) {

		final String innersql = this.makeSql(SQL_INSERT);
		final Object[] innerO = this.setArgs(entity, SQL_INSERT);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		System.out.println(innersql);

		/*
		 * System.out.println("插入方案记录,获取当前projectid"); int
		 * projectid=jdbcTemplate.update(sql);
		 */
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = jdbcTemplate
							.getDataSource()
							.getConnection()
							.prepareStatement(innersql,
									Statement.RETURN_GENERATED_KEYS);
					for (int i = 0; i < innerO.length; i++) {
						ps.setObject(i + 1, innerO[i]);
					}

					return ps;
				}
			}, keyHolder);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		System.out.println("自动插入id============================"
				+ keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	protected void save(T entity) {
		String sql = this.makeSql(SQL_INSERT);
		Object[] args = this.setArgs(entity, SQL_INSERT);
//		int[] argTypes = this.setArgTypes(entity, SQL_INSERT);
		jdbcTemplate.update(sql.toString(), args);
	}

	protected int update(T entity) {
		String sql = this.makeSql(SQL_UPDATE);
		Object[] args = this.setArgs(entity, SQL_UPDATE);
//		int[] argTypes = this.setArgTypes(entity, SQL_UPDATE);
		int exeIn = jdbcTemplate.update(sql, args);
		return exeIn;
	}

	protected void delete(T entity) {
		String sql = this.makeSql(SQL_DELETE);
		Object[] args = this.setArgs(entity, SQL_DELETE);
//		int[] argTypes = this.setArgTypes(entity, SQL_DELETE);
		jdbcTemplate.update(sql, args);
	}

	protected void delete(int id) {
		String sql = " DELETE FROM " + entityClass.getSimpleName()
				+ " WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	protected void deleteAll() {
		String sql = " TRUNCATE TABLE " + entityClass.getSimpleName();
		jdbcTemplate.execute(sql);
	}

	/**
	 * 未完成
	 */
	protected void batchSave(List<T> list) {
	}

	/**
	 * 未完成
	 */
	protected void batchUpdate(List<T> list) {

	}

	/**
	 * 未完成
	 */
	protected void batchDelete(List<T> list) {

	}

	protected T queryById(int id) {
		String sql = "SELECT * FROM " + entityClass.getSimpleName().toLowerCase()
				+ " WHERE id=?";
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper, id).get(0);
	}

	protected List<T> queryAll() {
		String sql = "SELECT * FROM " + entityClass.getSimpleName().toLowerCase();
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql, rowMapper);
	}

	// 组装SQL
	private String makeSql(String sqlFlag) {
		return setInfoSql(entityClass, sqlFlag);
	}
	
	private String setInfoSql(Class clazz, String sqlFlag){
		String table=clazz.getSimpleName().toLowerCase();
		StringBuffer fieldSql = new StringBuffer();
		StringBuffer valueSql = new StringBuffer();
		for(; clazz.getName().indexOf("Object") <= 0 ; clazz = clazz.getSuperclass()){
			Field[] fields = clazz.getDeclaredFields();
			for(int i = 0; fields != null && i < fields.length; i++){
				fields[i].setAccessible(true); // 暴力反射
				if (sqlFlag.equals(SQL_INSERT)) {
					String column = fields[i].getName();
					if (column.equals("id")) { // id 代表主键
						continue;
					}
					if(isHaveCapitals(column)){
						column = replaceUnderLine(column);
					}
					fieldSql.append(column).append(",");
					valueSql.append("?,");
				} else if (sqlFlag.equals(SQL_UPDATE)) {
					fields[i].setAccessible(true); // 暴力反射
					String column = fields[i].getName();
					if(isHaveCapitals(column)){
						column = replaceUnderLine(column);
					}
					if (column.equals("id")) { // id 代表主键
						continue;
					}
					fieldSql.append(column).append("=").append("?,");
				}
			}
		}
		
		StringBuffer sql = new StringBuffer();
		if (sqlFlag.equals(SQL_INSERT)) {
			sql.append(" INSERT INTO " + table);
			sql.append("(");
			sql.append(fieldSql.deleteCharAt(fieldSql.length() - 1));
			sql.append(") VALUES (");
			sql.append(valueSql.deleteCharAt(valueSql.length() - 1));
			sql.append(")");
			
		}else if (sqlFlag.equals(SQL_UPDATE)) {
			sql.append(" UPDATE " + table + " SET ");
			
			sql.append(fieldSql.deleteCharAt(fieldSql.length() - 1));
			
			sql.append(" WHERE id=?");
		} else if (sqlFlag.equals(SQL_DELETE)) {
			sql.append(" DELETE FROM " + table
					+ " WHERE id=?");
		}
		return sql.toString();
	}

	// 设置参数
//	private Object[] setArgs(T entity, String sqlFlag) {
//		Field[] fields = entityClass.getDeclaredFields();
//		if (sqlFlag.equals(SQL_INSERT)) {
//			Object[] args = new Object[fields.length];
//			for (int i = 0; args != null && i < args.length; i++) {
//				try {
//					fields[i].setAccessible(true); // 暴力反射
//					args[i] = fields[i].get(entity);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			return args;
//		} else if (sqlFlag.equals(SQL_UPDATE)) {
//			Object[] tempArr = new Object[fields.length];
//			for (int i = 0; tempArr != null && i < tempArr.length; i++) {
//				try {
//					fields[i].setAccessible(true); // 暴力反射
//					tempArr[i] = fields[i].get(entity);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			Object[] args = new Object[fields.length];
//			System.arraycopy(tempArr, 1, args, 0, tempArr.length - 1); // 数组拷贝
//			args[args.length - 1] = tempArr[0];
//			return args;
//		} else if (sqlFlag.equals(SQL_DELETE)) {
//			Object[] args = new Object[1]; // 长度是1
//			fields[0].setAccessible(true); // 暴力反射
//			try {
//				args[0] = fields[0].get(entity);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return args;
//		}
//		return null;
//	}
	// 设置参数
	public Object[] setArgs(T entity, String sqlFlag) {
		Class clazz =entity.getClass();
		List list = new ArrayList();
		for(; clazz.getName().indexOf("Object") <= 0 ; clazz = clazz.getSuperclass()){
			Field[] fields = clazz.getDeclaredFields();
			if (sqlFlag.equals(SQL_INSERT)) {
				for (int i = 0; fields != null && i < fields.length; i++) {
					try {
						String column = fields[i].getName();
						if (column.equals("id")) { // id 代表主键
							continue;
						}
						fields[i].setAccessible(true); // 暴力反射
						list.add(fields[i].get(entity));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			} else if (sqlFlag.equals(SQL_UPDATE)) {
				for (int i = 0; fields != null && i < fields.length; i++) {
					try {
						fields[i].setAccessible(true); // 暴力反射
						list.add(fields[i].get(entity));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			} else if (sqlFlag.equals(SQL_DELETE)) {
				for (int i = 0; fields != null && i < fields.length; i++) {
					try {
						String column = fields[i].getName();
						if (column.equals("id")) { // id 代表主键
							fields[i].setAccessible(true); // 暴力反射
							list.add(fields[i].get(entity));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		if(sqlFlag.equals(SQL_UPDATE)){
			Object [] objs = list.toArray();
			Object [] temp = list.toArray();
			objs[objs.length - 2] = temp[temp.length-1];
			int flag = (temp.length)-2;
			objs[objs.length-1] = temp[flag];
			return objs;
		}
		return list.toArray();
	}
	
	public static void main(String [] args){
//		Distributor b=new Distributor();
//		b.setAddress("北京");
//		b.setContactName("rs");
//		b.setEmail("89@a.com");
//		b.setLastModified(null);
//		b.setMobilePhone("321654");
//		b.setName("rss");
//		b.setPhone("12312");
//		b.setId(15);
//		BaseDao db= new BaseDao();
//		String sql = db.setInfoSql(Distributor.class,"delete");
//		System.out.println(sql);
//		Object [] objs =db.setArgs(b, "delete");
//		for (Object object : objs) {
//			System.out.println(object);
//		}
		String dist="contactName";
//		boolean bol= isHaveCapitals(dist);
//		System.out.println(bol);
//		String strss= replaceUnderLine(dist);
//		System.out.println(strss);
		
		String   str = "abcdABCDqwQW";
		String 	str1 = str.toLowerCase();
		String  str2 = str.toUpperCase();
		String sum="";
		for(int i=0;i<str.length();i++){
			if((str.substring(i, 1+i)).equals(str1.substring(i, 1+i))){
				sum +=str2.substring(i, 1+i);
			}else{
				sum +=str1.substring(i, 1+i);
			}
			
		}
		
		System.out.println(sum);
//		
	}

	private boolean isHaveCapitals(String field) {
		for (int i = 0; i < field.length(); i++) {
			char c = field.charAt(i);
			if (!Character.isLowerCase(c)) {
				return true;
			}
		}
		return false;
	}
	private String replaceUnderLine(String field) {
		String str = "";
		for (int i = 0; i < field.length(); i++) {
			char c = field.charAt(i);
			if (!Character.isLowerCase(c)) {
				str+="_"+String.valueOf(c).toLowerCase();
			}else{
				str+=c;
			}
		}
		return str;
	}
	// 设置参数类型（写的不全，只是一些常用的）
	private int[] setArgTypes(T entity, String sqlFlag) {
		Field[] fields = entityClass.getDeclaredFields();
		if (sqlFlag.equals(SQL_INSERT)) {
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; argTypes != null && i < argTypes.length; i++) {
					fields[i].setAccessible(true); // 暴力反射
					if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.String")) {
						argTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Double")) {
						argTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Integer")) {
						argTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.util.Date")) {
						argTypes[i] = Types.DATE;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.sql.Timestamp")) {
						argTypes[i] = Types.TIMESTAMP;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			int[] tempArgTypes = new int[fields.length];
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; tempArgTypes != null && i < tempArgTypes.length; i++) {
					fields[i].setAccessible(true); // 暴力反射
					if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.String")) {
						tempArgTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Double")) {
						tempArgTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.lang.Integer")) {
						tempArgTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.util.Date")) {
						tempArgTypes[i] = Types.DATE;
					} else if (fields[i].get(entity).getClass().getName()
							.equals("java.sql.Timestamp")) {
						argTypes[i] = Types.TIMESTAMP;
					}
				}
				System.arraycopy(tempArgTypes, 1, argTypes, 0,
						tempArgTypes.length - 1); // 数组拷贝
				argTypes[argTypes.length - 1] = tempArgTypes[0];

			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;

		} else if (sqlFlag.equals(SQL_DELETE)) {
			int[] argTypes = new int[1]; // 长度是1
			try {
				fields[0].setAccessible(true); // 暴力反射
				if (fields[0].get(entity).getClass().getName()
						.equals("java.lang.String")) {
					argTypes[0] = Types.VARCHAR;
				} else if (fields[0].get(entity).getClass().getName()
						.equals("java.lang.Integer")) {
					argTypes[0] = Types.INTEGER;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		}
		return null;
	}

	@SuppressWarnings("unused")
	private List<T> queryAll(int pageNo, int pageSize,
			Map<String, String> where, LinkedHashMap<String, String> orderby) {
		// where 与 order by 要写在select * from table 的后面，而不是where rownum<=? )
		// where rn>=?的后面
		StringBuffer sql = new StringBuffer(
				" SELECT * FROM (SELECT t.*,ROWNUM rn FROM (SELECT * FROM "
						+ entityClass.getSimpleName());
		if (where != null && where.size() > 0) {
			sql.append(" WHERE "); // 注意不是where
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" ").append(columnValue)
						.append(" AND "); // 没有考虑or的情况
			}
			int endIndex = sql.lastIndexOf("AND");
			if (endIndex > 0) {
				sql = new StringBuffer(sql.substring(0, endIndex));
			}
		}
		if (orderby != null && orderby.size() > 0) {
			sql.append(" ORDER BY ");
			for (Map.Entry<String, String> me : orderby.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" ").append(columnValue)
						.append(",");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(" ) t WHERE ROWNUM<=? ) WHERE rn>=? ");
		System.out.println("SQL=" + sql);
		Object[] args = { pageNo * pageSize, (pageNo - 1) * pageSize + 1 };
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return jdbcTemplate.query(sql.toString(), args, rowMapper);
	}

	protected int count(Map<String, String> where) {
		StringBuffer sql = new StringBuffer(" SELECT COUNT(*) FROM "
				+ entityClass.getSimpleName());
		if (where != null && where.size() > 0) {
			sql.append(" WHERE ");
			for (Map.Entry<String, String> me : where.entrySet()) {
				String columnName = me.getKey();
				String columnValue = me.getValue();
				sql.append(columnName).append(" ").append(columnValue)
						.append(" AND "); // 没有考虑or的情况
			}
			int endIndex = sql.lastIndexOf("AND");
			if (endIndex > 0) {
				sql = new StringBuffer(sql.substring(0, endIndex));
			}
		}
		System.out.println("SQL=" + sql);
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}

}
