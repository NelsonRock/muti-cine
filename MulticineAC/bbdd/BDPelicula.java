package bbdd;

import java.sql.*;

import clases.Pelicula;
import excepciones.ErrorTecnicoExcepcion;
import excepciones.PeliculaExcepcion;

public class BDPelicula {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;


	public static void buscarPelicula(BaseDatos bbdd, String nombre) throws PeliculaExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT * FROM peliculas WHERE nombre='"+ nombre+"'";
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				s.close();
				throw new PeliculaExcepcion("La Película ya esta contratada");
			}
			s.close();
		}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	
	
	}
	
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
			throw new PeliculaExcepcion("La Película no se encuentra en nuestros Datos");
		}}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	
	
	}
	
	public static void  añadirPelicula(Pelicula pelicula, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
		
		String cadena="INSERT INTO Peliculas (cod_cine, nombre, genero) VALUES(" + pelicula.getCod_cine() + ",'" + pelicula.getNombre() + "','" + pelicula.getGenero() +"')"; 	

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
	
    public static void borrarPelicula (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
 		
   	String c6="DELETE FROM Peliculas WHERE Cod_pelicula="+ cod;
		try{
			c=bbdd.getConexion();
			s=c.createStatement();
			s.executeUpdate(c6);
			s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}
		
	}


}
