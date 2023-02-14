package com.api.attornatus.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.attornatus.controller.Dto.PessoaDetalheDto;
import com.api.attornatus.controller.Dto.PessoaDto;
import com.api.attornatus.controller.Form.PessoaCreateForm;
import com.api.attornatus.model.Pessoa;
import com.api.attornatus.repository.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pRepository;

    public List<PessoaDto> buscarPessoas() {

        List<Pessoa> pessoas = pRepository.findAll();

        return PessoaDto.converter(pessoas);
    }

    public PessoaDetalheDto buscarPessoa(Long id){

        Pessoa pessoa = pRepository.findById(id).orElseThrow(() -> new ServiceException("Pessoa não encontrada"));


    return new PessoaDetalheDto(pessoa);
    }

    public void cadastrarPessoas(PessoaCreateForm create) {

        Pessoa pessoa = new Pessoa(create.getNome(), create.getDtNascimento());

        pRepository.save(pessoa);

    }

}