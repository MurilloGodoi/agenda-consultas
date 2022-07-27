package com.alura.agenda.repository;

import com.alura.agenda.modelo.Consulta;
import com.alura.agenda.projection.ConsultaDoDiaProjection;
import com.alura.agenda.projection.ConsultasDaSemanaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;


public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query(value = "SELECT timestampdiff(HOUR, CURRENT_TIMESTAMP, c.dia) FROM consultas c where c.id = :id", nativeQuery = true)
    public Integer quantidadeHorasDiferencaEntreDiaCancelamentoEDiaConsulta(Long id);

    @Query(value = "SELECT COUNT(c.id) FROM consultas c where DATE(c.dia) = :dia AND c.status = 'AGENDADA' AND c.paciente_id = :idPaciente", nativeQuery = true)
    public Integer quantidadeConsultasPacienteEmUmDiaEspecifico(Long idPaciente, String dia);

    @Query(value = "SELECT COUNT(c.id) FROM consultas c where DATE(c.dia) = :dia AND c.status = 'AGENDADA' AND c.medico_id = :idMedico", nativeQuery = true)
    public Integer quantidadeConsultasMedicoEmUmDiaEspecifico(Long idMedico, String dia);

    @Query(value = "SELECT EXISTS(SELECT m.id FROM medicos m WHERE :horario between m.horario_abertura and m.horario_fechamento and m.id = :idMedico)", nativeQuery = true)
    public Integer MedicoAtendeNoHorarioDaConsulta(String horario, Long idMedico);

    @Query(value = "SELECT COUNT(c.id) FROM consultas c where c.dia = :dia AND c.status = 'AGENDADA' AND c.medico_id = :idMedico", nativeQuery = true)
    public Integer quantidadeConsultasEmUmDiaHorarioEspecifico(Long idMedico, Timestamp dia);

    @Query(value = """ 
            SELECT m.nome AS nomeMedico, p.nome AS nomePaciente, TIME(c.dia) AS horario FROM consultas c 
            INNER JOIN medicos m ON m.id = c.medico_id 
            INNER JOIN pacientes p ON p.id = c.paciente_id 
            WHERE DATE(c.dia) = DATE(NOW()) AND c.status = 'AGENDADA'""", nativeQuery = true)
    public List<ConsultaDoDiaProjection> consultasDoDiaAtual();

    @Query(value = """ 
            SELECT m.nome AS nomeMedico, p.nome AS nomePaciente, c.dia AS dia FROM consultas c 
            INNER JOIN medicos m ON m.id = c.medico_id 
            INNER JOIN pacientes p ON p.id = c.paciente_id 
            WHERE c.dia BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 7 DAY) AND c.status = 'AGENDADA'""", nativeQuery = true)
    public List<ConsultasDaSemanaProjection> consultasDaSemanaAtual();

    @Query(value = "SELECT id FROM medicos ORDER BY RAND() LIMIT 1", nativeQuery = true)
    public Long iDMedicoAleatorio();

}

