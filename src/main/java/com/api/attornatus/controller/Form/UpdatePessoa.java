package com.api.attornatus.controller.Form;

import java.time.LocalDate;

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
public class UpdatePessoa {

    @NotBlank
    @NotEmpty
    @NotNull
    private String nome;

    @NotNull
    @Past
    private LocalDate dtNascimento;


}