package br.com.zup.estrelas.lojapecas.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.lojapecas.dto.MensagemDto;
import br.com.zup.estrelas.lojapecas.entity.Venda;
import br.com.zup.estrelas.lojapecas.service.IVendaService;
import br.com.zup.estrelas.lojapecas.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {
	@Autowired
	IVendaService vendaService;
	
	//host:8080/2/2 -> Variable, Variable ALTER
	@PutMapping (path = "/{codigoBarra}/{quantidade}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto realizarVenda(@PathVariable Long codigoBarra, @PathVariable int quantidade) {
		return vendaService.realizaVenda(codigoBarra, quantidade);
	}
	
	// host:8080/ -> LIST ALL
	@GetMapping (produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Venda> buscaVendasRealizadas(){
		return vendaService.buscaVendasRealizadas();
	}
	
	//host:8080/24/10/2020 -> Variable LIST ALL DATA VENDA
	@GetMapping (path = "/{dia}/{mes}/{ano}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Venda> buscaVendasPorData(@PathVariable Integer dia, @PathVariable Integer mes, @PathVariable Integer ano){
		return vendaService.buscaVendasPorData(LocalDate.of(ano, mes, dia));
	}
	
	//host:8080/2020 -> Variable LIST ALL BY YEAR
	@GetMapping (path = "/{ano}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Venda> buscarVendasPorAno(@PathVariable Integer ano){
		return vendaService.buscaVendasPorAno(LocalDate.of(ano, 01, 01), LocalDate.of(ano, 12, 31));
	}
}
