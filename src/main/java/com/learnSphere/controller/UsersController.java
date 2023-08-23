 package com.learnSphere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnSphere.entities.Comments;
import com.learnSphere.entities.Users;
import com.learnSphere.services.CommentService;
import com.learnSphere.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
@Autowired
	UsersService uservice;
@Autowired
CommentService cService;

	@PostMapping("/addUser")
	public String addUser(@ModelAttribute Users user)
	{
		String email=user.getEmail();
		boolean isPresent=uservice.checkEmail(email);
		if(isPresent==false)
		{
			uservice.addUser(user);	
		}
		else {
			System.out.println("Email Already Exist");
		}
		return "login";
	}
	@PostMapping("/validateUser")
	public String validateUser(@RequestParam ("email") String email,
				@RequestParam("password")String password,
				HttpSession session, Model m)
	{
	Users user=uservice.findUserByEmail(email);
	String dbPassword=user.getPassword();
	String role=user.getRole();
if(password.equals(dbPassword))
{
		session.setAttribute("loggedInUser", user);
		m.addAttribute("user",user);

	if(role.equals("trainer"))
		return "trainerHome";	
	else
		return "studentHome";
}
else
{
return "login";	
}
}
	
	@PostMapping("/addComment")
	public String comments (@RequestParam("comment")String comment,
			Model model)
	{
		Comments c=new Comments();
		c.setComment(comment);
		cService.addComment(c);
		
		List<Comments>commentsList=cService.commentList();
		model.addAttribute("comments",commentsList);
		return "redirect:/";
	}
	

}