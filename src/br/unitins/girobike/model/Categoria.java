package br.unitins.girobike.model;

public enum Categoria {
	
	ADULTOMAS(1, "Adulto - Masculino"), 
	ADULTOFEM(2, "Adulto - Feminino"), 
	INFANTILMAS(3, "Infantil - Masculino"), 
	INFANTILFEM(4, "Infantil- Feminino");
	
	private int value;
	private String label;
	
	Categoria(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna uma categoria a partir de um valor inteiro
	public static Categoria valueOf(int value) {
		for (Categoria cat : Categoria.values()) {
			if (cat.getValue() == value) {
				return cat;
			}
		}
		return null;
	}

}
