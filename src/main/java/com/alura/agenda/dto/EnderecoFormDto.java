package com.alura.agenda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class EnderecoFormDto {
    @NotNull
    private String logradouro;
    @NotNull
    private String numero;
    @NotNull
    private String bairro;
    @NotNull
    private String cidade;
    @NotNull
    private String uf;
    @NotNull
    private Integer cep;
}
