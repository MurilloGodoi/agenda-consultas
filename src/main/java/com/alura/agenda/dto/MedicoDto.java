package com.alura.agenda.dto;

import com.alura.agenda.modelo.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

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
    private Time horarioAbertura;
    private Time horarioFechamento;
    private Endereco endereco;
}
