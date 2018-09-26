package com.ISS_Atelie.sistemaWeb.repository;

import com.ISS_Atelie.sistemaWeb.domain.Consultora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultoraRepository extends JpaRepository<Consultora, Integer> {
}
