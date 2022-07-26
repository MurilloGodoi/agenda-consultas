CREATE TABLE IF NOT EXISTS enderecos (
    id bigint NOT NULL AUTO_INCREMENT,
    logradouro VARCHAR(45) NOT NULL,
    numero VARCHAR(45) NOT NULL,
    bairro VARCHAR(45) NOT NULL,
    cidade VARCHAR(45) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep INT NOT NULL,
    PRIMARY KEY (id));
