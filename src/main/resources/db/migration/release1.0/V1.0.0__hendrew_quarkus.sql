CREATE TABLE alunos
(
    id bigint NOT NULL,
    nome character varying(255),
    dt_nasc date,
    CONSTRAINT alunos_pkey PRIMARY KEY (id)
);

CREATE TABLE avaliacao
(
    id bigint NOT NULL,
    descricao character varying(255),
    peso double precision,
    CONSTRAINT avaliacao_pkey PRIMARY KEY (id)
);

CREATE TABLE bimestre_new
(
    id bigint NOT NULL,
    bimestre bigint NOT NULL,
    ano bigint NOT NULL,
    faltas bigint NOT NULL,
    id_alunos bigint NOT NULL,
    CONSTRAINT bimestre_new_pk PRIMARY KEY (id),
    CONSTRAINT bimestre_new_fk FOREIGN KEY (id_alunos)
        REFERENCES alunos (id)
);

CREATE TABLE notas_new
(
    id bigint NOT NULL,
    nota double precision NOT NULL,
    id_avaliacao bigint,
    id_bimestre bigint,
    CONSTRAINT notas_new_pkey PRIMARY KEY (id),
    CONSTRAINT fk4h80glp6irrd2de9nol1xj8wa FOREIGN KEY (id_avaliacao)
        REFERENCES avaliacao (id),
    CONSTRAINT fkryovf6h3hhj9r2ldd0xwnr2ee FOREIGN KEY (id_bimestre)
        REFERENCES public.bimestre_new (id) 
);