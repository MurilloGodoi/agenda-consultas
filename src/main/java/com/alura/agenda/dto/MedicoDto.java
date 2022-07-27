package com.alura.agenda.dto;

import com.alura.agenda.modelo.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private Endereco endereco;
}
