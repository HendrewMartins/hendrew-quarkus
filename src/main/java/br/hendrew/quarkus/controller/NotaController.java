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

import br.hendrew.quarkus.entity.Avaliacao;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Nota;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.NotaService;

@RequestScoped
@Path("/api/nota")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotaController {
	
	private final NotaService notaService;

	@Inject
    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }
 
    @GET
 	@PermitAll
    @Operation(summary = "Listar Notas", description = "Lista todas Notas")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Nota.class))))
    public List<Nota> getNota() {
        return notaService.getAllNota();
    }
 
  @GET
  	@PermitAll
    @Path("/{id}")
    @Operation(summary = "Pegar Nota", description = "Pesquisa por um ID a Nota")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Nota.class))),
            @APIResponse(responseCode = "404", description="Nota not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Nota getNota(@PathParam("id") int id) throws MenssageNotFoundException {
        return notaService.getNotaById(id);
    }

    @POST
    @PermitAll
    @Operation(summary = "Adiconar a Nota", description = "Create um Nota e persists no database")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Nota.class))))
    public Nota createNota(@Valid NotaDto notaDto) {
        return notaService.saveNota(notaDto.toNota());
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Atualizar uma Nota", description = "Atualizar um Nota existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Nota.class))),
            @APIResponse(responseCode = "404", description="Nota not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Nota updateNota(@PathParam("id") int id, @Valid NotaDto notaDto) throws MenssageNotFoundException {
        return notaService.updateNota(id, notaDto.toNota());
    }

    @DELETE
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Apagar Nota", description = "Deletar uma Nota pelo ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description="Nota not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Response deleteNota(@PathParam("id") int id) throws MenssageNotFoundException {
        notaService.deleteNota(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Schema(name="NotaDTO", description="DTO para Criar um novo Nota")
    public static class NotaDto {

        @NotBlank
        @Schema(title = "avaliacao", required = true)
        private Avaliacao avaliacao;
        
        @NotBlank
        @Schema(title = "nota", required = true)
        private double nota;
    	
        @NotBlank
        @Schema(title = "bimestre", required = true)
        private Bimestre bimestre;
        

        public Avaliacao getAvaliacao() {
            return avaliacao;
        }

        public void setId_Avaliacao(Avaliacao avaliacao) {
            this.avaliacao = avaliacao;
        }
        
        public double getNota() {
            return nota;
        }

        public void setNota(double nota) {
            this.nota = nota;
        }
        
        public Bimestre getBimestre() {
            return bimestre;
        }

        public void setId_bimestre(Bimestre bimestre) {
            this.bimestre = bimestre;
        }
               
        public Nota toNota() {
        	Nota nota = new Nota();
            nota.setAvaliacao(this.avaliacao);
            nota.setBimestre(this.bimestre);
            nota.setNota(this.nota);
            return nota;
        }
    }


}
