package com.alura.agenda.dto;

import com.alura.agenda.modelo.Medico;
import com.alura.agenda.modelo.Paciente;
import com.alura.agenda.modelo.StatusConsulta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDto {
    private Long id;
    private Timestamp dia;
    private String descricao;
    private StatusConsulta status;
    private Medico medico;
    private Paciente paciente;
}
