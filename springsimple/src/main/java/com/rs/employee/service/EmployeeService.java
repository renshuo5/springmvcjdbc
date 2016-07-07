package com.rs.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rs.employee.dao.EmployeeDao;
import com.rs.employee.vo.Employee;

@Service
@Transactional(readOnly=false)
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	/**
	 * 
	 * @param distri
	 * @return 返回数据库插入的id
	 */
	@Transactional(readOnly=false)
	public int saveEmployee(Employee emp){
		return employeeDao.saveEmployee(emp);
	}
	
	/**
	 * 
	 * @param distri
	 * @return 1修改成功 0修改失败
	 */
	public int updateEmployee(Employee emp){
		return employeeDao.updateEmployee(emp);
	}
	
	public List<Employee> findAll(){
		return employeeDao.findAll();
	}
	
	public Employee findById(int id){
		return employeeDao.findById(id);
	}
	
//	public void detele(Employee distri){
//		EmployeeDao.detele(distri);
//	}
}
