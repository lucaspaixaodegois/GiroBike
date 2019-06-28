package br.unitins.girobike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.girobike.dao.BikeDAO;
import br.unitins.girobike.model.Bike;
import br.unitins.girobike.model.Categoria;
import br.unitins.girobike.model.Marca;
import br.unitins.girobike.model.Modelo;
import br.unitins.girobike.model.TamanhoAro;

@Named
@ViewScoped
public class BikeController implements Serializable {

	private static final long serialVersionUID = 2602034636682098082L;

	private Bike bike;

	private List<Bike> listaBike = null;

	public List<Bike> getListaBike() {
		if (listaBike == null) {
			BikeDAO dao = new BikeDAO();
			listaBike = dao.findAll();
			if (listaBike == null)
				listaBike = new ArrayList<Bike>();
			dao.closeConnection();
		}

		return listaBike;
	}

	public void editar(int id) {
		BikeDAO dao = new BikeDAO();
		setBike(dao.findById(id));
	}

	public void incluir() {
		BikeDAO dao = new BikeDAO();

		if (dao.create(getBike())) {
			limpar();
			// para atualizar o data table
			listaBike = null;
		}
		dao.closeConnection();
	}

	public void alterar() {
		BikeDAO dao = new BikeDAO();
		if (dao.update(getBike())) {
			limpar();
			// para atualizar o data table
			listaBike = null;
		}
		dao.closeConnection();
	}

	public void excluir() {
		BikeDAO dao = new BikeDAO();
		if (dao.delete(getBike().getId())) {
			limpar();
			// para atualizar o data table
			listaBike = null;
		}
		dao.closeConnection();
	}

	public TamanhoAro[] getListaTamanhoAro() {
		return TamanhoAro.values();
	}

	public Marca[] getListaMarca() {
		return Marca.values();
	}

	public Modelo[] getListaModelo() {
		return Modelo.values();
	}

	public Categoria[] getListaCategoria() {
		return Categoria.values();
	}
	

	public void limpar() {
		bike = null;
	}

	public Bike getBike() {
		if (bike == null) {
			bike = new Bike();
		}

		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

}
