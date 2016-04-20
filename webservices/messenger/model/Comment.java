package org.sgaonkar.webservices.messenger.model;

import java.util.Date;

public class Comment {

	private long id;
	private String comment;
	private Date created;
	private String auther;
	
	public Comment(){}
	
	public Comment(long id, String comment, String auther) {
		this.id = id;
		this.comment = comment;
		this.created = new Date();
		this.auther = auther;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	
	
}
