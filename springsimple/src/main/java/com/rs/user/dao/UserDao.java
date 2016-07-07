package com.rs.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rs.user.vo.User;

@Repository
public class UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getId() {
		String sql = "select max(id) from ta_user";
		Integer id = jdbcTemplate.queryForObject(sql,Integer.class);
		if(id==null){
			return 1;
		}else{
			return id+1;
		}
	}
	
	public List<User> getUsers(String sql){
		BeanPropertyRowMapper br = new BeanPropertyRowMapper(User.class);
		List<User> list = jdbcTemplate.query(sql, br);
		return list;
	}
	
	public int save(List parm,String sql){
		int flag = jdbcTemplate.update(sql, parm.toArray());
		return flag;
	}
	
	public int update(List parm,String sql){
		int flag = jdbcTemplate.update(sql, parm.toArray());
		return flag;
	}
	
	
	
}
