package com.alura.agenda.repository;


import com.alura.agenda.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("SELECT COUNT(m.id) FROM Medico m where m.email = :email OR m.crm = :crm")
    Integer contaMedicosPorEmailOuCrm(String email, String crm);

}

