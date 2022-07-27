package com.alura.agenda.modelo;

import java.sql.Timestamp;

import lombok.*;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp dia;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;
    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
}
