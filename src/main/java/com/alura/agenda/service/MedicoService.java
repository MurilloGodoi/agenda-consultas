package com.alura.agenda.service;

import com.alura.agenda.dto.AtualizacaoMedicoFormDto;
import com.alura.agenda.dto.EnderecoFormDto;
import com.alura.agenda.dto.MedicoDto;
import com.alura.agenda.dto.MedicoFormDto;
import com.alura.agenda.modelo.Endereco;
import com.alura.agenda.modelo.Medico;
import com.alura.agenda.repository.EnderecoRepository;
import com.alura.agenda.repository.MedicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ModelMapper modelMapper;

    public Page<MedicoDto> listar(Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(medico -> modelMapper.map(medico, MedicoDto.class));
    }

    @Transactional
    public MedicoDto cadastrar(MedicoFormDto dto) {
        if (medicoRepository.contaMedicosPorEmailOuCrm(dto.getEmail(), dto.getCrm()) > 0) {
            throw new EntityExistsException("Médico já cadastrado");
        }
        Medico medico = modelMapper.map(dto, Medico.class);
        medico.setEndereco(enderecoService.cadastrar(dto.getEndereco()));
        return modelMapper.map(medicoRepository.save(medico), MedicoDto.class);
    }

    @Transactional
    public MedicoDto atualizar(AtualizacaoMedicoFormDto dto) {
        Medico medico = medicoRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException());
        Endereco endereco = enderecoRepository.findById(medico.getEndereco().getId()).orElseThrow(() -> new EntityNotFoundException());
        medico.atualizarInformacoes(dto);
        endereco.atualizarInformacoes(dto.getEndereco());
        enderecoService.cadastrar(dto.getEndereco());
        return modelMapper.map(medicoRepository.save(medico), MedicoDto.class);
    }

    @Transactional
    public void excluir(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        Long idEndereco = medico.getEndereco().getId();
        medicoRepository.deleteById(id);
        enderecoRepository.deleteById(idEndereco);
    }




}
