package com.alura.agenda.dto;

import com.alura.agenda.modelo.Endereco;
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
public class AtualizacaoMedicoFormDto{
    @NotNull
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String telefone;
    @NotNull
    private String crm;
    @JsonAlias("horario_abertura")
    @NotNull
    private String horarioAbertura;
    @JsonAlias("horario_fechamento")
    @NotNull
    private String horarioFechamento;
    @NotNull
    private Endereco endereco;
}

