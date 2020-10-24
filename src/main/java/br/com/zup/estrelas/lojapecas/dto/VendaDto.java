package br.com.zup.estrelas.lojapecas.dto;

public class VendaDto {
	private Long codigoBarra;
	private int quantidade;
	private double precoUnitario;
	private double precoTotalVenda;
	
	
	public double getPrecoTotalVenda() {
		return precoTotalVenda;
	}
	public void setPrecoTotalVenda(double precoTotalVenda) {
		this.precoTotalVenda = precoTotalVenda;
	}
	public Long getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(Long codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
}
