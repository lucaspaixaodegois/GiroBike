package br.unitins.girobike.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.girobike.application.Util;
import br.unitins.girobike.dao.BikeDAO;
import br.unitins.girobike.model.Bike;

@Named
@ViewScoped
public class ConsultaBikeController implements Serializable {

	private static final long serialVersionUID = 1646118458024979823L;

	private Bike bike;
	private String nome;

	int id;

	private List<Bike> listaBike = null;

	public List<Bike> getListaBike() {
		if (listaBike == null) {
			BikeDAO dao = new BikeDAO();
			listaBike = (List<Bike>) dao.findById(id);
			if (listaBike == null)
				listaBike = new ArrayList<Bike>();
			dao.closeConnection();
		}

		return listaBike;
	}

	public void pesquisar() {
		listaBike = null;
	}

	public void editar(int id) {
		BikeDAO dao = new BikeDAO();
		Bike bike = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("bikeFlash", bike);
		Util.redirect("bike.xhtml");
	}

	public void excluir(int id) {
		BikeDAO dao = new BikeDAO();
		if (dao.delete(id)) {
			limpar();
			// para atualizar o data table
			listaBike = null;
		}
		dao.closeConnection();
	}

	public void limpar() {
		bike = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setListaBike(List<Bike> listaBike) {
		this.listaBike = listaBike;
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
