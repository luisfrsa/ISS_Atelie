package com.ISS_Atelie.sistemaWeb.repository;

import com.ISS_Atelie.sistemaWeb.domain.Produto;
import com.ISS_Atelie.sistemaWeb.domain.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Integer> {
}
