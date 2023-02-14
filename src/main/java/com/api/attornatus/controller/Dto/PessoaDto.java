package com.api.attornatus.controller.Dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.api.attornatus.model.Pessoa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDto {

    private String nome;
    private LocalDate dataDeNascimento;

    public PessoaDto(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.dataDeNascimento = pessoa.getDtNascimento();
    }

    public static List<PessoaDto> converter(List<Pessoa> pessoa) {
        return (pessoa.stream().map(PessoaDto::new).collect(Collectors.toList()));
    }
}