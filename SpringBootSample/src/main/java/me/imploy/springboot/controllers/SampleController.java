package me.imploy.springboot.controllers;

import me.imploy.springboot.pojos.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.springboot.service.SampleManager;

@Controller
public class SampleController {

	@Autowired
	SampleManager sampleManager;
	
	@RequestMapping("/home.htm")
	public String getStarterPage(Model model){

		/*model.addAttribute("universityNames",new ArrayList<String>(){{
			add("Purdue Univ");
			add("UCB");
			add("UCLA");
			add("UT,Austin");
		}});
		
		model.addAttribute("firstNames",
				
			new ArrayList<String>(){{
			add("Saju");
			add("Neha");
			add("Ravisha");
			add("Pallavi");
			add("Balaji");
		}});
		
		
		model.addAttribute("students",
				new LinkedHashMap<String,Integer>(){{
				put("Saju",99);
				put("Neha",98);
				put("Ravisha",96);
				put("Pallavi",95);
				put("Balaji",76);
			}});*/
		
		model.addAttribute("student",new Student());
		
		System.out.println("Loading Home Page");
		return "hello";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginForm(){
		System.out.println("Loading Login Page");
		return "login";
	}
	
	@RequestMapping(value="/addStudent", method = RequestMethod.POST)
	public String greetingPage(@ModelAttribute("student") Student student,Model model){
		System.out.println("Student Posted");
		
		model.addAttribute("student",student);
		
		return "test";
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String helloPage(Model model){
		
		model.addAttribute("testFlag", true);
		return "hello";
	}
	
	@RequestMapping(value="/clickPage", method = RequestMethod.GET)
	public String clickPage(Model model){
		model.addAttribute("testFlag", false);
		return "test";
	}
	
	
	@RequestMapping(value="/ajax_test.htm", method = RequestMethod.GET)
	@ResponseBody
	public String checkAjaxPage(Model model){
		
		model.addAttribute("testFlag", false);
		return "test";
	}
	
	
	@RequestMapping(value="/ajax_post.json", method = RequestMethod.POST)
	@ResponseBody
	public String checkPost(@RequestBody Student student, Model model){
		System.out.println("Ajax Post");
		
		return "Ajax Post";
	}
	

}
