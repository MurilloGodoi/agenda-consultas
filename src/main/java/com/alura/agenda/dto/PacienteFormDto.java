package com.alura.agenda.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteFormDto {
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String telefone;
    @NotNull
    private String cpf;
    @NotNull
    private EnderecoFormDto endereco;
}
