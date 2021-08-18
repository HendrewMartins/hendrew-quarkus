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
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.AvaliacaoService;


@RequestScoped
@Path("/api/avaliacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoController {
	
	private final AvaliacaoService avaliacaoService;
	
	 @Inject
	    public AvaliacaoController(AvaliacaoService avaliacaoService) {
	        this.avaliacaoService = avaliacaoService;
	    }
	 
	 @GET
	 	@PermitAll
	    @Operation(summary = "Listar Avaliacao", description = "Lista todas Avaliacao")
	    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Avaliacao.class))))
	    public List<Avaliacao> getAvaliacaos() {
	        return avaliacaoService.getAllAvaliacao();
	    }
	 
	  @GET
	  	@PermitAll
	    @Path("/{id}")
	    @Operation(summary = "Pegar a avaliacao", description = "Pesquisa por um ID a Avaliacao")
	    @APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "Success",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Avaliacao.class))),
	            @APIResponse(responseCode = "404", description="Avaliacao not found",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
	    })
	    public Avaliacao getAvaliacao(@PathParam("id") int id) throws MenssageNotFoundException {
	        return avaliacaoService.getAvaliacaoById(id);
	    }

	    @POST
	    @PermitAll
	    @Operation(summary = "Adiconar a Avaliacao", description = "Criar uma Avaliacao e persistir no banco")
	    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Avaliacao.class))))
	    public Avaliacao createAvaliacao(@Valid AvaliacaoDto avaliacaoDto) {
	        return avaliacaoService.saveAvaliacao(avaliacaoDto.toAvaliacao());
	    }

	    @PUT
	    @PermitAll
	    @Path("/{id}")
	    @Operation(summary = "Atualizar uma Avaliacao", description = "Atualizar um Avaliacao existente via id")
	    @APIResponses(value = {
	            @APIResponse(responseCode = "200", description = "Success",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Avaliacao.class))),
	            @APIResponse(responseCode = "404", description="Avaliacao not found",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
	    })
	    public Avaliacao updateAvaliacao(@PathParam("id") int id, @Valid AvaliacaoDto avaliacaoDto) throws MenssageNotFoundException {
	        return avaliacaoService.updateAvaliacao(id, avaliacaoDto.toAvaliacao());
	    }

	    @DELETE
	    @PermitAll
	    @Path("/{id}")
	    @Operation(summary = "Apagar a Avaliacao", description = "Deletar uma Avaliacao pelo ID")
	    @APIResponses(value = {
	            @APIResponse(responseCode = "204", description = "Success"),
	            @APIResponse(responseCode = "404", description="Avaliacao not found",
	                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
	    })
	    public Response deleteAvaliacao(@PathParam("id") int id) throws MenssageNotFoundException {
	        avaliacaoService.deleteAvaliacao(id);
	        return Response.status(Response.Status.NO_CONTENT).build();
	    }

	    @Schema(name="AvaliacaoDTO", description="DTO para Criar um novo Avaliacao")
	    public static class AvaliacaoDto {

	        @NotBlank
	        @Schema(title = "Descrição", required = true)
	        private String descricao;
	    	
	        @NotBlank
	        @Schema(title = "Peso", required = true)
	        private Double peso;
	    	      
	        public String getDescricao() {
	            return descricao;
	        }

	        public void setDescricao(String descricao) {
	            this.descricao = descricao;
	        }
	        
	        public Double getPeso() {
	            return peso;
	        }

	        public void setPeso(Double peso) {
	            this.peso = peso;
	        }

	        public Avaliacao toAvaliacao() {
	            Avaliacao avaliacao = new Avaliacao();
	            avaliacao.setDescricao(descricao);
	            avaliacao.setPeso(peso);
	            return avaliacao;
	        }
	    }


}
