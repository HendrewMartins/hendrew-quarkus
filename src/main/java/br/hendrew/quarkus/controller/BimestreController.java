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

import br.hendrew.quarkus.convertion.BimestreConvertion;
import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.Bimestre;
import br.hendrew.quarkus.entity.Bimestre_Angular;
import br.hendrew.quarkus.entity.Bimestre_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.BimestreService;

@RequestScoped
@Path("/api/bimestre")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BimestreController {

    private final BimestreService bimestreService;
    BimestreConvertion convertion;

    @Inject
    public BimestreController(BimestreService bimestreService) {
        this.bimestreService = bimestreService;
    }

    @GET
    @PermitAll
    @Operation(summary = "Listar Bimestre", description = "Lista todas Bimestre")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre_Auxiliar.class))))
    public List<Bimestre_Auxiliar> getBimestre() {
        return bimestreService.getAllBimestreNmAluno();
    }

    @GET
    @PermitAll
    @Path("/page/{page}")
    @Operation(summary = "Listar Bimestre por Pagina", description = "Lista todas Bimestre por Pagina")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre_Auxiliar.class))))
    public List<Bimestre_Auxiliar> getPageBimestre(@PathParam("page") int pagina) throws MenssageNotFoundException {
        return bimestreService.getAllBimestreNmAlunoPage(pagina, 5);
    }

    @GET
    @PermitAll
    @Path("/id/{id}")
    @Operation(summary = "Pegar Bimestre", description = "Pesquisa por um ID a Bimestre")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre.class))),
            @APIResponse(responseCode = "404", description = "Bimestre not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Bimestre_Angular getBimestre(@PathParam("id") int id) throws MenssageNotFoundException {
        return bimestreService.getBimestreAngById(id);
    }

    @GET
    @PermitAll
    @Path("/aluno/{id}")
    @Operation(summary = "Pegar Bimestre por Aluno", description = "Pesquisa por um ID a Bimestre")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre.class))),
            @APIResponse(responseCode = "404", description = "Bimestre not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<Bimestre_Angular> getAlunoBimestre(@PathParam("id") int id) throws MenssageNotFoundException {
        return bimestreService.getBimestrePorAluno_Angular(id);
    }

    @GET
    @PermitAll
    @Path("/nota/aluno/{id}")
    @Operation(summary = "Pegar Bimestre por Aluno", description = "Pesquisa por um ID a Bimestre")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre.class))),
            @APIResponse(responseCode = "404", description = "Bimestre not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<Bimestre_Auxiliar> getNotaAlunoBimestre(@PathParam("id") int id) throws MenssageNotFoundException {
        return bimestreService.getNotaBimestrePorAluno(id);
    }

    @POST
    @PermitAll
    @Path("/save")
    @Operation(summary = "Adicionar a Bimestre", description = "Create um Bimestre e persistir no banco")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre_Angular.class))))
    public Bimestre createBimestre(@Valid BimestreDto bimestreDto) throws MenssageNotFoundException {
        return bimestreService.saveBimestre(bimestreDto.toBimestre(), bimestreDto.getId_Aluno());
    }

    @PUT
    @PermitAll
    @Path("/edit/{id}")
    @Operation(summary = "Atualizar um Bimestre", description = "Atualizar um Bimestre existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bimestre_Angular.class))),
            @APIResponse(responseCode = "404", description = "Bimestre not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Bimestre updateBimestre(@PathParam("id") int id, @Valid BimestreDto bimestreDto)
            throws MenssageNotFoundException {
        return bimestreService.updateBimestre(id, bimestreDto.toBimestre(), bimestreDto.getId_Aluno());
    }

    @DELETE
    @PermitAll
    @Path("/delete/{id}")
    @Operation(summary = "Apagar Bimestre", description = "Apagar um Bimestre pelo ID")
    @APIResponses(value = { @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description = "Bimestre not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Boolean deleteBimestre(@PathParam("id") int id) throws MenssageNotFoundException {

        bimestreService.deleteBimestre(id);
        return true;
    }

    @GET
    @PermitAll
    @Path("/count")
    @Operation(summary = "Pegar Quantidade dos Bimestre", description = "Quantidade Repository Bimestre")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @APIResponse(responseCode = "404", description = "Bimestre not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public long getQuantidade() throws MenssageNotFoundException {
        return bimestreService.countBimestre();
    }

    @Schema(name = "BimestreDTO", description = "DTO para Criar um novo Bimestre")
    public static class BimestreDto {

        @Schema(title = "bimestre", required = true)
        private long bimestre;

        @Schema(title = "ano", required = true)
        private long ano;

        @Schema(title = "faltas", required = true)
        private long faltas;

        @Schema(title = "id_Aluno", required = true)
        private Alunos id_Aluno;

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

        public Alunos getId_Aluno() {
            return id_Aluno;
        }

        public void setId_Aluno(Alunos id_Aluno) {
            this.id_Aluno = id_Aluno;
        }

        public Bimestre_Angular toBimestre() {
            Bimestre_Angular bimestre_angular = new Bimestre_Angular();
            bimestre_angular.setBimestre(this.bimestre);
            bimestre_angular.setAno(this.ano);
            bimestre_angular.setFaltas(this.faltas);
            bimestre_angular.setId_Aluno(this.id_Aluno);
            return bimestre_angular;
        }
    }

}
