package com.alura.agenda.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
@Getter
@Setter
public class ConsultaDoDiaDto {
    String nomeMedico;
    String nomePaciente;
    Time horario;

    public ConsultaDoDiaDto(String nomeMedico, String nomePaciente, Time horario) {
        this.nomeMedico = nomeMedico;
        this.nomePaciente = nomePaciente;
        this.horario = horario;
    }
}
