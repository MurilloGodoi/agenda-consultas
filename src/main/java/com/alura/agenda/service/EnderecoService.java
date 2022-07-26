package com.alura.agenda.service;

import com.alura.agenda.dto.EnderecoFormDto;
import com.alura.agenda.modelo.Endereco;
import com.alura.agenda.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Endereco cadastrar(EnderecoFormDto dto) {
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        return enderecoRepository.save(endereco);
    }
}
