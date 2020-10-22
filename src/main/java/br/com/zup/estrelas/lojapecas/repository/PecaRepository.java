package br.com.zup.estrelas.lojapecas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.lojapecas.entity.Peca;

@Repository
public interface PecaRepository extends CrudRepository<Peca, Long>{

}
