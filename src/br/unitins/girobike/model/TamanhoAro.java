package br.unitins.girobike.model;

public enum TamanhoAro {

	ARO12(1,"Aro12"),
	ARO16(2,"Aro16"),
	ARO20(3,"Aro20"),
	ARO24(4,"Aro24"),
	ARO26(5,"Aro26"),
	ARO27(6,"Aro27.5"),
	ARO29(7,"Aro29");

	
	private int value;
	private String label;
	
	TamanhoAro(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna um tamanhoAro a partir de um valor inteiro
	public static TamanhoAro valueOf(int value) {
		for (TamanhoAro tamanhoAro : TamanhoAro.values()) {
			if (tamanhoAro.getValue() == value) {
				return tamanhoAro;
			}
		}
		return null;
	}
}
