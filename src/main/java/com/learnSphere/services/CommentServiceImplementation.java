package com.learnSphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entities.Comments;
import com.learnSphere.repositories.CommentRepository;

@Service
public class CommentServiceImplementation 
			implements CommentService{

	
	@Autowired
	CommentRepository commentRepo;
	@Override
	public List<Comments> commentList() {
		
		return commentRepo.findAll();
	}
	@Override
	public String addComment(Comments comment) {
	
		commentRepo.save(comment);
		return "comment added";
	}

}
