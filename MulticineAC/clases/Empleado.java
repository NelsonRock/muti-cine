package clases;



public class Empleado {
	private int id_emple;
	private int cod_cine;
	private String nombre;
	private String apellidos;
	private String dni;
	private String oficio;
	
	
	public Empleado(int cod_cine, String nombre, String apellidos, String dni, String oficio) {

		this.cod_cine = cod_cine;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.oficio = oficio;
	}
	public Empleado (int id_emple, int cod_cine ,  String nombre, String apellidos, String dni, String oficio) {
		
		this.id_emple = id_emple;
		this.cod_cine = cod_cine;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.oficio = oficio;
		
	}

	public int getId_emple() {
		return id_emple;
	}


	public int getCod_cine() {
		return cod_cine;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public String getDni() {
		return dni;
	}


	public String getOficio() {
		return oficio;
	}
	
	
	
	
}
