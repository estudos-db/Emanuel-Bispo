package com.br.modelmapperexample.app.controller;

import com.br.modelmapperexample.app.dto.PessoaCadastro;
import com.br.modelmapperexample.app.dto.PessoaResposta;
import com.br.modelmapperexample.app.model.Pessoa;
import com.br.modelmapperexample.app.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaRepository pessoaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    public ResponseEntity<PessoaResposta> cadastrar(@RequestBody PessoaCadastro pessoa) {

        /*
                
        * */
        Pessoa pessoadaMappeada = modelMapper.map(pessoa, Pessoa.class);
        pessoaRepository.save(pessoadaMappeada);
        PessoaResposta pessoaDto = modelMapper.map(pessoadaMappeada, PessoaResposta.class);
        return ResponseEntity.ok(pessoaDto);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaResposta> lisar(@PathVariable Long pessoaId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
        PessoaResposta pessoaMap = modelMapper.map(pessoa, PessoaResposta.class);
        return ResponseEntity.ok(pessoaMap);
    }

}
