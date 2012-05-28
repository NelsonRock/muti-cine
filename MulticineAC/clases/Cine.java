package clases;
import java.util.*;
import java.sql.*;


public class Cine {
private int cod;
private String nombre;
private String provincia;
private String poblacion;
private String direccion;
private java.sql.Date fecha_alta;

/**
 * 
 * @param nombre Nombre del Cine
 * @param provincia Provincia del Cine
 * @param poblacion Poblacion del Cine
 * @param direccion Direccion del Cine
 * @param feca_alta Fecha en la que ingresa el Cine (la aplicacion recogerá la fecha del sistema)
 */
public Cine( String nombre, String provincia, 
		String poblacion, String direccion, GregorianCalendar fecha_alta) {

	this.nombre = nombre;
	this.provincia = provincia;
	this.poblacion = poblacion;
	this.direccion = direccion;
	this.fecha_alta = new java.sql.Date(fecha_alta.get(Calendar.YEAR)-1900,fecha_alta.get(Calendar.MONTH),fecha_alta.get(Calendar.DATE));
}

/**
 * @param cod Codigo del Cine (Autonumerico)
 * @param nombre Nombre del Cine
 * @param provincia Provincia del Cine
 * @param poblacion Poblacion del Cine
 * @param direccion Direccion del Cine
 * @param feca_alta Fecha en la que ingresó el Cine
 */



public Cine (int cod,String nombre, String provincia, 
		String poblacion, String direccion, java.sql.Date fecha_alta) {
	this.cod= cod;
	this.nombre = nombre;
	this.provincia = provincia;
	this.poblacion = poblacion;
	this.direccion = direccion;
	this.fecha_alta = fecha_alta;
	
}
public String getDireccion() {
	return direccion;
}


public java.sql.Date getFecha_alta() {
	return fecha_alta;
}


public String getNombre() {
	return nombre;
}


public String getPoblacion() {
	return poblacion;
}

public String getProvincia() {
	return provincia;
}


/**
 * @return toString() Retorna el Nombre, la Provincia, la Poblacion y Direccion del Cine.
 */


public String toString() {
	// TODO Auto-generated method stub
	return nombre+",  "+provincia+ " " +poblacion+ ",  "+ direccion ;
}




}
