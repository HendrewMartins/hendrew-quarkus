package br.hendrew.quarkus.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.hendrew.quarkus.DTO.AlunosImportDTO;
import br.hendrew.quarkus.entity.Alunos;
import br.hendrew.quarkus.entity.AlunosEndereco_Auxiliar;
import br.hendrew.quarkus.entity.AlunosTelefone_Auxiliar;
import br.hendrew.quarkus.entity.Alunos_Auxiliar;
import br.hendrew.quarkus.exception.MenssageNotFoundException;
import br.hendrew.quarkus.exceptionhandler.ExceptionHandler;
import br.hendrew.quarkus.service.AlunosEnderecoService;
import br.hendrew.quarkus.service.AlunosService;
import br.hendrew.quarkus.service.AlunosTelefoneService;

@RequestScoped
@Path("/api/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunosController {

	private final AlunosService alunosService;
	private final AlunosEnderecoService enderecoService;
	private final AlunosTelefoneService telefoneService;

	@Inject
	public AlunosController(AlunosService alunosService, AlunosEnderecoService enderecoService,
			AlunosTelefoneService telefoneService) {
		this.alunosService = alunosService;
		this.enderecoService = enderecoService;
		this.telefoneService = telefoneService;

	}

	@GET
	@PermitAll
	@Operation(summary = "Listar Alunos", description = "Lista todos alunos")
	@APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))))
	public List<Alunos_Auxiliar> getAlunos() {
		List<Alunos_Auxiliar> lista = new ArrayList<Alunos_Auxiliar>();
		List<Alunos> aluno = alunosService.getAllAlunos();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 0; i < aluno.size(); i++) {
			Alunos_Auxiliar aux = new Alunos_Auxiliar();
			aux.setId(aluno.get(i).getId());
			aux.setNome(aluno.get(i).getNome());
			aux.setDt_nasc(formatador.format(aluno.get(i).getDt_nasc()));
			aux.setCpf(aluno.get(i).getCpf());
			aux.setMatricula(aluno.get(i).getMatricula());
			aux.setNm_mae(aluno.get(i).getNm_mae());
			aux.setNm_pai(aluno.get(i).getNm_pai());
			aux.setRg_aluno(aluno.get(i).getRg_aluno());
			lista.add(i, aux);
		}

		return lista;
	}

	@GET
	@PermitAll
	@Path("/page/{page}")
	@Operation(summary = "Listar Alunos", description = "Lista todos alunos")
	@APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))))
	public List<Alunos_Auxiliar> getPageAlunos(@PathParam("page") int pagina) throws MenssageNotFoundException {
		List<Alunos_Auxiliar> lista = new ArrayList<Alunos_Auxiliar>();

		// Paginação
		List<Alunos> aluno = alunosService.getAlunosPage(pagina, 5);
		;

		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 0; i < aluno.size(); i++) {
			Alunos_Auxiliar aux = new Alunos_Auxiliar();
			aux.setId(aluno.get(i).getId());
			aux.setNome(aluno.get(i).getNome());
			aux.setDt_nasc(formatador.format(aluno.get(i).getDt_nasc()));
			aux.setCpf(aluno.get(i).getCpf());
			aux.setMatricula(aluno.get(i).getMatricula());
			aux.setNm_mae(aluno.get(i).getNm_mae());
			aux.setNm_pai(aluno.get(i).getNm_pai());
			aux.setRg_aluno(aluno.get(i).getRg_aluno());
			lista.add(i, aux);
		}

		return lista;
	}

	@GET
	@PermitAll
	@Path("/id/{id}")
	@Operation(summary = "Pegar aluno", description = "Pesquisa por um ID o Aluno")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))),
			@APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
	public Alunos_Auxiliar getAluno(@PathParam("id") int id) throws MenssageNotFoundException {
		Alunos aluno = alunosService.getAlunosById(id);
		Alunos_Auxiliar aluno_aux = new Alunos_Auxiliar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		aluno_aux.setId(aluno.getId());
		aluno_aux.setNome(aluno.getNome());
		aluno_aux.setDt_nasc(formatador.format(aluno.getDt_nasc()));
		aluno_aux.setCpf(aluno.getCpf());
		aluno_aux.setMatricula(aluno.getMatricula());
		aluno_aux.setNm_mae(aluno.getNm_mae());
		aluno_aux.setNm_pai(aluno.getNm_pai());
		aluno_aux.setRg_aluno(aluno.getRg_aluno());
		aluno_aux.setAlunos_endereco(enderecoService.getEnderecoPorAluno(aluno.getId()));
		aluno_aux.setAlunos_telefone(telefoneService.getTelefonePorAluno(aluno.getId()));

		return aluno_aux;
	}

	@GET
	@PermitAll
	@Path("/count")
	@Operation(summary = "Pegar Quantidade dos Alunos", description = "Quantidade Repository Alunos")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
			@APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
	public long getQuantidade() throws MenssageNotFoundException {
		return alunosService.countAluno();
	}

	@GET
	@PermitAll
	@Path("/nome/{nome}")
	@Operation(summary = "Pegar aluno", description = "Pesquisa por um Nome do Aluno")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos_Auxiliar.class))),
			@APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
	public List<Alunos_Auxiliar> getAlunoData(@PathParam("nome") String nome) throws MenssageNotFoundException {
		List<Alunos_Auxiliar> lista = new ArrayList<Alunos_Auxiliar>();
		List<Alunos> aluno = alunosService.getAlunosByNome(nome);
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < aluno.size(); i++) {
			Alunos_Auxiliar aux = new Alunos_Auxiliar();
			aux.setId(aluno.get(i).getId());
			aux.setNome(aluno.get(i).getNome());
			aux.setDt_nasc(formatador.format(aluno.get(i).getDt_nasc()));
			aux.setCpf(aluno.get(i).getCpf());
			aux.setMatricula(aluno.get(i).getMatricula());
			aux.setNm_mae(aluno.get(i).getNm_mae());
			aux.setNm_pai(aluno.get(i).getNm_pai());
			aux.setRg_aluno(aluno.get(i).getRg_aluno());
			lista.add(i, aux);
		}

		return lista;
	}

	@POST
	@PermitAll
	@Path("/save")
	@Operation(summary = "Adicionar alunos", description = "Criar um novo aluno e persistir no banco")
	@APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))))
	public Alunos_Auxiliar createAluno(@Valid AlunosDto alunosDto) {
		Alunos aluno = alunosService.saveAlunos(alunosDto.toAluno());

		// grava os endereco
		if (alunosDto.alunos_endereco != null) {
			for (int x = 0; x < alunosDto.alunos_endereco.size(); x++) {
				AlunosEndereco_Auxiliar endAux = alunosDto.getAlunos_endereco().get(x);
				try {
					enderecoService.saveEndereco(endAux, aluno);
				} catch (MenssageNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		// grava os telefones
		if (alunosDto.alunos_telefone != null) {
			for (int x = 0; x < alunosDto.alunos_telefone.size(); x++) {
				AlunosTelefone_Auxiliar telAux = alunosDto.getAlunos_telefone().get(x);
				try {
					telefoneService.saveTelefone(telAux, aluno);
				} catch (MenssageNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		Alunos_Auxiliar aluno_aux = new Alunos_Auxiliar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		aluno_aux.setId(aluno.getId());
		aluno_aux.setNome(aluno.getNome());
		aluno_aux.setDt_nasc(formatador.format(aluno.getDt_nasc()));
		aluno_aux.setCpf(aluno.getCpf());
		aluno_aux.setMatricula(aluno.getMatricula());
		aluno_aux.setNm_mae(aluno.getNm_mae());
		aluno_aux.setNm_pai(aluno.getNm_pai());
		aluno_aux.setRg_aluno(aluno.getRg_aluno());

		return aluno_aux;
	}

	@POST
	@PermitAll
	@Consumes("multipart/form-data")
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/importar")
	@Operation(summary = "Adicionar alunos", description = "Criar um novo aluno e persistir no banco")
	@APIResponses(value = @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MultipartForm.class))))
	public List<Alunos_Auxiliar> importarAluno(@MultipartForm File imp) throws IOException {

		System.out.println();

		FileReader arq;
		BufferedReader lerArq;
		String linha = "";
		String arquivo = "";
		Boolean begin = false;
		Boolean end = false;
		try {
			arq = new FileReader(imp);
			lerArq = new BufferedReader(arq);
			linha = lerArq.readLine();

			while (linha != null) {

				int posicao_inicio = linha.indexOf('[');
				int posicao_fim = linha.indexOf(']');
				if (begin == false) {
					if (posicao_inicio >= 0) {
						begin = true;
						if (posicao_fim >= 0) {
							arquivo = arquivo + linha.substring(posicao_inicio, posicao_fim + 1);
							end = true;
						} else {
							arquivo = arquivo + linha.substring(posicao_inicio);
						}
					}
				} else {
					if ((linha != null) && (begin == true) && (end == false)) {
						if (posicao_fim >= 0) {
							arquivo = arquivo + linha.substring(0, posicao_fim + 1);
							end = true;
						} else {
							arquivo = arquivo + linha;
						}

					}
				}
				// lê da segunda até a última linha
				linha = lerArq.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<AlunosImportDTO> importDto = new ArrayList<AlunosImportDTO>();

		try {
			Type listType = new TypeToken<ArrayList<AlunosImportDTO>>(){}.getType();
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			importDto = gson.fromJson(arquivo, listType);
		} catch (JsonIOException err) {
			System.out.println("Exception : " + err.toString());
		}
		
		List<Alunos_Auxiliar> aux = new ArrayList<Alunos_Auxiliar>();

		for (int i = 0; i < importDto.size(); i++) {
			System.out.println(i);
			Alunos aluno = alunosService.saveAlunos(importDto.get(i).toAlunoImportDTO());
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			Alunos_Auxiliar aluno_auxiliar = new Alunos_Auxiliar();

			aluno_auxiliar.setId(aluno.getId());
			aluno_auxiliar.setNome(aluno.getNome());
			aluno_auxiliar.setDt_nasc(formatador.format(aluno.getDt_nasc()));
			aluno_auxiliar.setCpf(aluno.getCpf());
			aluno_auxiliar.setMatricula(aluno.getMatricula());
			aluno_auxiliar.setNm_mae(aluno.getNm_mae());
			aluno_auxiliar.setNm_pai(aluno.getNm_pai());
			aluno_auxiliar.setRg_aluno(aluno.getRg_aluno());

			aux.add(i, aluno_auxiliar);

		}
		System.out.println("fim");
		return aux;
	}

	@PUT
	@PermitAll
	@Path("/edit/{id}")
	@Operation(summary = "Atualizar um aluno", description = "Atualizar um aluno existente via id")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alunos.class))),
			@APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
	public Alunos_Auxiliar updateAluno(@PathParam("id") int id, @Valid AlunosDto alunosDto)
			throws MenssageNotFoundException {
		Alunos aluno = alunosService.updateAlunos(id, alunosDto.toAluno());

		enderecoService.deleteEnderecoAluno(id);
		telefoneService.deleteTelefoneAluno(id);

		// grava os endereco
		if (alunosDto.alunos_endereco != null) {
			for (int x = 0; x < alunosDto.alunos_endereco.size(); x++) {
				AlunosEndereco_Auxiliar endAux = alunosDto.getAlunos_endereco().get(x);
				try {
					enderecoService.saveEndereco(endAux, aluno);
				} catch (MenssageNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		// grava os telefones
		if (alunosDto.alunos_telefone != null) {
			for (int x = 0; x < alunosDto.alunos_telefone.size(); x++) {
				AlunosTelefone_Auxiliar telAux = alunosDto.getAlunos_telefone().get(x);
				try {
					telefoneService.saveTelefone(telAux, aluno);
				} catch (MenssageNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		Alunos_Auxiliar aluno_aux = new Alunos_Auxiliar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		aluno_aux.setId(aluno.getId());
		aluno_aux.setNome(aluno.getNome());
		aluno_aux.setDt_nasc(formatador.format(aluno.getDt_nasc()));
		aluno_aux.setCpf(aluno.getCpf());
		aluno_aux.setMatricula(aluno.getMatricula());
		aluno_aux.setNm_mae(aluno.getNm_mae());
		aluno_aux.setNm_pai(aluno.getNm_pai());
		aluno_aux.setRg_aluno(aluno.getRg_aluno());

		return aluno_aux;
	}

	@DELETE
	@PermitAll
	@Path("/delete/{id}")
	@Operation(summary = "Apagar a aluno", description = "Apagar um usuário pelo ID")
	@APIResponses(value = { @APIResponse(responseCode = "204", description = "Success"),
			@APIResponse(responseCode = "404", description = "Alunos not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
	public boolean deleteAluno(@PathParam("id") int id) throws MenssageNotFoundException {
		enderecoService.deleteEnderecoAluno(id);
		telefoneService.deleteTelefoneAluno(id);
		alunosService.deleteAluno(id);
		return true;
	}

	@Schema(name = "AlunosDTO", description = "DTO para Criar um novo Aluno")
	public static class AlunosDto {

		@Schema(title = "nome", required = true)
		private String nome;

		@Schema(title = "dt_nasc", required = true)
		private String dt_nasc;

		@Schema(title = "nm_mae", required = true)
		private String nm_mae;

		@Schema(title = "matricula", required = true)
		private String matricula;

		@Schema(title = "nm_pai", required = true)
		private String nm_pai;

		@Schema(title = "rg_aluno", required = true)
		private String rg_aluno;

		@Schema(title = "cpf", required = true)
		private String cpf;

		@Schema(title = "alunos_endereco")
		private List<AlunosEndereco_Auxiliar> alunos_endereco;

		@Schema(title = "alunos_telefone")
		private List<AlunosTelefone_Auxiliar> alunos_telefone;

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDt_nasc() {
			return dt_nasc;
		}

		public void setDt_nasc(String dt_nasc) {
			this.dt_nasc = dt_nasc;
		}

		public String getNm_mae() {
			return nm_mae;
		}

		public void setNm_mae(String nm_mae) {
			this.nm_mae = nm_mae;
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}

		public String getNm_pai() {
			return nm_pai;
		}

		public void setNm_pai(String nm_pai) {
			this.nm_pai = nm_pai;
		}

		public String getRg_aluno() {
			return rg_aluno;
		}

		public void setRg_aluno(String rg_aluno) {
			this.rg_aluno = rg_aluno;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public List<AlunosEndereco_Auxiliar> getAlunos_endereco() {
			return alunos_endereco;
		}

		public void setAlunos_endereco(List<AlunosEndereco_Auxiliar> alunos_endereco) {
			this.alunos_endereco = alunos_endereco;
		}

		public List<AlunosTelefone_Auxiliar> getAlunos_telefone() {
			return alunos_telefone;
		}

		public void setAlunos_telefone(List<AlunosTelefone_Auxiliar> alunos_telefone) {
			this.alunos_telefone = alunos_telefone;
		}

		public Alunos toAluno() {
			SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

			String dia = dt_nasc.substring(0, 2);
			String mes = dt_nasc.substring(3, 5);
			String ano = dt_nasc.substring(6, 10);
			String data_format = ano + "-" + mes + "-" + dia;

			Alunos alunos = new Alunos();

			alunos.setNome(nome);
			java.util.Date util_Date = new java.util.Date();
			try {
				util_Date = formatador.parse(data_format);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sql_Date = new java.sql.Date(util_Date.getTime());

			alunos.setDt_nasc(sql_Date);
			alunos.setCpf(cpf);
			alunos.setMatricula(matricula);
			alunos.setNm_mae(nm_mae);
			alunos.setNm_pai(nm_pai);
			alunos.setRg_aluno(rg_aluno);

			return alunos;
		}
	}

}
