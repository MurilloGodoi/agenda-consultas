package com.alura.agenda.repository;

import com.alura.agenda.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT COUNT(p.id) FROM Paciente p where p.email = :email OR p.cpf = :cpf")
    Integer contaPacientesPorEmailOuCpf(String email, String cpf);

}

