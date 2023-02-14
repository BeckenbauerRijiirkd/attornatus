package com.api.attornatus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.attornatus.controller.Dto.PessoaDto;
import com.api.attornatus.controller.Form.PessoaCreateForm;
import com.api.attornatus.model.Pessoa;
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
    public ResponseEntity<Pessoa> buscarDetalhePessoa(@PathVariable Long id) {

        Pessoa pessoa = pService.buscaPessoaPorId(id);

        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<?> criarPessoa(@Valid @RequestBody PessoaCreateForm pessoa) {

        pService.cadastrarPessoas(pessoa);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{idPessoa}/endereco/{idEndereco}/principal")
    public ResponseEntity<?> alterarEnderecoPrincipal(@Valid @PathVariable Long idPessoa,
            @PathVariable Long idEndereco) {

        pService.alteraEnderecoPrincipal(idPessoa, idEndereco);

        return ResponseEntity.ok().build();
    }

}