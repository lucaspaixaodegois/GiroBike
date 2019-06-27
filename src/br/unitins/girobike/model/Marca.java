package br.unitins.girobike.model;

public enum Marca {

	HOUSTON (1, "Houston"), 
	CALOI(2, "Caloi"),
	VIKINGX(3, "Vikingx"),
	SENSE(4, "Sense"),
	GTS(5, "GTS"),
	MONARK(6,"Monark");
	
	private int value;
	private String label;
	
	Marca(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna uma marca a partir de um valor inteiro
	public static Marca valueOf(int value) {
		for (Marca marca : Marca.values()) {
			if (marca.getValue() == value) {
				return marca;
			}
		}
		return null;
	}
}
