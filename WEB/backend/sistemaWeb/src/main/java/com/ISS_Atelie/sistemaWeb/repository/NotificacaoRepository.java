package com.ISS_Atelie.sistemaWeb.repository;

import com.ISS_Atelie.sistemaWeb.domain.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Integer> {
}
