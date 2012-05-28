package bbdd;

import java.sql.*;

import clases.Cine;
import excepciones.ErrorTecnicoExcepcion;
import excepciones.CineExcepcion;


public class BDCine {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;
	
	public static void buscarCine(BaseDatos bbdd, String nombre) throws CineExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT nombre FROM cines WHERE nombre='"+ nombre+"'";
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				s.close();
				throw new CineExcepcion("El Cine ya está registrado");
			}
			s.close();
			
		}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
	public static int buscarCod(BaseDatos bbdd, String nombre) throws CineExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT Cod_cine FROM cines WHERE nombre='"+ nombre+"'";
	    try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				int cod=reg.getInt(1);
				s.close();
				return cod;
			}else{
			s.close();
			throw new CineExcepcion("El Código del Cine no figura en nuestros Datos");
			}}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
	
	public static void buscarCodCine(BaseDatos bbdd, int cod) throws CineExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT nombre FROM cines WHERE Cod_cine="+ cod;
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				s.close();
			}else{
			s.close();
			throw new CineExcepcion("El Código del Cine no figura en nuestros Datos");
			}}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
	
	public static Cine informacionCine(BaseDatos bbdd, int cod) throws CineExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT * FROM cines WHERE Cod_cine="+ cod;
		Cine cine=null;
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				cine=new Cine(reg.getInt(1), reg.getString(2), reg.getString(3), reg.getString(4), reg.getString(5), reg.getDate(6));
				s.close();
				return cine;
				
			}
			s.close();
			throw new CineExcepcion("El Código del Cine no figura en nuestros Datos");
			}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
	
	

	public static void  añadirCine(Cine cine, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
	
		String cadena="INSERT INTO cines (nombre, provincia, poblacion, direccion, fecha_alta) VALUES('" + cine.getNombre() + "','" + cine.getProvincia() + "','" + cine.getPoblacion() +"','" + cine.getDireccion()+"','" +cine.getFecha_alta()+"')"; 	

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
	public static void borrarCine (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion,SQLException{
		String c1="DELETE FROM Cuentas WHERE Id_emple in(select Id_emple from Empleados where Cod_cine="+ cod+ ")";
		String c2="DELETE FROM Empleados WHERE Cod_cine="+ cod;
		String c3="DELETE FROM Salas WHERE Cod_cine="+ cod;
		String c4="DELETE FROM Facturas WHERE Cod_cine="+ cod;
		String c5="DELETE FROM Informes WHERE Cod_cine="+ cod;
		String c6="DELETE FROM Peliculas WHERE Cod_cine="+ cod;
		String c7="DELETE FROM Cines WHERE Cod_cine="+ cod;
		try{
			c=bbdd.getConexion();
			c.setAutoCommit(false);
			s=c.createStatement();
			s.executeUpdate(c1);	
			s.executeUpdate(c2);	
			s.executeUpdate(c3);	
			s.executeUpdate(c4);	
			s.executeUpdate(c5);	
			s.executeUpdate(c6);	
			s.executeUpdate(c7);
			c.commit();
			
			c.setAutoCommit(true);
			s.close();
		}
		catch ( SQLException e){
			System.out.println(e.getMessage());
			c.rollback();
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}
	}
		
		
	public static void borrarCuentas (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
		
		String c1="DELETE FROM Cuentas WHERE Id_emple in(select Id_emple from Empleados where Cod_cine="+ cod+ ")";
		try{
			c=bbdd.getConexion();
			s=c.createStatement();
			s.executeUpdate(c1);			
			s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}
		
	}
	
     public static void borrarEmpleados (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
		
    	 String c2="DELETE FROM Empleados WHERE Cod_cine="+ cod;
		try{
			c=bbdd.getConexion();
			s=c.createStatement();
			s.executeUpdate(c2);
			s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}
		
	}
     
     public static void borrarSalas (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
 		
    	String c3="DELETE FROM Salas WHERE Cod_cine="+ cod;
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
     public static void borrarFacturas (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
 		
    	String c4="DELETE FROM Facturas WHERE Cod_cine="+ cod;
		try{
			c=bbdd.getConexion();
			s=c.createStatement();
			s.executeUpdate(c4);
			s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}
		
	}
     
     public static void borrarInformes (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
 		
    	 String c5="DELETE FROM Informes WHERE Cod_cine="+ cod;
		try{
			c=bbdd.getConexion();
			s=c.createStatement();
			s.executeUpdate(c5);
			s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}
		
	}
     
     public static void borrarPeliculas (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
 		
    	String c6="DELETE FROM Peliculas WHERE Cod_cine="+ cod;
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
     
    public static void borrarCines (int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
   		
    	String c7="DELETE FROM Cines WHERE Cod_cine="+ cod;
		try{
			c=bbdd.getConexion();
			s=c.createStatement();
			s.executeUpdate(c7);
			s.close();
		}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}
		
	}
    
    public static void trasladarCine (String provincia, String poblacion, String direccion, int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
    	
    	String cadena="UPDATE Cines set provincia='" + provincia+"', poblacion='" + poblacion+ "', direccion='"+direccion+"' where Cod_cine="+cod;
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


}

