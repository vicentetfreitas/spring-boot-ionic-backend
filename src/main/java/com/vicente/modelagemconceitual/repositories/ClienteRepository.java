package com.vicente.modelagemconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicente.modelagemconceitual.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
