package com.rs.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rs.employee.service.EmployeeService;
import com.rs.employee.vo.Employee;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		
		List<Employee> list = employeeService.findAll();
		model.addAttribute("list", list);
		return "employee/list";
	}
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String form(@RequestParam(value="id",required=false)String id,Model model){
		
		Employee emp =new Employee();
		if(id!=null&& !"".equals(id)){
			emp = employeeService.findById(Integer.parseInt(id));
		}
		model.addAttribute("entity", emp);
		return "employee/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create(Employee emp,Model model){
		
		if(emp==null){
			emp=new Employee(); 
		}
		int id = employeeService.saveEmployee(emp);
		emp.setId(id);
		model.addAttribute("entity", emp);
		
		
		return "redirect:./employee";
	}
	
	@RequestMapping(value="/${id}",method=RequestMethod.PUT)
	public String update(@PathVariable("id")Employee emp,Model model){
		
		if(emp==null){
			emp=new Employee(); 
		}
		
		model.addAttribute("entity", emp);
		
		
		return "redirect:./employee";
	}

}
