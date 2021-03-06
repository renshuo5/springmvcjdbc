package com.rs.employee.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rs.employee.dao.EmployeeDao;
import com.rs.employee.vo.Employee;

@Service
@Transactional(readOnly=false)
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	@Qualifier("employeeCache")
	private Ehcache employeeCache;
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
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAll(){
		List<Employee> list = new ArrayList<Employee>();
		if(employeeCache.get("employees")==null){
			list = employeeDao.findAll();
			employeeCache.put(new Element("employees", list));
		}else{
			Element element = employeeCache.get((Serializable)"employees");
			list =  (List<Employee>) element.getObjectValue();
		}
		return list;
	}
	
	public Employee findById(int id){
		return employeeDao.findById(id);
	}
	
//	public void detele(Employee distri){
//		EmployeeDao.detele(distri);
//	}
}
