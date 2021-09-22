CREATE TABLE alunos_endereco
(
    sequencia bigint NOT NULL,
    id_aluno bigint NOT NULL,
    tipo bigint,
    logradouro character varying(100) COLLATE pg_catalog."default",
    numero character varying(10) COLLATE pg_catalog."default",
    cep character varying(10) COLLATE pg_catalog."default",
    bairro character varying(100) COLLATE pg_catalog."default",
    complemento character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT alunosendereco_pkey PRIMARY KEY (sequencia,id_aluno),
    CONSTRAINT alunosendereco_fkey FOREIGN KEY (id_aluno) REFERENCES alunos(id)
);

