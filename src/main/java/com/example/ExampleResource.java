package com.example;

import com.example.impl.ProcessImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/process")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {

    @Inject
    ProcessImpl process;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/layout")
    public Response process()
    {
        return Response.ok().entity(process.process()).build();
    }
}