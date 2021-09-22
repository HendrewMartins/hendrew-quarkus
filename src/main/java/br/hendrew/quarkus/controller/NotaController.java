package br.hendrew.quarkus.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Nota;
import br.hendrew.quarkus.entity.Nota_Angular;
import br.hendrew.quarkus.entity.Nota_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.AvaliacaoService;
import br.hendrew.quarkus.service.BimestreService;
import br.hendrew.quarkus.service.NotaService;

@RequestScoped
@Path("/api/nota")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotaController {
	
	private final NotaService notaService;
    private final BimestreService bimestreService;
    private final AvaliacaoService avaliacaoService;

	@Inject
    public NotaController(NotaService notaService, BimestreService bimestreService, 
                          AvaliacaoService avaliacaoService) {
        this.notaService = notaService;
        this.bimestreService = bimestreService;
        this.avaliacaoService = avaliacaoService;
    }
 
    @GET
 	@PermitAll
    @Operation(summary = "Listar Notas", description = "Lista todas Notas")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Nota.class))))
    public List<Nota_Auxiliar> getNota() {
        return notaService.getAllNotaDescricao();
    }
 
    @GET
  	@PermitAll
    @Path("/id/{id}")
    @Operation(summary = "Pegar Nota", description = "Pesquisa por um ID a Nota")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Nota.class))),
            @APIResponse(responseCode = "404", description="Nota not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Nota_Angular getNota(@PathParam("id") int id) throws MenssageNotFoundException {
        return notaService.getNotaAngById(id);
    }

    @POST
    @PermitAll
    @Path("/save")
    @Operation(summary = "Adiconar a Nota", description = "Create um Nota e persists no database")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Nota.class))))
    public Nota createNota(@Valid NotaDto notaDto) throws MenssageNotFoundException {
        return notaService.saveNota(notaDto.toNota(), 
        bimestreService.getBimestreById(notaDto.getId_Bimestre()), 
        avaliacaoService.getAvaliacaoById(notaDto.getId_Avaliacao()));
    }

    @PUT
    @PermitAll
    @Path("/edit/{id}")
    @Operation(summary = "Atualizar uma Nota", description = "Atualizar um Nota existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Nota.class))),
            @APIResponse(responseCode = "404", description="Nota not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Nota updateNota(@PathParam("id") int id, @Valid NotaDto notaDto) throws MenssageNotFoundException {
        return notaService.updateNota(id, notaDto.toNota(), 
        bimestreService.getBimestreById(notaDto.getId_Bimestre()), 
        avaliacaoService.getAvaliacaoById(notaDto.getId_Avaliacao()));
    }

    @DELETE
    @PermitAll
    @Path("/delete/{id}")
    @Operation(summary = "Apagar Nota", description = "Deletar uma Nota pelo ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description="Nota not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public boolean deleteNota(@PathParam("id") int id) throws MenssageNotFoundException {
        notaService.deleteNota(id);
        return true;
    }

    @Schema(name="NotaDTO", description="DTO para Criar um novo Nota")
    public static class NotaDto {

        
        @Schema(title = "id_Avaliacao", required = true)
        private long id_Avaliacao;
        
        
        @Schema(title = "nota", required = true)
        private double nota;
    	
        
        @Schema(title = "id_Bimestre", required = true)
        private long id_Bimestre;

        
        @Schema(title = "id_Aluno", required = true)
        private Alunos id_Aluno;
        

        public long getId_Avaliacao() {
            return id_Avaliacao;
        }

        public void setId_Avaliacao(long id_Avaliacao) {
            this.id_Avaliacao= id_Avaliacao;
        }
        
        public double getNota() {
            return nota;
        }

        public void setNota(double nota) {
            this.nota = nota;
        }
        
        public long getId_Bimestre() {
            return id_Bimestre;
        }

        public void setId_Bimestre(long id_Bimestre) {
            this.id_Bimestre = id_Bimestre;
        }

        public Alunos getId_Aluno() {
            return id_Aluno;
        }

        public void setId_Aluno(Alunos id_Aluno) {
            this.id_Aluno = id_Aluno;
        }
               
        public Nota_Angular toNota() {
        	Nota_Angular nota = new Nota_Angular();
            nota.setId_Avaliacao(this.id_Avaliacao);
            nota.setId_Bimestre(this.id_Bimestre);
            nota.setNota(this.nota);
            return nota;
        }
    }


}
