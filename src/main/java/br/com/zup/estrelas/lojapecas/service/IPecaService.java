package br.com.zup.estrelas.lojapecas.service;

import java.util.List;
import br.com.zup.estrelas.lojapecas.dto.MensagemDto;
import br.com.zup.estrelas.lojapecas.entity.Peca;

public interface IPecaService {
	public MensagemDto adicionaPeca(Peca peca);
	
	public MensagemDto removePeca(Long codigoBarra);
	
	public MensagemDto alteraPeca(Long codigoBarra, Peca peca);
	
	public MensagemDto realizaVenda(Long codigoBarra, int quantidade);
	
	public Peca buscaPeca(Long codigoBarra);
	
	public List<Peca> listarPecas();
	
	public List<Peca> buscarPecaPorNome(String nome);
	
	public List<Peca> buscarPecaPorModelo(String modelo);
	
	public List<Peca> buscarPecaPorCategoria(String categoria);
}
