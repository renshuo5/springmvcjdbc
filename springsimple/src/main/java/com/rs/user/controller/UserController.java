package com.rs.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rs.user.service.UserService;
import com.rs.user.vo.User;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		
		List<User> list = userService.getUsers();
		model.addAttribute("list", list);
		
		return "user/list";
	}
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String form(Model model){
		model.addAttribute("entity", new User());
		return "user/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create(User user,Model model){
		System.out.println(user);
		
//		userService.save(user);saveTest
		userService.saveTest(user);
		return "redirect:/user";
	}
}
