package br.hendrew.quarkus.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import br.hendrew.quarkus.entity.AlunosTelefone;
import br.hendrew.quarkus.entity.AlunosTelefone_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.AlunosTelefoneService;

@RequestScoped
@Path("/api/alunostelefone")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosTelefoneController {

    private final AlunosTelefoneService telefoneService;

    @Inject
    public AlunosTelefoneController(AlunosTelefoneService telefoneService) {
        this.telefoneService = telefoneService;
    }

    @GET
    @PermitAll
    @Path("/lista/{id}")
    @Operation(summary = "Pegar Telefones por Aluno", description = "Pesquisar Telefones por Alunos")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlunosTelefone.class))),
            @APIResponse(responseCode = "404", description = "Endereços not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<AlunosTelefone_Auxiliar> getTelefoneAluno(@PathParam("id") int id) throws MenssageNotFoundException {
        return telefoneService.getTelefonePorAluno(id);
    }

    @POST
    @PermitAll
    @Path("/save/{id}")
    @Operation(summary = "Adicionar a Telefones", description = "Create um Telefone e persistir no banco")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlunosTelefone_Auxiliar.class))))
    public AlunosTelefone createEndereco(@PathParam("id") int id, @Valid TelefoneDto telefoneDto)
            throws MenssageNotFoundException {
        return telefoneService.saveTelefone(telefoneDto.toTelefone(), id);
    }

    @Schema(name = "TelefoneDTO", description = "DTO para Criar um novo Telefone")
    public static class TelefoneDto {

        @Schema(title = "sequencia", required = true)
        private long sequencia;

        @Schema(title = "tipo", required = true)
        private String tipo;

        @Schema(title = "numero", required = true)
        private String numero;

        @Schema(title = "contato", required = true)
        private String contato;

        public long getSequencia() {
            return sequencia;
        }

        public void setSequencia(long sequencia) {
            this.sequencia = sequencia;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getContato() {
            return contato;
        }

        public void setContato(String contato) {
            this.contato = contato;
        }

        public AlunosTelefone_Auxiliar toTelefone() {
            AlunosTelefone_Auxiliar angular = new AlunosTelefone_Auxiliar();
            angular.setContato(this.contato);
            angular.setNumero(this.numero);
            angular.setSequencia(this.sequencia);
            angular.setTipo(this.tipo);
            return angular;
        }
    }
}
