package br.com.zup.estrelas.lojapecas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.lojapecas.dto.MensagemDto;
import br.com.zup.estrelas.lojapecas.dto.VendaDto;
import br.com.zup.estrelas.lojapecas.entity.Peca;
import br.com.zup.estrelas.lojapecas.service.IPecaService;

@RestController
@RequestMapping("/pecas")
public class PecaController {
	@Autowired
	IPecaService pecaService;

	// host:8080/ -> Body Peca INSERT
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto adicionaPeca(@RequestBody Peca peca) {
		return pecaService.adicionaPeca(peca);
	}

	// host:8080/00001 -> Variable DELETE
	@DeleteMapping(path = "/{codigoBarra}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto removePeca(@PathVariable Long codigoBarra) {
		return pecaService.removePeca(codigoBarra);
	}

	// host:8080/00002 -> Variable, Body Peca ALTER
	@PutMapping(path = "/{codigoBarra}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto alteraPeca(@PathVariable Long codigoBarra, @RequestBody Peca peca) {
		return pecaService.alteraPeca(codigoBarra, peca);
	}

	// host:8080/00003 -> Variable LIST BY ID
	@GetMapping(path = "/{codigoBarra}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Peca buscaPeca(@PathVariable Long codigoBarra) {
		return pecaService.buscaPeca(codigoBarra);
	}

	// host:8080/ -> LIST ALL
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Peca> listaPeca() {
		return pecaService.listarPecas();
	}

	// host:8080/nome/Filtro de ar -> Variable LIST BY NAME
	@GetMapping(path = "/nome/{nome}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Peca> buscaPecaPorNome(@PathVariable String nome) {
		return pecaService.buscarPecaPorNome(nome);
	}

	// host:8080/modelo/Gol -> Variable LIST BY MODEL
	@GetMapping(path = "/modelo/{modelo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Peca> buscaPecaPorModelo(@PathVariable String modelo) {
		return pecaService.buscarPecaPorModelo(modelo);
	}

	// host:8080/categoria/Motor -> Variable LIST BY CATEGORY
	@GetMapping(path = "/categoria/{categoria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Peca> buscaPecaPorCategoria(@PathVariable String categoria) {
		return pecaService.buscarPecaPorCategoria(categoria);
	}
}
