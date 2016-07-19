package com.smartSchool.restFulService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestRestService {

	//Sample webservice call URL : http://localhost:8080/SmartSchool/SmartSchoolRest/test/srikanth
	@GET
	@Path("/{param}")
	public Response inputMessage(@PathParam("param") String input) {
 
		String output = "Input String : " + input;
 
		return Response.status(200).entity(output).build();
 
	}
}
