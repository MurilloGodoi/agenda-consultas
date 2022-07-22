package com.alura.agenda.modelo;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table(name = "enderecos")

public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private char uf;
    private Integer cep;
}
