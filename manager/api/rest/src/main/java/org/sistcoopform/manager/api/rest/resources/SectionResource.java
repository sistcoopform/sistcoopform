package org.sistcoopform.manager.api.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoopform.manager.api.beans.representations.idm.SectionRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */

public interface SectionResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SectionRepresentation toRepresentation();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(SectionRepresentation rep);

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove();

	@Path("questions")
	public QuestionsResource questions();

}