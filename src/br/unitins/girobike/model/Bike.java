package br.unitins.girobike.model;

public class Bike {
	private Integer id;
	private Integer tamanhoAro;
	private Categoria categoria;
	private Modelo modelo;
	private Marca marca;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTamanhoAro() {
		return tamanhoAro;
	}

	public void setTamanhoAro(Integer tamanhoAro) {
		this.tamanhoAro = tamanhoAro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
