package clases;

public class Sala {
	private int cod_cine;
	private int num_sala;
	private int capacidad;

public Sala(int capacidad, int cod_cine) {
	
	this.capacidad = capacidad;
	this.cod_cine = cod_cine;
}

public int getCod_cine() {
	return cod_cine;
}

public int getNum_sala() {
	return num_sala;
}

public int getCapacidad() {
	return capacidad;
}


}
