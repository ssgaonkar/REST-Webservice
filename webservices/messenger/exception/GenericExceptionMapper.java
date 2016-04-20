package org.sgaonkar.webservices.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.sgaonkar.webservices.messenger.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage err = new ErrorMessage(ex.getMessage(),404,"Link to documentation");
		return Response.status(Status.NOT_FOUND)
					   .entity(err)
					   .build();
	}

}