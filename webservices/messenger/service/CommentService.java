package org.sgaonkar.webservices.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.sgaonkar.webservices.messenger.database.DatabaseClass;
import org.sgaonkar.webservices.messenger.model.*;

public class CommentService {

private static Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public CommentService(){
		Comment comment1 = new Comment(1,"Hello world","Suman");
		Comment comment2 = new Comment(2,"Hello world","Suman");
		Map<Long,Comment> comments = new HashMap<>();
		comments.put(1L,comment1);
		comments.put(2L,comment2);
	}
	
	public List<Comment> getAllComments(long messageId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
		//not reccommanded as its a part of presentation
		//this file is for businees logic
		ErrorMessage err = new ErrorMessage("Not Found",404,"Link to documentation");
		Response response= Response.status(Status.NOT_FOUND)
					   .entity(err)
					   .build();
		
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if(comments==null){
			throw new WebApplicationException(response);
		}
		 Comment comment = comments.get(commentId);
		 if(comment==null){
			 throw new WebApplicationException(response);
		 }
		 return comment;
	}
	
	public Comment addComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(),comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if(comments.size()<0){
			return null;
		}
		comments.put(comment.getId(),comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}