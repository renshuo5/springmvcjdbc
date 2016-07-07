package com.rs.distributor.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rs.base.dao.BaseDao;
import com.rs.distributor.vo.Distributor;


@Repository
public class DistributorDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** 设置一些操作的常量 */
	protected static final String SQL_INSERT = "insert";
	protected static final String SQL_UPDATE = "update";
	protected static final String SQL_DELETE = "delete";
	/**
	 * 
	 * @param distri
	 * @return 返回数据库插入的id
	 */
	public int saveDistributor(Distributor distri){
		final String innersql = this.makeSql(SQL_INSERT);
		final Object[] innerO = this.setArgs(distri, SQL_INSERT);
		
		return jdbcTemplate.update(innersql, innerO);
	}
	private String makeSql(String sqlFlag) {
		return setInfoSql(Distributor.class, sqlFlag);
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
	
	// 设置参数
	public Object[] setArgs(Distributor entity, String sqlFlag) {
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
	/**
	 * 
	 * @param distri
	 * @return 1修改成功 0修改失败
	 */
//	public int updateDistributor(Distributor distri){
//		return update(distri);
//	}
//	
	public List<Distributor> findAll(){
		String sql = "SELECT * FROM distributor" ;
		RowMapper<Distributor> rowMapper = BeanPropertyRowMapper.newInstance(Distributor.class);
		return jdbcTemplate.query(sql, rowMapper);
	}
//	
	public Distributor findById(int id){
		String sql = "SELECT * FROM distributor"  + " WHERE id=?";
		RowMapper<Distributor> rowMapper = BeanPropertyRowMapper.newInstance(Distributor.class);
		return jdbcTemplate.query(sql, rowMapper, id).get(0);
	}
//	
//	public void detele(Distributor distri){
//		delete(distri);
//	}

}
