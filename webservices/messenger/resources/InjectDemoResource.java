package org.sgaonkar.webservices.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

// Matrix params are separated by ;
//@HeaderParam can be used for authentication
//@FormParam for accessing html forms key: control's name, value: control's value


@Path("/injectDemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("parama") String param,
											@HeaderParam("customHeader") String header,
											@CookieParam("name") String cookie){
		return "Param: "+param + " Header value: "+ header + " cookie : "+cookie;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders headers){
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "Path : "+path +" Cookies: "+cookies;
	}
}
