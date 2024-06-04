package com.example.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.account;
import com.example.repo.accountRepo;

@Service
public class accountService {

	@Autowired
	accountRepo ar;

	public boolean login(String username, String password) {
	  
	    if(username.equals("devika") && password.equals("devika"))
{
	return true;
}
	    
else return false;
	

}
	@Transactional
	public void delete( String username) {
		ar.deleteById(username);
	}


}