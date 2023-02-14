package com.attornatus.attornatus;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
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
import com.api.attornatus.repository.PessoaRepository;
import com.api.attornatus.service.PessoaService;

@ExtendWith(MockitoExtension.class)

@AutoConfigureMockMvc
public class PessoaTest {
    @Mock
    public PessoaRepository pessoaRepository;

    @InjectMocks
    public PessoaService pessoaService;

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

        Mockito.when(
                pessoaRepository.findById(anyLong()).orElseThrow(() -> new ServiceException("Pessoa não encontrada")))
                .thenReturn(new Pessoa("teste", LocalDate.now(), new ArrayList<Endereco>()));

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
    public void quandoCadastrarEnderecoRetorneSucesso(){

        // Endereco endereco = new Endereco(anyLong(), anyString(), anyString(), anyString(), anyInt(), anyString(), anyBoolean());

    }

}