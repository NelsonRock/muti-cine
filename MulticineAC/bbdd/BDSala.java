package bbdd;

import java.sql.*;

import clases.Sala;
import excepciones.CineExcepcion;
import excepciones.ErrorTecnicoExcepcion;

public class BDSala {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;

	
	/**Método para añadir una Sala a la base de datos.
	 * @param sala Sala: Objeto Sala
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
	public static void  añadirSala(Sala sala, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
		
		String cadena="INSERT INTO Salas (capacidad, cod_cine) VALUES('" + sala.getCapacidad() + "'," + sala.getCod_cine() +")"; 	

		try{
		c=bbdd.getConexion();
		s=c.createStatement();
		s.executeUpdate(cadena);
		s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}	



	}

	/**Método para buscar una Sala en la base de datos.
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @param cod int: Codigo de la sala
	 * @throws CineExcepcion Exception: Si la sala no se encuentra en la base de datos
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
public static void buscarSala(BaseDatos bbdd, int cod) throws CineExcepcion, ErrorTecnicoExcepcion{
	String cadena="SELECT numero_sala FROM Salas WHERE numero_sala="+ cod+"";
	try{
		c=bbdd.getConexion();	
		s=c.createStatement();
		reg=s.executeQuery(cadena);
		if ( reg.next()){
			s.close();
		}else{
		s.close();
		throw new CineExcepcion("La Sala no se encuentra en nuestros Datos");
	}}
	catch ( SQLException e){
		//throw new ErrorTecnicoExcepcion(e.getMessage());
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		
	}
}

/**Método para borrar una Sala de la base de datos.
 * @param cod int: Codigo de la sala
 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
 **/
public static void borrarSala (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
		
	String c3="DELETE FROM Salas WHERE numero_sala="+ cod;
	try{
		c=bbdd.getConexion();
		s=c.createStatement();
		s.executeUpdate(c3);
		s.close();
	}
	catch ( SQLException e){
	throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
	}
	
}

}
