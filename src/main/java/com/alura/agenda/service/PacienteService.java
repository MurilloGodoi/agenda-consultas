package com.alura.agenda.service;

import com.alura.agenda.dto.AtualizacaoPacienteFormDto;
import com.alura.agenda.dto.PacienteDto;
import com.alura.agenda.dto.PacienteFormDto;
import com.alura.agenda.modelo.Endereco;
import com.alura.agenda.modelo.Paciente;
import com.alura.agenda.repository.EnderecoRepository;
import com.alura.agenda.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PacienteDto> listar(Pageable paginacao) {
        return pacienteRepository.findAll(paginacao).map(paciente -> modelMapper.map(paciente, PacienteDto.class));
    }

    @Transactional
    public PacienteDto cadastrar(PacienteFormDto dto) {
        if( pacienteRepository.contaPacientesPorEmailOuCpf(dto.getEmail(), dto.getCpf()) > 0 ) {
            throw new EntityExistsException("Paciente já cadastrado");
        }
        Paciente paciente = modelMapper.map(dto, Paciente.class);
        paciente.setEndereco(enderecoService.cadastrar(dto.getEndereco()));
        return modelMapper.map(pacienteRepository.save(paciente), PacienteDto.class);
    }

    @Transactional
    public PacienteDto atualizar(AtualizacaoPacienteFormDto dto) {
        Paciente paciente = pacienteRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        Endereco endereco = enderecoRepository.findById(paciente.getEndereco().getId()).orElseThrow(EntityNotFoundException::new);
        paciente.atualizarInformacoes(dto);
        endereco.atualizarInformacoes(dto.getEndereco());
        enderecoService.cadastrar(dto.getEndereco());
        return modelMapper.map(pacienteRepository.save(paciente), PacienteDto.class);
    }

    @Transactional
    public void excluir(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Long idEndereco = paciente.getEndereco().getId();
        pacienteRepository.deleteById(id);
        enderecoRepository.deleteById(idEndereco);
    }
}
