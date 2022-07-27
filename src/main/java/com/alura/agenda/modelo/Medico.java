package com.alura.agenda.modelo;

import com.alura.agenda.dto.AtualizacaoMedicoFormDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Column(name = "horario_abertura")
    private LocalTime horarioAbertura;
    @Column(name = "horario_fechamento")
    private LocalTime horarioFechamento;
    @ManyToOne(cascade=CascadeType.MERGE)
    private Endereco endereco;

    public void atualizarInformacoes(AtualizacaoMedicoFormDto dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.telefone = dto.getTelefone();
        this.crm = dto.getCrm();
        this.horarioAbertura = LocalTime.parse(dto.getHorarioAbertura());
        this.horarioFechamento = LocalTime.parse(dto.getHorarioFechamento());
        this.endereco = dto.getEndereco();
    }
}
