package resources;


import java.net.URI;
import java.net.URISyntaxException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import daoInterface.MemberEjbInt;
import model.Member;

@Path("/member")
@RequestScoped
public class MemberRest {
	
	@Inject
	MemberEjbInt memberService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson() {
		return Response.ok(memberService.listMembers()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMember(Member member) throws URISyntaxException {
		memberService.createMember(member);
		return Response.created(new URI("localhost:8080/webservice/rest/member")).build();
	}
	
	@GET
	@Path("/{lastName}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchByLastName(@PathParam("lastName") String lastName) throws URISyntaxException {
		return Response.ok(memberService.searchByLastName(lastName)).build();
	}
	
	@PUT
	@Path("/updateaddress/{memberId}/{address}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateById(@PathParam("memberId")int memberId, @PathParam("address")String address) throws URISyntaxException {
		Response.created(new URI("localhost:8080/webservice/rest/member/"+memberId)).build();
		return Response.ok(memberService.updateById(memberId, address)).build();
	}
	

}
