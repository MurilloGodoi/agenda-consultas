package com.alura.agenda.modelo;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table(name = "pacientes")

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @ManyToOne
    private Endereco endereco;

}
