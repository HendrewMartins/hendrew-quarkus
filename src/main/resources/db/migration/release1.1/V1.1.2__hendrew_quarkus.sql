ALTER TABLE alunos
    ADD COLUMN matricula character varying(15); 
ALTER TABLE alunos
    ADD COLUMN nm_pai character varying(100);
ALTER TABLE alunos
    ADD COLUMN rg_aluno character varying(15);
ALTER TABLE alunos
    ADD COLUMN cpf character varying(20);

CREATE TABLE alunos_telefone
(
    sequencia bigint NOT NULL,
    id_aluno bigint NOT NULL,
    tipo bigint,
    numero character varying(20) COLLATE pg_catalog."default",
    contato character varying(60) COLLATE pg_catalog."default",
    CONSTRAINT alunostelefone_pkey PRIMARY KEY (sequencia,id_aluno),
    CONSTRAINT alunostelefone_fkey FOREIGN KEY (id_aluno) REFERENCES alunos(id)
);