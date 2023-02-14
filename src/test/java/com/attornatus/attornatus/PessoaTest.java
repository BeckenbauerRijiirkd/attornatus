package com.attornatus.attornatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.api.attornatus.controller.Dto.PessoaDto;
import com.api.attornatus.controller.Form.CreatePessoa;
import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;
import com.api.attornatus.repository.EnderecoRepository;
import com.api.attornatus.repository.PessoaRepository;
import com.api.attornatus.service.EnderecoService;
import com.api.attornatus.service.PessoaService;

@ExtendWith(MockitoExtension.class)

@AutoConfigureMockMvc
public class PessoaTest {
        @Mock
        public PessoaRepository pessoaRepository;

        @Mock
        public EnderecoRepository enderecoRepository;

        @InjectMocks
        public PessoaService pessoaService;

        @InjectMocks
        public EnderecoService enderecoService;

        @Test
        public void quandoBuscarPessoasRetorneSucesso() {

                List<Pessoa> lista = new ArrayList<Pessoa>();

                Mockito.when(pessoaRepository.findAll())
                                .thenReturn(lista);

                List<PessoaDto> response = pessoaService.buscarPessoas();

                assertNotNull(response);

        }

        @Test
        public void quandoBuscarUmaPessoaRetorneSucesso() {

                Endereco endereco = new Endereco(1L, new Pessoa(), "Logradouro teste",
                                "4910000", 10, "AJU", true);

                Pessoa pessoa = new Pessoa(1L, "teste", LocalDate.now(), new ArrayList<Endereco>(List.of(endereco)));

                Mockito.when(
                                pessoaRepository.findById(anyLong()))
                                .thenReturn(Optional.of(pessoa));

                Pessoa response = pessoaService.buscaPessoaPorId(anyLong());

                assertNotNull(response);

        }

        @Test
        public void quandocadastrarPessoaRetorneSucesso() {

                CreatePessoa cadastro = new CreatePessoa("teste", LocalDate.now(), new ArrayList<Endereco>());

                pessoaService.cadastrarPessoas(cadastro);

                Mockito.verify(pessoaRepository).save(new Pessoa("teste", LocalDate.now(), new ArrayList<Endereco>()));

        }

        @Test
        public void quandoCadastrarEnderecoRetorneSucesso() {

                Endereco endereco = new Endereco(1L, new Pessoa(), "Logradouro teste",
                                "4910000", 10, "AJU", true);

                Pessoa pessoa = new Pessoa(1L, "teste", LocalDate.now(), new ArrayList<Endereco>(List.of(endereco)));

                Mockito.when(
                                pessoaRepository.findById(1L))
                                .thenReturn(Optional.of(pessoa));

                pessoaService.cadastrarEndereco(1L, endereco);

                Mockito.verify(pessoaRepository).save(pessoa);

        }

        @Test
        public void quandoBuscarEnderecosRetorneSucesso() {

                Endereco endereco = new Endereco(1L, new Pessoa(), "Logradouro teste",
                                "4910000", 10, "AJU", true);

                Mockito.when(enderecoRepository.findByPessoa(new Pessoa(1L)))
                                .thenReturn(List.of(endereco));

                List<Endereco> response = enderecoService.listarEnderecosPessoa(1L);

                assertNotNull(response);

                assertEquals(List.of(endereco), response);

        }

}