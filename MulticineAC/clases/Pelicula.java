package clases;

public class Pelicula {
	private int cod_pelicula;
	private int cod_cine;
	private String nombre;
	private String genero;
	
	public Pelicula(int cod_cine, String nombre, String genero) {
		this.cod_cine = cod_cine;
		this.nombre = nombre;
		this.genero = genero;
	}

	public int getCod_pelicula() {
		return cod_pelicula;
	}

	public int getCod_cine() {
		return cod_cine;
	}

	public String getNombre() {
		return nombre;
	}

	public String getGenero() {
		return genero;
	}
	
	
}
