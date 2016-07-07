package com.rs.employee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rs.base.dao.BaseDao;
import com.rs.employee.vo.Employee;


@Repository
public class EmployeeDao extends BaseDao<Employee>{
	
	/**
	 * 
	 * @param distri
	 * @return 返回数据库插入的id
	 */
	public int saveEmployee(Employee emp){
		this.save(emp);
		return 1;
	}
	
	/**
	 * 
	 * @param distri
	 * @return 1修改成功 0修改失败
	 */
	public int updateEmployee(Employee emp){
		return this.update(emp);
	}
	
	public List<Employee> findAll(){
		return this.queryAll();
	}
	
//	
	public Employee findById(int id){
		return this.queryById(id);
	}
//	
//	public void detele(Employee distri){
//		delete(distri);
//	}

}
