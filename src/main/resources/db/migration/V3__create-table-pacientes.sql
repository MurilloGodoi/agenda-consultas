CREATE TABLE IF NOT EXISTS pacientes (
    id bigint NOT NULL,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    cpf VARCHAR(45) NOT NULL,
    endereco_id bigint NOT NULL,
    PRIMARY KEY (id, endereco_id),
    UNIQUE INDEX email_UNIQUE (email ASC),
    UNIQUE INDEX cpf_UNIQUE (cpf ASC),
    FOREIGN KEY (endereco_id)
    REFERENCES enderecos (id));
