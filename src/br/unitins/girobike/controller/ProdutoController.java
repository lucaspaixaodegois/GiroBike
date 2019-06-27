package br.unitins.girobike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.girobike.dao.ProdutoDAO;
import br.unitins.girobike.model.Produto;

@Named
@ViewScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = -8601622053041048376L;

	private Produto produto;
	
	private List<Produto> listaProduto = null;
	
	public List<Produto> getListaProduto(){
		if (listaProduto == null) {
			ProdutoDAO dao = new ProdutoDAO();
			listaProduto = dao.findAll();
			if (listaProduto == null)
				listaProduto = new ArrayList<Produto>();
			dao.closeConnection();
		}
		
		return listaProduto;
	}
	
	public void editar(int id) {
		ProdutoDAO dao = new ProdutoDAO();
		setProduto(dao.findById(id));
	}
	
	
	public void incluir() {
		ProdutoDAO dao = new ProdutoDAO();

		if (dao.create(getProduto())) {
			limpar();
			// para atualizar o data table
			listaProduto = null;
		}
		dao.closeConnection();
	}
	
	public void alterar() {
		ProdutoDAO dao = new ProdutoDAO();
		if (dao.update(getProduto())) {
			limpar();
			// para atualizar o data table
			listaProduto = null;
		}
		dao.closeConnection();
	}
	
	public void excluir() {
		ProdutoDAO dao = new ProdutoDAO();
		if (dao.delete(getProduto().getId())) {
			limpar();
			// para atualizar o data table
			listaProduto = null;
		}
		dao.closeConnection();
	}
	
	public void limpar() {
		produto = null;
	}
	

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}
		
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
