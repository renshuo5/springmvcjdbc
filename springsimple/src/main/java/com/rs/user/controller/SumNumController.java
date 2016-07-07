package com.rs.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/test")
public class SumNumController {
	
	@RequestMapping("/api")
	@ResponseBody
	public String sumTotal(HttpServletRequest req,HttpServletResponse res,Model model){
		
		String jsonStr = req.getParameter("req");
		JSONObject js=new JSONObject(jsonStr);
		
		System.out.println("rs"+js.get("authHeader"));
		JSONObject jo = (JSONObject) js.get("authHeader");
		System.out.println(jo.get("token")); 
		
		String str ="[{'res':{'status': 2,"
				+ "'errors': [{'field': 'creativeId','message': 'Invalid or duplicate creative id','index': 0,'code': 2002}]}}]";
		return new JSONArray(str).toString();
	}

}
