package clases;

import java.util.GregorianCalendar;

public class Informe {
	private int id_inf;
	private int cod_cine;
	private GregorianCalendar fecha;
	private String pelicula1;
	private String pelicula2;
	private String pelicula3;
	private double beneficios;
	private String mayor_audiencia;
	
	
	public Informe(int id_inf, int cod_cine, GregorianCalendar fecha, String pelicula1, String pelicula2, String pelicula3, double beneficios, String mayor_audiencia) {
		super();
		this.id_inf = id_inf;
		this.cod_cine = cod_cine;
		this.fecha = fecha;
		this.pelicula1 = pelicula1;
		this.pelicula2 = pelicula2;
		this.pelicula3 = pelicula3;
		this.beneficios = beneficios;
		this.mayor_audiencia = mayor_audiencia;
	}


	public int getId_inf() {
		return id_inf;
	}


	public int getCod_cine() {
		return cod_cine;
	}


	public GregorianCalendar getFecha() {
		return fecha;
	}


	public String getPelicula1() {
		return pelicula1;
	}


	public String getPelicula2() {
		return pelicula2;
	}


	public String getPelicula3() {
		return pelicula3;
	}


	public double getBeneficios() {
		return beneficios;
	}


	public String getMayor_audiencia() {
		return mayor_audiencia;
	}
	
	
	
}
