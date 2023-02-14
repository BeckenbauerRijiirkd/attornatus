package com.api.attornatus.controller.Form;

import java.time.LocalDate;
import java.util.List;

import com.api.attornatus.model.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PessoaCreateForm {

    @NotBlank
    @NotEmpty
    @NotNull
    private String nome;

    @NotNull
    @Past
    private LocalDate dtNascimento;

    private List<Endereco> enderecos;

}