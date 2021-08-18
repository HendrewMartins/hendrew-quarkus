package br.hendrew.quarkus.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import br.hendrew.quarkus.entity.Boletim;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.BoletimService;


@RequestScoped
@Path("/api/boletim")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoletimController {
	
	private final BoletimService boletimService;

	@Inject
    public BoletimController(BoletimService boletimService) {
        this.boletimService = boletimService;
    }
 

 
 @GET
 @PermitAll
 @Path("/{ano}")
 @Operation(summary = "Listar Boletim", description = "Pesquisa por um Ano no boletim")
 @APIResponses(value = {
         @APIResponse(responseCode = "200", description = "Success",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boletim.class))),
         @APIResponse(responseCode = "404", description="Boletim not found",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
 })
 public List<Boletim> getBoletim(@PathParam("ano") long ano) throws MenssageNotFoundException {
     return boletimService.buscarboletim(ano);
 }



}
