package br.unitins.girobike.model;

public enum Modelo {

	DOWNHILL (1, "Downhill"), 
	SPEED(2, "Speed"),
	BMX(3, "BMX"),
	TRIAL(4, "Trial");
	private int value;
	
	private String label;
	
	Modelo(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna uma modelo a partir de um valor inteiro
	public static Modelo valueOf(int value) {
		for (Modelo modelo : Modelo.values()) {
			if (modelo.getValue() == value) {
				return modelo;
			}
		}
		return null;
	}
}
