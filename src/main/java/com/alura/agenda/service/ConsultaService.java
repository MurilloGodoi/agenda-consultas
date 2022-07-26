package com.alura.agenda.service;

import com.alura.agenda.dto.ConsultaDto;
import com.alura.agenda.dto.ConsultaFormDto;
import com.alura.agenda.modelo.Consulta;
import com.alura.agenda.modelo.StatusConsulta;
import com.alura.agenda.repository.ConsultaRepository;
import com.alura.agenda.repository.MedicoRepository;
import com.alura.agenda.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ConsultaDto cadastrar(ConsultaFormDto dto) {
        // TODO - Esse map tá com problemas, verificar o que está errado
        Consulta consulta = modelMapper.map(dto, Consulta.class);
        if (Objects.isNull(dto.getIdMedico())) {
            // TODO - chamar aqui o método para escolher um médico aleatoriamente verificando o horário dele
            consulta.setMedico(medicoRepository.findById(dto.getIdMedico()).get());
        } else {
            // TODO - Limite de doze consultas por médico por dia
            // TODO - Validar horários do médico antes de marcar
            consulta.setMedico(medicoRepository.findById(dto.getIdMedico()).get());
        }
        // TODO - Limite de uma consulta por paciente

        consulta.setPaciente(pacienteRepository.findById(dto.getIdPaciente()).get());
        consulta.setStatus(StatusConsulta.PENDENTE);


        return modelMapper.map(consultaRepository.save(consulta), ConsultaDto.class);
    }

    @Transactional
    public ConsultaDto cancelar(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        // TODO - Validar as 24 horas de antecedência para cancelar uma consulta
        consulta.setStatus(StatusConsulta.CANCELADA);
        return modelMapper.map(consultaRepository.save(consulta), ConsultaDto.class);
    }

    //TODO - Criar o método para listar  as consultas do dia atual

    // TODO - Criar o método para listar as consultas dos próximos 7 dias
}
