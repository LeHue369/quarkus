package org.acme.resource;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.entity.Todo;

import java.util.List;

@Path("/")
//@RunOnVirtualThread
public class TodoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Todo> getAll(){
		Log.info("get all todos");
		return Todo.listAll();
	}

	@POST
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void create(@Valid Todo todo){
		Log.infof("create todo %s", todo.title);
		todo.persist();
	}
}
