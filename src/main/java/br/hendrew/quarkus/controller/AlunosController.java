package br.hendrew.quarkus.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.jaxrs.PathParam;



import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.AlunosService;


@RequestScoped
@Path("/api/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosController {
	
	 private final AlunosService alunosService;
	 
	 @Inject
	    public AlunosController(AlunosService alunosService) {
	        this.alunosService = alunosService;
	    }
	 
	 @GET
	 	@PermitAll
	    @Operation(summary = "Listar Alunos", description = "Lista todos alunos")
	    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))))
	    public List<Alunos> getAlunos() {
	        return alunosService.getAllAlunos();
	    }
	 
	  @GET
	  	@PermitAll
	    @Path("/{id}")
	    @Operation(summary = "Pegar aluno", description = "Pesquisa por um ID o Aluno")
	    @APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "Success",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))),
	            @APIResponse(responseCode = "404", description="Alunos not found",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
	    })
	    public Alunos getAluno(@PathParam("id") int id) throws MenssageNotFoundException {
	        return alunosService.getAlunosById(id);
	    }

	    @POST
	    @PermitAll
	    @Operation(summary = "Adicionar alunos", description = "Criar um novo aluno e persistir no banco")
	    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))))
	    public Alunos createAluno(@Valid AlunosDto alunosDto) {
	        return alunosService.saveAlunos(alunosDto.toAluno());
	    }

	    @PUT
	    @PermitAll
	    @Path("/{id}")
	    @Operation(summary = "Atualizar um aluno", description = "Atualizar um aluno existente via id")
	    @APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "Success",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))),
	            @APIResponse(responseCode = "404", description="Alunos not found",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
	    })
	    public Alunos updateAluno(@PathParam("id") int id, @Valid AlunosDto alunosDto) throws MenssageNotFoundException {
	        return alunosService.updateAlunos(id, alunosDto.toAluno());
	    }

	    @DELETE
	    @PermitAll
	    @Path("/{id}")
	    @Operation(summary = "Apagar a aluno", description = "Apagar um usuário pelo ID")
	    @APIResponses(value = {
	            @APIResponse(responseCode = "204", description = "Success"),
	            @APIResponse(responseCode = "404", description="Alunos not found",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
	    })
	    public Response deleteAluno(@PathParam("id") int id) throws MenssageNotFoundException {
	        alunosService.deleteAluno(id);
	        return Response.status(Response.Status.NO_CONTENT).build();
	    }

	    @Schema(name="AlunosDTO", description="DTO para Criar um novo Aluno")
	    public static class AlunosDto {

	        @NotBlank
	        @Schema(title = "Nome", required = true)
	        private String nome;
  	        
	        public String getNome() {
	            return nome;
	        }

	        public void setNome(String nome) {
	            this.nome = nome;
	        }

	        public Alunos toAluno() {
	            Alunos alunos = new Alunos();
	            alunos.setNome(nome);
	            return alunos;
	        }
	    }

}
