package com.rs.distributor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rs.distributor.service.DistributorService;
import com.rs.distributor.vo.Distributor;


@Controller
@RequestMapping("/distri")
public class DistributorController {
	
	@Autowired
	private DistributorService distributorService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		
		List<Distributor> list = distributorService.findAll();
		model.addAttribute("list", list);
		return "distributor/list";
	}
	
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String form(@RequestParam(value="id",required=false)String id,Model model){
		
		Distributor distri =new Distributor();
		if(id!=null&& !"".equals(id)){
			distri = distributorService.findById(Integer.parseInt(id));
		}
		model.addAttribute("entity", distri);
		return "distributor/form";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create(Distributor distri,Model model){
		
		if(distri==null){
			distri=new Distributor(); 
		}
		int id = distributorService.saveDistributor(distri);
		distri.setId(id);
		model.addAttribute("entity", distri);
		
		
		return "redirect:./distri";
	}
	
	@RequestMapping(value="/${id}",method=RequestMethod.PUT)
	public String update(@PathVariable("id")Distributor distri,Model model){
		
		if(distri==null){
			distri=new Distributor(); 
		}
		
		model.addAttribute("entity", distri);
		
		
		return "redirect:./distri";
	}

}
