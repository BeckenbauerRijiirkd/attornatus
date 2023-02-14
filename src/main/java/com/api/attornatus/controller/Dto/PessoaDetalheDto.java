package com.api.attornatus.controller.Dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDetalheDto {

    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private List<Endereco> enderecos;

    public PessoaDetalheDto(Pessoa pessoa) {
        this.id = pessoa.getPessoa_id();
        this.nome = pessoa.getNome();
        this.dataDeNascimento = pessoa.getDtNascimento();
        this.enderecos = pessoa.getEnderecos();
    }

    public static List<PessoaDetalheDto> converter(List<Pessoa> pessoa) {
        return (pessoa.stream().map(PessoaDetalheDto::new).collect(Collectors.toList()));
    }
}