package br.hendrew.quarkus.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.hendrew.quarkus.entity.Alunos;

@Schema(name = "AlunosImportDTO", description = "DTO para Criar um novo Aluno para Import")
public class AlunosImportDTO {

        @Schema(title = "first_name", required = true)
		private String first_name;

        @Schema(title = "last_name", required = true)
		private String last_name;

        @Schema(title = "birth", required = true)
		private String birth;

		@Schema(title = "mom", required = true)
		private String mom;

		@Schema(title = "dad", required = true)
		private String dad;

		@Schema(title = "record", required = true)
		private String record;


		public String getFirst_Name() {
			return first_name;
		}

		public void setFirst_Name(String first_Name) {
			this.first_name = first_Name;
		}

        public String getLast_Name() {
			return last_name;
		}

		public void setLast_Name(String last_Name) {
			this.last_name = last_Name;
		}

		public String getBirth() {
			return birth;
		}

		public void setBirth(String birth) {
			this.birth = birth;
		}

		public String getMom() {
			return mom;
		}

		public void setMom(String mom) {
			this.mom = mom;
		}

		public String getRecord() {
			return record;
		}

		public void setRecord(String record) {
			this.record = record;
		}

		public String getDad() {
			return dad;
		}

		public void setDad(String dad) {
			this.dad = dad;
		}

		
		public Alunos toAlunoImportDTO() {
			SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
			
            String data_format = birth;


			Alunos alunos = new Alunos();

			alunos.setNome(first_name+last_name);
			java.util.Date util_Date = new java.util.Date();
			try {
				util_Date = formatador.parse(data_format);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sql_Date = new java.sql.Date(util_Date.getTime());

			alunos.setDt_nasc(sql_Date);
			//alunos.setCpf(cpf);
			alunos.setMatricula(record);
			alunos.setNm_mae(mom);
			alunos.setNm_pai(dad);
			//alunos.setRg_aluno(rg_aluno);

			return alunos;
		}
    
}
