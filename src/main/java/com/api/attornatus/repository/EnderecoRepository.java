package com.api.attornatus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;

public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {

    List<Endereco> findByPessoa(Pessoa pessoa);

}