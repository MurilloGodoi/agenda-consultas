package com.alura.agenda.modelo;

import com.alura.agenda.dto.EnderecoFormDto;
import lombok.*;

import javax.persistence.*;

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
    private String uf;
    private Integer cep;

    public Endereco(EnderecoFormDto dto) {
        this.logradouro = dto.getLogradouro();
        this.numero = dto.getNumero();
        this.bairro = dto.getBairro();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
        this.cep = dto.getCep();
    }

    public void atualizarInformacoes(EnderecoFormDto endereco) {
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.cep = endereco.getCep();
    }
}
