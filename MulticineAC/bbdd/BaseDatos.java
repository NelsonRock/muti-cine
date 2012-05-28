/*
 * Creado el 19/11/2008
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package bbdd;
/**
*
* @author Olga Yanguas
* @author Nelson Rodriguez
* @author Manuel Romero
* @author Alvaro Bilbao
* @version 24/05/2012
*/
import java.sql.*;
import excepciones.ErrorTecnicoExcepcion;
public class BaseDatos {
	private String cadenaConexion;
	private String driver;
	private Connection conexion;

	
	public BaseDatos(){
		
	}
	public BaseDatos(String cx, String dr){
		cadenaConexion=cx;
		driver=dr;
	}
	public void abrir () throws ErrorTecnicoExcepcion{
	try{
		Class.forName(driver);
	}
	catch (ClassNotFoundException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
	}
	try{
		conexion=DriverManager.getConnection(cadenaConexion);
	}
	catch (SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
	}
	}

	public void abrir(String cx, String dr)throws ErrorTecnicoExcepcion{
		cadenaConexion=cx;
		driver=dr;
		abrir();
	}
public void cerrar()throws ErrorTecnicoExcepcion{
	try{
	conexion.close();
	}
	catch (SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
	}
}

public Connection getConexion(){
	return conexion;
}
}
