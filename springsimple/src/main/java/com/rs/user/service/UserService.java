package com.rs.user.service;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rs.user.dao.UserDao;
import com.rs.user.vo.User;

@Service
@Transactional(readOnly = false)
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	public List<User> getUsers(){
		String sql = "select name,age from test_user";
		return userDao.getUsers(sql);
	}
	
	public int getId() {
		return userDao.getId();
	}
	
	public void save(User user){
		String sql = "INSERT INTO test_user (name,age,sex) VALUES(?,?,?)";
		List parm = new ArrayList();
//		int id= this.getId();
//		parm.add(id);
//		user.setId(id);
//		if(user.getVersion()!=null){
//			parm.add(user.getVersion()+1);
//		}else{
//			parm.add(1);
//		}
//		parm.add(user.getLastModified());
//		parm.add(user.getLastModified());
		parm.add(user.getName());
		parm.add(user.getAge());
		parm.add(user.getSex());
		
		int flag = userDao.save(parm, sql);
		log.debug("flag"+flag);
		
	}
	
	public void update(User user){
		String sql = "update set ta_user name=?,age=?,sex=? where id=?";
		List parm = new ArrayList();
		
		parm.add(user.getName());
		parm.add(user.getAge()+"");
		parm.add(user.getSex());
		parm.add(user.getId());
		
		int flag = userDao.update(parm, sql);
		log.debug("flag"+flag);
		
	}
	
	public void saveTest(User user){
		this.save(user);
		System.out.println(user.getId());
		this.update(user);
	}
	

}
