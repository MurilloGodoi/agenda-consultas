package com.alura.agenda.modelo;

import com.alura.agenda.dto.AtualizacaoPacienteFormDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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

    public void atualizarInformacoes(AtualizacaoPacienteFormDto dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.telefone = dto.getTelefone();
        this.cpf = dto.getCpf();
        this.endereco = new Endereco(dto.getEndereco());
    }
}
