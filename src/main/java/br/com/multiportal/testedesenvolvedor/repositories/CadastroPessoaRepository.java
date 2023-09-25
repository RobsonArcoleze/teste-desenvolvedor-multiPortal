package br.com.multiportal.testedesenvolvedor.repositories;

import br.com.multiportal.testedesenvolvedor.model.CadastroPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroPessoaRepository extends JpaRepository<CadastroPessoa, Long> {
}