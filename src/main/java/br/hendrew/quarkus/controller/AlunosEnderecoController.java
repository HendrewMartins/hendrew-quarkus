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

import br.hendrew.quarkus.entity.AlunosEndereco;
import br.hendrew.quarkus.entity.AlunosEndereco_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.AlunosEnderecoService;

@RequestScoped
@Path("/api/alunosendereco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosEnderecoController {

    private final AlunosEnderecoService enderecoService;

    @Inject
    public AlunosEnderecoController(AlunosEnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GET
    @PermitAll
    @Path("/lista/{id}")
    @Operation(summary = "Pegar Endereços por Aluno", description = "Pesquisar Endereços por Alunos")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlunosEndereco.class))),
            @APIResponse(responseCode = "404", description = "Endereços not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<AlunosEndereco_Auxiliar> getEnderecosAluno(@PathParam("id") int id) throws MenssageNotFoundException {
        return enderecoService.getEnderecoPorAluno(id);
    }

    @POST
    @PermitAll
    @Path("/save/{id}")
    @Operation(summary = "Adicionar a Endereço", description = "Create um Endereco e persistir no banco")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlunosEndereco_Auxiliar.class))))
    public AlunosEndereco createEndereco(@PathParam("id") int id, @Valid EnderecoDto enderecoDto)
            throws MenssageNotFoundException {
        return enderecoService.saveEndereco(enderecoDto.toEndereco(), id);
    }

    @Schema(name = "EnderecoDTO", description = "DTO para Criar um novo Endereco")
    public static class EnderecoDto {

        @Schema(title = "sequencia", required = true)
        private long sequencia;

        @Schema(title = "tipo", required = true)
        private String tipo;

        @Schema(title = "logradouro", required = true)
        private String logradouro;

        @Schema(title = "numero", required = true)
        private String numero;

        @Schema(title = "cep", required = true)
        private String cep;

        @Schema(title = "bairro", required = true)
        private String bairro;

        @Schema(title = "complemento", required = true)
        private String complemento;

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

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }
       
        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public AlunosEndereco_Auxiliar toEndereco() {
            AlunosEndereco_Auxiliar angular = new AlunosEndereco_Auxiliar();
            angular.setBairro(this.bairro);
            angular.setCep(this.cep);
            angular.setComplemento(this.complemento);
            angular.setLogradouro(this.logradouro);
            angular.setNumero(this.numero);
            angular.setSequencia(this.sequencia);
            angular.setTipo(this.tipo);
            return angular;
        }
    }
}
