package com.api.attornatus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;
import com.api.attornatus.repository.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository eRepository;

    public List<Endereco> listarEnderecosPessoa(Long idPessoa) {

        Pessoa pessoa = new Pessoa(idPessoa);

        List<Endereco> enderecos = eRepository.findByPessoa(pessoa);

        return enderecos;
    }

    

}
