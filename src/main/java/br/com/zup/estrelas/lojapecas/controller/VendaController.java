package br.com.zup.estrelas.lojapecas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.lojapecas.dto.MensagemDto;
import br.com.zup.estrelas.lojapecas.dto.VendaDto;
import br.com.zup.estrelas.lojapecas.service.IVendaService;
import br.com.zup.estrelas.lojapecas.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {
	@Autowired
	IVendaService vendaService;
	
	//host:8080/00002/2 -> Variable, Variable ALTER
	@PutMapping (path = "/{codigoBarra}/{quantidade}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto realizarVenda(@PathVariable Long codigoBarra, @PathVariable int quantidade) {
		return vendaService.realizaVenda(codigoBarra, quantidade);
	}
	
	// host:8080/ -> LIST ALL
	@GetMapping (produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<VendaDto> buscaVendasRealizadas(){
		return vendaService.buscaVendasRealizadas();
	}
}
