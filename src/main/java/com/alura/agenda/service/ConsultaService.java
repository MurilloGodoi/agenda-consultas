package com.alura.agenda.service;

import com.alura.agenda.dto.ConsultaDoDiaDto;
import com.alura.agenda.dto.ConsultaDto;
import com.alura.agenda.dto.ConsultaFormDto;
import com.alura.agenda.modelo.Consulta;
import com.alura.agenda.modelo.StatusConsulta;
import com.alura.agenda.projection.ConsultaDoDiaProjection;
import com.alura.agenda.projection.ConsultasDaSemanaProjection;
import com.alura.agenda.repository.ConsultaRepository;
import com.alura.agenda.repository.MedicoRepository;
import com.alura.agenda.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
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
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Consulta consulta = modelMapper.map(dto, Consulta.class);

        if (Objects.isNull(dto.getIdMedico())) {
            Long idMedicoAleatorio = escolheMedicoAleatorio(dto.getDia());
            consulta.setMedico(medicoRepository.findById(idMedicoAleatorio).get());
        } else {
            if (medicoPodeAtender(dto.getDia(), dto.getIdMedico())) {
                consulta.setMedico(medicoRepository.findById(dto.getIdMedico()).get());
            } else {
                throw  new IllegalArgumentException("O médico não pode atender neste horário");
            }
        }

        if(pacientePodeMarcarConsulta(dto.getIdPaciente(), dto.getDia().toString().split(" ")[0])) {
            consulta.setPaciente(pacienteRepository.findById(dto.getIdPaciente()).get());
        }
        else {
            throw new IllegalArgumentException("O paciente não pode marcar consulta neste dia");
        }

        consulta.setStatus(StatusConsulta.AGENDADA);
        return modelMapper.map(consultaRepository.save(consulta), ConsultaDto.class);
    }

    @Transactional
    public ConsultaDto cancelar(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(consultaRepository.quantidadeHorasDiferencaEntreDiaCancelamentoEDiaConsulta(id) >= 24) {
            consulta.setStatus(StatusConsulta.CANCELADA);
            return modelMapper.map(consultaRepository.save(consulta), ConsultaDto.class);
        } else {
            throw new IllegalArgumentException("Não é possível cancelar uma consulta com menos de 24 horas de antecedência");
        }
    }

    public List<ConsultaDoDiaProjection> listarConsultasDoDiaAtual() {
        return consultaRepository.consultasDoDiaAtual();
    }

    public List<ConsultasDaSemanaProjection> listarConsultasDaSemana() {
        return consultaRepository.consultasDaSemanaAtual();
    }

    public Long escolheMedicoAleatorio(Timestamp diaHoraConsulta) {
        Long idMedico = consultaRepository.iDMedicoAleatorio();

        if(medicoPodeAtender(diaHoraConsulta, idMedico)) {
            return idMedico;
        } else {
            return escolheMedicoAleatorio(diaHoraConsulta);
        }
    }

    public Boolean medicoPodeAtender(Timestamp diaHoraConsulta, Long idMedico) {
        medicoRepository.findById(idMedico).orElseThrow(EntityNotFoundException::new);

        if (consultaRepository.MedicoAtendeNoHorarioDaConsulta(diaHoraConsulta.toString().split(" ")[1], idMedico) == 0) {
            return false;
        }

        if (consultaRepository.quantidadeConsultasEmUmDiaHorarioEspecifico(idMedico, diaHoraConsulta) > 0) {
            return false;
        }

        if (consultaRepository.quantidadeConsultasMedicoEmUmDiaEspecifico(idMedico, diaHoraConsulta.toString().split(" ")[0]).intValue() > 12) {
            return false;
        }

        return true;
    }

    public Boolean pacientePodeMarcarConsulta(Long idPaciente, String diaConsulta) {
        pacienteRepository.findById(idPaciente).orElseThrow(EntityNotFoundException::new);
        if(consultaRepository.quantidadeConsultasPacienteEmUmDiaEspecifico(idPaciente, diaConsulta).intValue() > 0) {
            return false;
        }
        return true;
    }
}
