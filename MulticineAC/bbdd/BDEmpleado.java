package bbdd;

import java.sql.*;

import clases.Empleado;
import excepciones.UsuarioNoExisteExcepcion;
import excepciones.ErrorTecnicoExcepcion;
import excepciones.EmpleadoExcepcion;
import java.util.*;

public class BDEmpleado {
	private static Statement s;
	private static Connection c;
	private static ResultSet reg;
	
	/**Método para comprobar los datos de inicio de sesion
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @param login String: Nombre de inicio de sesion de un empleado
	 * @param password String: Contraseña de inicio de sesion de un empleado
	 * @return tipo String: Retorna el tipo de cuenta del empleado (Administrador/Usuario)
	 * @throws UsuarioNoExisteExcepcion Exception: Si el usuario no esta registrado en la base de datos.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
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
	
	/**Método para añadir una cuenta de empleado a la base de datos
	 * @param idemple int: El numero del empleado
	 * @param login String: Nombre de inicio de sesion de un empleado
	 * @param password String: Contraseña de inicio de sesion de un empleado
	 * @param tipo_c String: El tipo de cuenta del empleado (Administrador/Usuario)
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
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

	/**Método para actualizar una cuenta de empleado
	 * @param idemple int: El numero del empleado
	 * @param login String: Nombre de inicio de sesion de un empleado
	 * @param String: Contraseña de inicio de sesion de un empleado
	 * @param tipo_c String: El tipo de cuenta del empleado (Administrador/Usuario)
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
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
	
	/**Método para buscar un empleado en la base de datos
	 * para confirmar que no esta ya contratado
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @param dni String: El dni del empleado
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 * @throws EmpleadoExcepcion Exception: Si el empleado ya esta contratado
	 **/
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
	
	/**Método para buscar el numero de un empleado
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @param dni String: El dni del empleado
	 * @return id int: Retorna el numero del empleado
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 * @throws EmpleadoExcepcion Exception: Si el empleado no figura en la base de datos
	 **/
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
	
	/**Método para contratar a un empleado
	 * @param emple Empleado: Objeto Empleado
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
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
	
	/**Método para cambiar un empleado de cine
	 * @param idemple int: El numero del empleado
	 * @param cod int: Codigo del nuevo cine
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
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
	
    /**Método para modificar los datos de un empleado
	 * @param idemple int: Numero del empleado
	 * @param nombre String: Nombre del empleado (Nuevo nombre, si ha variado)
	 * @param apellidos String: Apellidos del empleado (Nuevos apellidos, si han variado)
	 * @param dni String: DNI del empleado (Nuevo dni, si ha variado)
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
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
 
    /**Método para modificar el oficio de un empleado
	 * @param idemple int: Numero del empleado
	 * @param oficio String: Nuevo oficio del empleado (Taquillero/Gerente)
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
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
    
	 
    /**Método para extraer informacion de un Empleado
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @param idemple int: Numero del Empleado
	 * @param emple Empleado: Retorna un Objeto Empleado
	 * @throws EmpleadoExcepcion Exception: Si el empleado no figura en la base de datos
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
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
    /**Método para extraer informacion de los Empleados de un Cine
	 * @param bbdd BaseDatos: La Base de Datos a la que se conecta.
	 * @param cod int: Codigo del cine
	 * @return empleados Vector: Retorna un vector con la informacion de los empleados.
	 * @throws ErrorTecnicoExcepcion Exception: Si el método no ha podido ejecutar la sentencia sql.
	 **/
    public static Vector EmpleadoVector(BaseDatos bbdd, int cod) throws ErrorTecnicoExcepcion{
		String cadena="SELECT * FROM empleados WHERE Cod_cine="+ cod;
		Vector emples = new Vector();
		try{
			c=bbdd.getConexion();	
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){
				emples.add(new Empleado(reg.getInt(1), reg.getInt(2), reg.getString(3), reg.getString(4), reg.getString(5), reg.getString(6)));
				s.close();		
			}
			s.close();
			return emples;
			}
		catch ( SQLException e){
			//throw new ErrorTecnicoExcepcion(e.getMessage());
			throw new ErrorTecnicoExcepcion("Por motivos técnicos no podemos atender su petición");
			
		}
	}
	
}
