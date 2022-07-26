package com.alura.agenda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDto {
    private Long id;
    private String nomeMedico;
    private String nomePaciente;
    private String hora;
}
