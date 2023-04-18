package com.br.modelmapperexample.app.controller;

import com.br.modelmapperexample.app.dto.PessoaCadastro;
import com.br.modelmapperexample.app.dto.PessoaResposta;
import com.br.modelmapperexample.app.model.Pessoa;
import com.br.modelmapperexample.app.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PessoaController(PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody PessoaCadastro pessoa) {
        //Dentro do método map vai dois parametros(classe que você quer converter, Tipo de classe na qual vai ser convertida)
        //Note que ele quer converter a classe PessoaCadastro para a classe Pessoa
        Pessoa pessoadaMappeada = modelMapper.map(pessoa, Pessoa.class);
        pessoaRepository.save(pessoadaMappeada);

        return ResponseEntity.ok(pessoadaMappeada);

    }

    @GetMapping
    public ResponseEntity<List<PessoaResposta>> converter() {
        //Exemplo de mapeamento de listas/coleções
        List<Pessoa> pessoas = pessoaRepository.findAll();

        List<PessoaResposta> pessoasMap = pessoas.stream()
                .map(pessoaEntity -> modelMapper.map(pessoaEntity, PessoaResposta.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoasMap);
    }


}
