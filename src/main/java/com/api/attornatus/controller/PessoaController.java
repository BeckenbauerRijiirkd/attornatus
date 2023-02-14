package com.api.attornatus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.attornatus.controller.Dto.PessoaDetalheDto;
import com.api.attornatus.controller.Dto.PessoaDto;
import com.api.attornatus.controller.Form.PessoaCreateForm;
import com.api.attornatus.service.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pService;

    @GetMapping
    public ResponseEntity<List<PessoaDto>> listarClientes() {

        List<PessoaDto> pessoas = pService.buscarPessoas();

        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDetalheDto> buscarDetalhePessoa(@PathVariable Long id) {

        PessoaDetalheDto pessoa = pService.buscarPessoa(id);

        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<?> criarPessoa(@Valid @RequestBody PessoaCreateForm create) {

        pService.cadastrarPessoas(create);

        return ResponseEntity.status(201).build();
    }

}