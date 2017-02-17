package com.jindata.restserver.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jindata.restserver.apis.beans.DetailUser;

@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserService {
    @GET
    @Path("/user/{id}")
    public DetailUser getUser(@PathParam("id")long id){
        return null;
        
    }
}
