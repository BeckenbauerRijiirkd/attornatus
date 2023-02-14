package com.api.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.attornatus.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}