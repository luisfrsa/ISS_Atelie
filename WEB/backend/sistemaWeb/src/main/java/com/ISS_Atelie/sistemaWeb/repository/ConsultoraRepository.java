package com.ISS_Atelie.sistemaWeb.repository;

import com.ISS_Atelie.sistemaWeb.domain.Consultora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultoraRepository extends JpaRepository<Consultora, Integer> {
    Optional<Consultora> findByCpf(String cpf);

    Optional<Consultora> findByCpfAndSenha(String cpf, String senha);
}
