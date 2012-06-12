package bbdd;

import java.sql.*;

import clases.Pelicula;
import excepciones.ErrorTecnicoExcepcion;
import excepciones.PeliculaExcepcion;

public class BDPelicula {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;

	/**M�todo para buscar una Pelicula en la base de datos.
	 * @param nombre String: Nombre de la pelicula
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws PeliculaExcepcion Exception: Si la pelicula ya esta contratada
	 * @throws ErrorTecnicoExcepcion Exception: Si el m�todo no ha podido ejecutar la sentencia sql.
	 **/
	public static void buscarPelicula(BaseDatos bbdd, String nombre) throws PeliculaExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT * FROM peliculas WHERE nombre='"+ nombre+"'";
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				s.close();
				throw new PeliculaExcepcion("La Pel�cula ya esta contratada");
			}
			s.close();
		}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos t�cnicos no podemos atender su petici�n");
		}
	}
	
	/**M�todo para buscar el codigo de una Pelicula en la base de datos.
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @param cod int: Codigo de la pelicula
	 * @throws PeliculaExcepcion Exception: Si la pelicula no se encuentra en la base de datos
	 * @throws ErrorTecnicoExcepcion Exception: Si el m�todo no ha podido ejecutar la sentencia sql.
	 **/
	public static void buscarCodPelicula(BaseDatos bbdd, int cod) throws PeliculaExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT Cod_pelicula FROM peliculas WHERE Cod_pelicula="+ cod;
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				s.close();
				
			}else{
			s.close();
			throw new PeliculaExcepcion("La Pel�cula no se encuentra en nuestros Datos");
		}}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos t�cnicos no podemos atender su petici�n");
			
		}
	
	
	}
	
	/**M�todo para a�adir una Pelicula a la base de datos.
	 * @param pelicula Pelicula: Objeto Pelicula
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el m�todo no ha podido ejecutar la sentencia sql.
	 **/
	public static void  a�adirPelicula(Pelicula pelicula, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
		
		String cadena="INSERT INTO Peliculas (cod_cine, nombre, genero) VALUES(" + pelicula.getCod_cine() + ",'" + pelicula.getNombre() + "','" + pelicula.getGenero() +"')"; 	

		try{
		c=bbdd.getConexion();
		s=c.createStatement();
		s.executeUpdate(cadena);
		s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos t�cnicos no podemos atender su petici�n");
		}	



	}
	
	/**M�todo para borrar una Pelicula de la base de datos.
	 * @param cod int: Codigo de la pelicula
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el m�todo no ha podido ejecutar la sentencia sql.
	 **/
    public static void borrarPelicula (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
 		
   	String c6="DELETE FROM Peliculas WHERE Cod_pelicula="+ cod;
		try{
			c=bbdd.getConexion();
			s=c.createStatement();
			s.executeUpdate(c6);
			s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos t�cnicos no podemos atender su petici�n");
		}
		
	}


}
