package br.com.zup.estrelas.lojapecas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.lojapecas.dto.MensagemDto;
import br.com.zup.estrelas.lojapecas.dto.VendaDto;
import br.com.zup.estrelas.lojapecas.entity.Peca;

@Service
public class VendaService implements IVendaService{
	private static final String VENDA_NÃO_REALIZADA_TENTE_NOVAMENTE = "Venda não realizada, tente novamente";
	private static final String VENDA_REALIZADA_SUCESSO = ("Venda realizada com sucesso");
	
	@Autowired
	PecaService pecaService;
	
	private List<VendaDto> listaVendas = new ArrayList<>();
	
	public MensagemDto realizaVenda(Long codigoBarra, int quantidade) {
		Peca peca = pecaService.repository.findById(codigoBarra).get();

		if (peca.getCodigoBarra() != 0 && peca.getQuantidadeEstoque() > quantidade) {
			peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - quantidade);
			pecaService.alteraPeca(codigoBarra, peca);

			VendaDto venda = new VendaDto();
			venda.setCodigoBarra(peca.getCodigoBarra());
			venda.setPrecoUnitario(peca.getPrecoVenda());
			venda.setQuantidade(quantidade);
			venda.setPrecoTotalVenda(quantidade * peca.getPrecoVenda());

			listaVendas.add(venda);

			return new MensagemDto(VENDA_REALIZADA_SUCESSO);
		}

		return new MensagemDto(VENDA_NÃO_REALIZADA_TENTE_NOVAMENTE);
	}
	
	public List<VendaDto> buscaVendasRealizadas() {
		return listaVendas;
	}
}
