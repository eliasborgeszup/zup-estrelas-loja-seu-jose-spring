package br.com.zup.estrelas.lojapecas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.lojapecas.dto.MensagemDto;
import br.com.zup.estrelas.lojapecas.dto.VendaDto;
import br.com.zup.estrelas.lojapecas.entity.Peca;
import br.com.zup.estrelas.lojapecas.repository.PecaRepository;

@Service
public class PecaService implements IPecaService {
	private final static String PECA_CADASTRADA_COM_SUCESSO = ("Peca cadastrada com sucesso");
	private final static String PECA_ALTERADA_COM_SUCESSO = ("Peca alterada com sucesso");
	private final static String PECA_EXCLUIDA_COM_SUCESSO = ("Peca excluida com sucesso");
	private final static String PECA_INEXISTENTE = ("Peça inexistente, não foi possivel realizar a operação");
	private final static String PECA_NAO_CADASTRADA = ("Nao foi possivel cadastrar peça");

	private List<VendaDto> listaVendas = new ArrayList<>();
	
	@Autowired
	PecaRepository repository;

	public MensagemDto adicionaPeca(Peca peca) {
		if (repository.existsById(peca.getCodigoBarra())) {
			return new MensagemDto(PECA_NAO_CADASTRADA);
		}

		repository.save(peca);
		return new MensagemDto(PECA_CADASTRADA_COM_SUCESSO);
	}

	public MensagemDto removePeca(Long codigoBarra) {
		if (repository.existsById(codigoBarra)) {
			repository.deleteById(codigoBarra);
			return new MensagemDto(PECA_EXCLUIDA_COM_SUCESSO);
		}
		return new MensagemDto(PECA_INEXISTENTE);
	}

	public MensagemDto alteraPeca(Long codigoBarra, Peca peca) {

		if (repository.existsById(codigoBarra) && codigoBarra.equals(peca.getCodigoBarra())) {
			repository.save(peca);
			return new MensagemDto(PECA_ALTERADA_COM_SUCESSO);
		}

		return new MensagemDto(PECA_INEXISTENTE);
	}

	public MensagemDto realizaVenda(Long codigoBarra, int quantidade) {
		Peca peca = repository.findById(codigoBarra).get();
		
		if(peca.getCodigoBarra() != 0 && peca.getQuantidadeEstoque() > quantidade) {
			peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - quantidade);
			alteraPeca(codigoBarra, peca);
			
			VendaDto venda = new VendaDto();
			venda.setCodigoBarra(peca.getCodigoBarra());
			venda.setPrecoUnitario(peca.getPrecoVenda());
			venda.setQuantidade(quantidade);
			venda.setPrecoTotalVenda(quantidade * peca.getPrecoVenda());
			
			listaVendas.add(venda);
			
			return new MensagemDto("Venda realizada com sucesso");
		}
		
		return new MensagemDto("Venda não realizada, tente novamente");
	}
	
	public Peca buscaPeca(Long codigoBarra) {
		return repository.findById(codigoBarra).get();
	}

	public List<Peca> listarPecas() {
		return (List<Peca>) repository.findAll();
	}

	public List<Peca> buscarPecaPorNome(String nome) {
		return repository.findByNome(nome);
	}

	public List<Peca> buscarPecaPorModelo(String modelo) {
		return repository.findByModelo(modelo);
	}

	public List<Peca> buscarPecaPorCategoria(String categoria) {
		return repository.findByCategoria(categoria);
	}
}
