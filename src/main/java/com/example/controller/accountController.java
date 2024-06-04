package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.account;
import com.example.repo.accountRepo;
import com.example.service.accountService;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/admin")

public class accountController {

	@Autowired
	accountRepo ar;
	@Autowired 
	accountService as;
	


	public accountController() {}
//	public accountController(accountService mockAccountService) {
//		// TODO Auto-generated constructor stub
//		
//	}

	@Autowired
    public accountController(accountService accountService) {
        this.as = accountService;
    }
	@GetMapping("/read")
	public List<account> read() {
		return ar.findAll();
	}

	 
	 @PostMapping("/add")
	    public String createAccount(@RequestParam String name,
	                              @RequestParam String username,
	                              @RequestParam String password,
	                              @RequestParam int age,
	                              @RequestParam String ssn,
	                              @RequestParam String address,
	                              @RequestParam String email,
	                              @RequestParam String phone,
	                              @RequestParam float balance) {
		 
	        account ac = new account(name, username, password, age, ssn, address, email, phone, balance);
	        ar.save(ac);
	        return "account successfully created";
	 }

	@DeleteMapping("/delete")
	public String delete(@RequestParam String username) {
		as.delete(username);
		return "delete successfull";
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@PostMapping("/loginacc")
	public ModelAndView loginacc(@RequestParam String username, @RequestParam String password,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		boolean loginSuccessful = as.login(username, password);
		if (loginSuccessful) {
			session.setAttribute("username", username);
			modelAndView.setViewName("redirect:/admin/indexPage");
		} else {
			modelAndView.setViewName("login");
			modelAndView.addObject("loginFailed", true);
		}
		return modelAndView;
	}

	@GetMapping("/indexPage")
	public ModelAndView indexPage() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("indexPage");
	   
	    return modelAndView;
	}
	@GetMapping("/deletePage")
	public ModelAndView deletePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("deletePage");
		return modelAndView;
	}
	@GetMapping("/addPage")
	public ModelAndView addPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addPage");
		return modelAndView;
	}
	public void setAs(accountService mockAccountService) {
		// TODO Auto-generated method stub
		
	}
}
