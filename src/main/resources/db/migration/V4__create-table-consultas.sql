CREATE TABLE IF NOT EXISTS consultas (
    id bigint NOT NULL AUTO_INCREMENT,
    dia DATETIME NOT NULL,
    descricao VARCHAR(45) NOT NULL,
    status VARCHAR(45) NOT NULL,
    medico_id bigint NOT NULL,
    paciente_id bigint NOT NULL,
    PRIMARY KEY (id, medico_id, paciente_id),
    FOREIGN KEY (medico_id)
    REFERENCES medicos (id),
    FOREIGN KEY (paciente_id)
    REFERENCES pacientes (id));
