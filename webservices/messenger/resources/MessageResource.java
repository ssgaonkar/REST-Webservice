package org.sgaonkar.webservices.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.sgaonkar.webservices.messenger.model.Message;
import org.sgaonkar.webservices.messenger.service.MessageService;


@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value ={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear()>0) return messageService.getAllMessagesWithYear(filterBean.getYear());
		if(filterBean.getStart()>0 && filterBean.getSize()>0) return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId")long messageId,@Context UriInfo uriInfo){
		Message message= messageService.getMessage(messageId);
		message.addLink(getUriForSelf(uriInfo, message),"self");
		message.addLink(getUriForProfile(uriInfo, message),"profile");
		message.addLink(getUriForComments(uriInfo, message),"comments");
		return message;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuther())
				.build()
				.toString();
	}
	
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(String.valueOf(message.getId()))
				.build()
				.toString();
	}
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId",message.getId())
				.build()
				.toString();
	}
	
	@POST
	public Response addMessage(Message message ,@Context UriInfo uriInfo){
		Message msg= messageService.addMessage(message);
		String id = String.valueOf(msg.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(uri) //.status(Status.CREATED)
				.entity(msg)
				.build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId")long messageId,Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId")long messageId){
		 messageService.removeMessage(messageId);
	}
	
	//subResource
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
}
