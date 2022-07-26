package com.alura.agenda.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaFormDto {
    @JsonAlias("paciente_id")
    @NotNull
    private Long idPaciente;
    @JsonAlias("medico_id")
    private Long idMedico;
    @NotNull
    private String dia;
    @NotNull
    private String descricao;
}
