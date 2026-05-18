package com.payflow.payflow_api.repository;

import com.payflow.payflow_api.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByNumeroConta(UUID numeroConta);
}
