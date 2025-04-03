package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Song;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
    @Autowired
	UsersService service;
    
    @Autowired
    SongService songService; 
    
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		boolean userstatus = service.emailExists
				(user.getEmail());
		if(userstatus == false) {
			service.addUser(user);
			return "login";
		}
		else
		{
			return "login";
		}
	}
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,
			@RequestParam String password, HttpSession session)
	{
		//invoking validateUser() in service
		if(service.validateUser(email, password) == true)
		{
			
			session.setAttribute("email", email);
			//checking whether the user is admin or customer
			if(service.getRole(email).equals("admin"))
			{
				return "adminhome";
			}
			else
			{
				return "customerhome";
			}
		}
		else
		{
			return "login";
		}
	}
    @GetMapping("/exploreSongs")
	public String exploreSongs(HttpSession session, Model model) {
			String email = (String) session.getAttribute("email");
			
			if (email == null) {
				return "redirect:/login";
			}
			
			Users user = service.getUser(email);
			if(user!= null && user.isPremium()) {
				List<Song> songslist = songService.fetchAllSongs();
				model.addAttribute("songs", songslist);
				return "displaySongs";
			}
			else {
				return "pay";
			}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "login";
		
	}
}   

