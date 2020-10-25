package br.com.zup.estrelas.lojapecas.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.lojapecas.dto.MensagemDto;
import br.com.zup.estrelas.lojapecas.entity.Peca;
import br.com.zup.estrelas.lojapecas.entity.Venda;
import br.com.zup.estrelas.lojapecas.repository.VendaRepository;

@Service
public class VendaService implements IVendaService {
	private static final String QUANTIDADE_EM_ESTOQUE_INSUFICIENTE = ("Quantidade em estoque insuficiente");
	private static final String PEÇA_INEXISTENTE = ("Peça inexistente");
	private static final String VENDA_REALIZADA_SUCESSO = ("Venda realizada com sucesso");

	@Autowired
	PecaService pecaService;
	
	@Autowired
	VendaRepository repository;

	public MensagemDto realizaVenda(Long codigoBarra, int quantidade) {
		Optional<Peca> pecaOptional = pecaService.repository.findById(codigoBarra);

		if (pecaOptional.isEmpty()) {
			return new MensagemDto(PEÇA_INEXISTENTE);
		}

		Peca peca = pecaOptional.get();

		if (peca.getQuantidadeEstoque() >= quantidade) {
			peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - quantidade);
			pecaService.alteraPeca(codigoBarra, peca);
			
			Venda venda = new Venda();
			venda.setCodigoBarra(peca.getCodigoBarra());
			venda.setPrecoUnitario(peca.getPrecoVenda());
			venda.setQuantidade(quantidade);
			venda.setPrecoTotalVenda(quantidade * peca.getPrecoVenda());			
			venda.setDataVenda(LocalDate.now());

			repository.save(venda);
			return new MensagemDto(VENDA_REALIZADA_SUCESSO);
		} else {
			return new MensagemDto(QUANTIDADE_EM_ESTOQUE_INSUFICIENTE);
		}
	}

	public List<Venda> buscaVendasRealizadas() {
		return (List<Venda>) repository.findAll();
	}

	public List<Venda> buscaVendasPorData(LocalDate dataVenda) {
		return repository.findByDataVenda(dataVenda);
	}

	public List<Venda> buscaVendasPorAno(LocalDate dataAnoInicio, LocalDate dataAnoFim) {
		return repository.findByDataVendaBetween(dataAnoInicio, dataAnoFim);
	}
	

}
