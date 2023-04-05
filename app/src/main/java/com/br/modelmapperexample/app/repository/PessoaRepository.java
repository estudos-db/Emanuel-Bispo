package com.br.modelmapperexample.app.repository;

import com.br.modelmapperexample.app.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
