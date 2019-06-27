package br.unitins.girobike.model;

import java.time.LocalDate;
import java.util.List;

public class Venda {
	private Integer id;
	private LocalDate dataVenda;
	private Usuario usuario;
	private String cliente;
	private List<ItemVenda> listaItemVenda;
	
	// atributo calculado (nao existe na tabela)
	private double totalVenda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
	public double getTotalVenda() {
		totalVenda = 0;
		if (listaItemVenda != null)
			for (ItemVenda itemVenda : listaItemVenda) 
				totalVenda += itemVenda.getValor();
		
		return totalVenda;
	}

}
