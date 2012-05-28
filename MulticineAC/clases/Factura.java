package clases;

import java.util.*;

public class Factura {
private int cod_factura;
private int id_emple;
private int cod_cine;
private String pelicula;
private int num_sala;
private int asiento;
private GregorianCalendar fecha;
private double precio;
private int tipo_descuento;
private double precio_final;
private String tipo_pago;


public Factura(int cod_factura, int id_emple, int cod_cine, String pelicula, int num_sala, int asiento, GregorianCalendar fecha, double precio, int tipo_descuento, double precio_final, String tipo_pago) {

	this.cod_factura = cod_factura;
	this.id_emple = id_emple;
	this.cod_cine = cod_cine;
	this.pelicula = pelicula;
	this.num_sala = num_sala;
	this.asiento = asiento;
	this.fecha = fecha;
	this.precio = precio;
	this.tipo_descuento = tipo_descuento;
	this.precio_final = precio_final;
	this.tipo_pago = tipo_pago;
}


public int getCod_factura() {
	return cod_factura;
}


public int getId_emple() {
	return id_emple;
}


public int getCod_cine() {
	return cod_cine;
}


public String getPelicula() {
	return pelicula;
}


public int getNum_sala() {
	return num_sala;
}


public int getAsiento() {
	return asiento;
}


public GregorianCalendar getFecha() {
	return fecha;
}


public double getPrecio() {
	return precio;
}


public int getTipo_descuento() {
	return tipo_descuento;
}


public double getPrecio_final() {
	return precio_final;
}


public String getTipo_pago() {
	return tipo_pago;
}



}
