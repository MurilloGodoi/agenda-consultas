CREATE TABLE IF NOT EXISTS medicos (
    id bigint NOT NULL,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    crm VARCHAR(15) NOT NULL,
    horario_abertura TIME NOT NULL,
    horario_fechamento TIME NOT NULL,
    endereco_id bigint NOT NULL,
    PRIMARY KEY (id, endereco_id),
    UNIQUE INDEX email_UNIQUE (email ASC),
    UNIQUE INDEX crm_UNIQUE (crm ASC),
    FOREIGN KEY (endereco_id)
    REFERENCES enderecos (id));
