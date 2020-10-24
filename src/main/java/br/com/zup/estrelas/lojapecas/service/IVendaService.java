package br.com.zup.estrelas.lojapecas.service;

import java.util.List;

import br.com.zup.estrelas.lojapecas.dto.MensagemDto;
import br.com.zup.estrelas.lojapecas.dto.VendaDto;

public interface IVendaService {
	public MensagemDto realizaVenda(Long codigoBarra, int quantidade);
	
	public List<VendaDto> buscaVendasRealizadas();
}
