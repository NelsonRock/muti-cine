package bbdd;

import java.sql.*;

import clases.Empleado;
import excepciones.UsuarioNoExisteExcepcion;
import excepciones.ErrorTecnicoExcepcion;
import excepciones.EmpleadoExcepcion;

public class BDEmpleado {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;
	
	public static String buscarLogin(BaseDatos bbdd, String login, String password)throws UsuarioNoExisteExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT tipo_cuenta FROM cuentas " +
				"WHERE usuario='"+ login+"' and contraseña='"+ password+"'";
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				String tipo=reg.getString(1);
				s.close();
				return tipo;
			}
			s.close();
			throw new UsuarioNoExisteExcepcion("Este Usuario no está registrado");
		}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
	
public static void  añadirCuenta(int idemple, String login, String password, String tipo_c, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
		
		String cadena="INSERT INTO cuentas VALUES('" + idemple + "','" + login + "','" + password +"','" + tipo_c +"')"; 	

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

public static void actualizarCuenta (int idemple, String login, String password, String tipo_c, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
	
	String cadena="UPDATE cuentas set usuario='" + login+"', contraseña='"+password+"', tipo_cuenta='"+tipo_c+"' where Id_emple="+idemple;
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
	
	public static void buscarEmpleado(BaseDatos bbdd, String dni) throws EmpleadoExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT * FROM empleados WHERE dni='"+ dni+"'";
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				s.close();
				throw new EmpleadoExcepcion("El Empleado ya está contratado");
			}
			s.close();
		}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
	
	public static int buscarIdEmple(BaseDatos bbdd, String dni) throws EmpleadoExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT Id_emple FROM empleados WHERE dni='" + dni +"'";
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				int id=reg.getInt(1);
				s.close();
				return id;
			}else{
			s.close();
			throw new EmpleadoExcepcion("El Empleado no figura en nuestros Datos");
		}}
		catch ( SQLException e){
		throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
		}
	}
	
	
	public static void  añadirEmpleado(Empleado emple, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
		
		String cadena="INSERT INTO empleados (cod_cine, nombre, apellidos, dni, oficio) VALUES('" + emple.getCod_cine() + "','" + emple.getNombre() + "','" + emple.getApellidos() +"','" + emple.getDni() +"','" + emple.getOficio() +"')"; 	

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
	
    public static void trasladarEmpleado (int idemple, int cod, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
    	
    	String cadena="UPDATE Empleados set Cod_cine=" + cod+" where Id_emple="+idemple;
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
	
 public static void SetdatosEmpleado (int idemple, String nombre, String apellidos, String dni, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
    	
    	String cadena="UPDATE Empleados set nombre='" + nombre+"', apellidos='"+ apellidos+"', dni='"+dni+"' where Id_emple="+idemple;
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
 
 public static void OficioEmpleado (int idemple, String oficio, BaseDatos bbdd)throws ErrorTecnicoExcepcion{
 	
 	String cadena="UPDATE Empleados set oficio='" + oficio +"' where Id_emple="+idemple;
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
 
 public static Empleado informacionEmpleado(BaseDatos bbdd, int cod) throws EmpleadoExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT * FROM empleados WHERE Cod_cine="+ cod;
		Empleado emple=null;
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				emple=new Empleado(reg.getInt(1), reg.getInt(2), reg.getString(3), reg.getString(4), reg.getString(5), reg.getString(6));
				s.close();
				return emple;
				
			}
			s.close();
			throw new EmpleadoExcepcion("El Empleado no figura en nuestros Datos");
			}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
 
 public static Empleado informacionEmple(BaseDatos bbdd, int idemple) throws EmpleadoExcepcion, ErrorTecnicoExcepcion{
		String cadena="SELECT * FROM empleados WHERE Id_emple="+idemple;
		Empleado emple=null;
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next()){
				emple=new Empleado(reg.getInt(1), reg.getInt(2), reg.getString(3), reg.getString(4), reg.getString(5), reg.getString(6));
				s.close();
				return emple;
				
			}
			s.close();
			throw new EmpleadoExcepcion("El Empleado no figura en nuestros Datos");
			}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
	
	
}
