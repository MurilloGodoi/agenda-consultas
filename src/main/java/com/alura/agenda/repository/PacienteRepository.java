package com.alura.agenda.repository;

import com.alura.agenda.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}

