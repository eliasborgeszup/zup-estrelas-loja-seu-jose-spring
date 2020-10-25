package br.com.zup.estrelas.lojapecas.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Venda {
	@Id
	private Long codigoBarra;
	
	@Column(nullable = false, columnDefinition = "int default 0")
	private int quantidade;
	
	@Column(name = "preco_unitario", nullable = false)
	private double precoUnitario;
	
	@Column(name = "preco_total_venda", nullable = false)
	private double precoTotalVenda;
	
	@Column(name = "data_venda", nullable = false)
	private LocalDate dataVenda;
}
