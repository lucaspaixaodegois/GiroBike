package br.unitins.girobike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.girobike.application.Session;
import br.unitins.girobike.application.Util;
import br.unitins.girobike.dao.ProdutoDAO;
import br.unitins.girobike.dao.VendaDAO;
import br.unitins.girobike.model.ItemVenda;
import br.unitins.girobike.model.Produto;
import br.unitins.girobike.model.Usuario;
import br.unitins.girobike.model.Venda;

@Named
@RequestScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = 8149999991497336066L;

	private Venda venda;

	private Usuario usuario;
	private String nomeCliente;

	public void remover(ItemVenda itemvenda) {
		// busca o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		carrinho.remove(itemvenda);

		// atualiza o carrinho
		Session.getInstance().setAttribute("carrinho", carrinho);

		Util.addMessageError("Item removido com Sucesso! ");
	}

	public void finalizar() {
		getVenda().setCliente(getNomeCliente());
		getVenda().setUsuario((Usuario) Session.getInstance().getAttribute("usuarioLogado"));
		VendaDAO dao = new VendaDAO();
		dao.create(getVenda());
		// atualiza o carrinho 
		AtualiaCarrinho();
		Util.addMessageError("Venda realizada com sucesso!");
	}
	
	public void AtualiaCarrinho(){
		// atualiza o carrinho
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		carrinho=null;
		Session.getInstance().setAttribute("carrinho", carrinho);
		
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
		}
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		if (carrinho != null)
			venda.setListaItemVenda(carrinho);
		else
			venda.setListaItemVenda(new ArrayList<ItemVenda>());

		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public void adicionar(int id) {
		// pesquisa o produto selecionado
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.findById(id);

		// verifica se existe o carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona o carrinho na sessao
			Session.getInstance().setAttribute("carrinho", new ArrayList<ItemVenda>());
		}
		// busca o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// cria um item de venda
		ItemVenda item = new ItemVenda();
		item.setProduto(produto);
		item.setValor(produto.getValor());

		// adiciona o item no objeto de referencia do carrinho
		carrinho.add(item);

		// atualiza o carrinho
		Session.getInstance().setAttribute("carrinho", carrinho);

		Util.addMessageError("Adicionado com Sucesso! ");
	}

}
