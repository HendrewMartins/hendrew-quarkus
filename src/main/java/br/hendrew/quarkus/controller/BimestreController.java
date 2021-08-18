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
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.BimestreService;

@RequestScoped
@Path("/api/bimestre")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BimestreController {
	
	private final BimestreService bimestreService;

	@Inject
    public BimestreController(BimestreService bimestreService) {
        this.bimestreService = bimestreService;
    }
 
 @GET
 	@PermitAll
    @Operation(summary = "Listar Bimestre", description = "Lista todas Bimestre")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre.class))))
    public List<Bimestre> getBimestre() {
        return bimestreService.getAllBimestre();
    }
 
  @GET
  	@PermitAll
    @Path("/{id}")
    @Operation(summary = "Pegar Bimestre", description = "Pesquisa por um ID a Bimestre")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre.class))),
            @APIResponse(responseCode = "404", description="Bimestre not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Bimestre getBimestre(@PathParam("id") int id) throws MenssageNotFoundException {
        return bimestreService.getBimestreById(id);
    }

    @POST
    @PermitAll
    @Operation(summary = "Adicionar a Bimestre", description = "Create um Bimestre e persistir no banco")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre.class))))
    public Bimestre createBimestre(@Valid BimestreDto bimestreDto) {
        return bimestreService.saveBimestre(bimestreDto.toBimestre());
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Atualizar um Bimestre", description = "Atualizar um Bimestre existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre.class))),
            @APIResponse(responseCode = "404", description="Bimestre not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Bimestre updateBimestre(@PathParam("id") int id, @Valid BimestreDto bimestreDto) throws MenssageNotFoundException {
        return bimestreService.updateBimestre(id, bimestreDto.toBimestre());
    }

    @DELETE
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Apagar Bimestre", description = "Apagar um Bimestre pelo ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description="Bimestre not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Response deleteBimestre(@PathParam("id") int id) throws MenssageNotFoundException {
        bimestreService.deleteBimestre(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Schema(name="BimestreDTO", description="DTO para Criar um novo Bimestre")
    public static class BimestreDto {

        @NotBlank
        @Schema(title = "Bimestre", required = true)
        private long bimestre;
        
        @NotBlank
        @Schema(title = "Ano", required = true)
        private long ano;
    	
        @NotBlank
        @Schema(title = "Faltas", required = true)
        private long faltas;
        
        @NotBlank
        @Schema(title = "Aluno", required = true)
        private Alunos alunos;

        public long getBimestre() {
            return bimestre;
        }

        public void setBimestre(long bimestre) {
            this.bimestre = bimestre;
        }
        
        public long getAno() {
            return ano;
        }

        public void setAno(long ano) {
            this.ano = ano;
        }
        
        public long getFaltas() {
            return faltas;
        }

        public void setFaltas(long faltas) {
            this.faltas = faltas;
        }
        
        public Alunos getAlunos() {
            return alunos;
        }

        public void setAlunos(Alunos alunos) {
            this.alunos = alunos;
        }
       
        public Bimestre toBimestre() {
        	Bimestre bimestre = new Bimestre();
            bimestre.setBimestre(this.bimestre);
            bimestre.setAno(this.ano);
            bimestre.setFaltas(this.faltas);
            bimestre.setAlunos(this.alunos);
            return bimestre;
        }
    }


}
