package com.alura.agenda.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoFormDto {
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioAbertura;
    @JsonAlias("horario_fechamento")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioFechamento;
    @NotNull
    private EnderecoFormDto endereco;
}
