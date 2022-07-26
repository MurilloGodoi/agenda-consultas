package com.alura.agenda.modelo;

import com.alura.agenda.dto.AtualizacaoMedicoFormDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;

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
    private Time horarioAbertura;
    @Column(name = "horario_fechamento")
    private Time horarioFechamento;
    @ManyToOne
    private Endereco endereco;

    public void atualizarInformacoes(AtualizacaoMedicoFormDto dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.telefone = dto.getTelefone();
        this.crm = dto.getCrm();
        this.horarioAbertura = Time.valueOf(dto.getHorarioAbertura());
        this.horarioFechamento = Time.valueOf(dto.getHorarioFechamento());
        this.endereco = new Endereco(dto.getEndereco());
    }
}
