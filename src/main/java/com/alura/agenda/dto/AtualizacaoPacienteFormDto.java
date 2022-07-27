package com.alura.agenda.dto;

import com.alura.agenda.modelo.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoPacienteFormDto{
    @NotNull
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String telefone;
    @NotNull
    private String cpf;
    @NotNull
    private Endereco endereco;
}

