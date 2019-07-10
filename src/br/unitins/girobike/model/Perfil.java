package br.unitins.girobike.model;

import java.util.Arrays;
import java.util.List;

public enum Perfil {
	
	ADMINISTRADOR(1, "Administrador", Arrays.asList("usuario.xhtml","consultausuario","bike.xhtml","usuario2.xhtml", "template.xhtml", "acessonegado.xhtml", "produto.xhtml", "vendaproduto.xhtml", "carrinho.xhtml", "historico.xhtml","consultarBike.xhtml","consultarProduto.xhtml", "detalhesvenda.xhtml")),
	GERENTE(2, "Gerente" , Arrays.asList( "template.xhtml", "consultarBike.xhtml","consultarProduto.xhtml","acessonegado.xhtml", "produto.xhtml", "vendaproduto.xhtml", "carrinho.xhtml", "historico.xhtml", "detalhesvenda.xhtml")),
	FUNCIONARIO(3, "Funcionário", Arrays.asList("template.xhtml","consultarBike.xhtml","consultarProduto.xhtml", "acessonegado.xhtml", "vendaproduto.xhtml", "carrinho.xhtml", "historico.xhtml", "detalhesvenda.xhtml"));

	private int value;
	private String label;
	private List<String> pages;
	
	Perfil(int value, String label, List<String> pages) {
		this.value = value;
		this.label = label;
		this.pages = pages;
		
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public List<String> getPages() {
		return pages;
	}
	
	// retorna um perfil a partir de um valor inteiro
	public static Perfil valueOf(int value) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getValue() == value) {
				return perfil;
			}
		}
		return null;
	}
	
}
