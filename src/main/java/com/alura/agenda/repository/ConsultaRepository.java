package com.alura.agenda.repository;

import com.alura.agenda.modelo.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    // TODO - Criar o método para verificar a antecedência mínima de 24 horas para cancelamento de consultas
    // TODO - Criar o método para verificar se o usuário já possui uma consulta em um dia
    // TODO - Criar o método para verificar se o médico já possuí 12 consultas em um dia
    // TODO - Criar o método para verificar se a data/horário bate com o horário do medico e se esse horário está livre
    // TODO - Criar o método para escolher um médico randomicamente e verificar se a data/horário bate com o horário do medico e se esse horário está livre
    // TODO - Criar o método para buscar as consultas do dia atual
    // TODO - Criar o método para buscar as consultas dos próximos 7 dias
}

