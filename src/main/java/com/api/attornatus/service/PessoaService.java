package com.api.attornatus.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.attornatus.controller.Dto.PessoaDto;
import com.api.attornatus.controller.Form.CreatePessoa;
import com.api.attornatus.controller.Form.UpdatePessoa;
import com.api.attornatus.model.Endereco;
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

    public Pessoa buscaPessoaPorId(Long id) {

        Pessoa pessoa = pRepository.findById(id).orElseThrow(() -> new ServiceException("Pessoa não encontrada"));

        return pessoa;
    }

    public void cadastrarPessoas(CreatePessoa create) {

        Pessoa pessoa = new Pessoa(create.getNome(), create.getDtNascimento(), create.getEnderecos());

        if (!pessoa.getEnderecos().isEmpty())
            pessoa.getEnderecos().stream().forEach(endereco -> endereco.setPessoa(pessoa));

        pRepository.save(pessoa);

    }

    public void alterarPessoas(Long idPessoa, UpdatePessoa update) {

        Pessoa pessoa = this.buscaPessoaPorId(idPessoa);

        pessoa.setNome(update.getNome());
        pessoa.setDtNascimento(update.getDtNascimento());

        pRepository.save(pessoa);
    }

    public void alteraEnderecoPrincipal(Long idPessoa, Long idEndereco) {

        Pessoa pessoa = this.buscaPessoaPorId(idPessoa);

        pessoa.getEnderecos().stream().forEach(endereco -> {

            endereco.setPrincipal(endereco.getId().equals(idEndereco));
        });

        pRepository.save(pessoa);
    }

    public void cadastrarEndereco(Long idPessoa, Endereco endereco) {

        Pessoa pessoa = this.buscaPessoaPorId(idPessoa);

        pessoa.getEnderecos().add(endereco);

        pessoa.getEnderecos().stream().forEach(enderecoLb -> {
            enderecoLb.setPessoa(pessoa);
        });

        pRepository.save(pessoa);
    }

}
