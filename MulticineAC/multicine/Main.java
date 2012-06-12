package multicine;
import java.util.*;
import bbdd.*;
import clases.*;
import excepciones.*;
import leerconsola.*;
import java.sql.*;
public class Main {

	/**
	*
	* @author Olga Yanguas
	* @author Nelson Rodriguez
	* @author Manuel Romero
	* @author Alvaro Bilbao
	* @version 24/05/2012
	*/
	static int idemple=0;
	static String continua=null;
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		GregorianCalendar fecha_alta=new GregorianCalendar();
		Vector emples;
		Cine cine;
		Empleado emple;
		Factura fac;
		Informe inf;
		Pelicula pelicula;
		Sala sala;
		String registro=null, oficio=null, resp=null, resp2=null, resp3=null, nombre=null;
		int op=0,menu=0, cod=0, op3=0;
		BaseDatos mibase=new BaseDatos("jdbc:odbc:multicines","sun.jdbc.odbc.JdbcOdbcDriver");
		
				System.out.println("                           +-----------------+                            ");
				System.out.println("                           |    MULTICINES   |                            ");
				System.out.println("                           |    -------1.0   |                            ");
				System.out.println("                 ______....+-----------------+....________                ");
				System.out.println("....------''''''' ______.........................________ ```````------....");
				System.out.println("|....------'''''''                                       ```````------....|");
				System.out.println("|   +--------------+        _________________            +------------+   |");
				System.out.println("|   | Olga Yanguas |       |                 |           |Manu Romero |   |");
				System.out.println("|   | Nelson Rodgz |       |_________________|           |    PLQS    |   |");
				System.out.println("|   +--------------+       | |      |      | |           +------------+   |");
				System.out.println("|                          | |______|______| |                            |");
				System.out.println("|__________________________| |_____[|]_____| |____________________________|");
				System.out.println("|__________________________| |      |      | |____________________________|");
				System.out.println("|                          | |______|______| |                            |");
				System.out.println("|__________________________|_________________|____________________________|");
				System.out.println("                           |        |        |                             ");
				System.out.println("                          |         |         |                            ");
				System.out.println("                         |          |          |                           ");
				System.out.println("                        |           |           |                          ");
				System.out.println("                       |            |            |                         ");
				System.out.println("                      |             |             |                        ");
				System.out.println("                     |              |              |                       ");
				System.out.println("                    |               |               |                      ");

		
		
		
		
		do{
		System.out.print("Login:");
		String login=Consola.LeeCadena();
		System.out.print("Password: ");
		String password=Consola.LeeCadena();
		
		try{
		mibase.abrir();
		registro=BDEmpleado.buscarLogin(mibase, login, password);
		mibase.cerrar();
		}
		catch(UsuarioNoExisteExcepcion e){
		System.out.println(e.getMessage());
		System.exit(0);
		}
		catch(ErrorTecnicoExcepcion e){
			System.out.println(e.getMessage());
			System.exit(0);
			}
		if (registro.equals("ADMINISTRADOR")){
			do{
				menu=menuAdmin();
				switch(menu){
				
				
//case 1 TERMINADO Y PROBADO					
					case 1: //Añadir Cine
						
					System.out.print("Introduce el Nombre del Cine: ");
					String nombreCine=Consola.LeeCadena();
					System.out.print("Introduce la Provincia del Cine: ");
					String provincia=Consola.LeeCadena();
					System.out.print("Introduce la Poblacion del Cine: ");
					String poblacion=Consola.LeeCadena();
					System.out.print("Introduce Direccion del Cine: ");
					String direccion=Consola.LeeCadena();
					System.out.print("Fecha de Ingreso del Cine: ");
					escribirFecha(fecha_alta); 
					
					cine = new Cine(nombreCine, provincia, poblacion, direccion, fecha_alta);
					try{
						
						mibase.abrir();
						BDCine.buscarCine(mibase, nombreCine);
						BDCine.añadirCine(cine, mibase);
						mibase.cerrar();
						System.out.println("NUEVO CINE GUARDADO");
						System.out.println(cine.toString());
						
						System.out.println("\nAsigna un Empleado Gerente para tu nuevo Cine: ");
						System.out.print("Introduce el Nombre del Empleado: ");
						nombre=Consola.LeeCadena();
						System.out.print("Introduce los Apellidos del Empleado: ");
						String apellidos=Consola.LeeCadena();
						System.out.print("Introduce el DNI del Empleado: ");
						String dni=Consola.LeeCadena();
						mibase.abrir();
						cod=BDCine.buscarCod(mibase, nombreCine);
						mibase.cerrar();
						emple = new Empleado (cod, nombre, apellidos, dni, "GERENTE");
						mibase.abrir();
						BDEmpleado.añadirEmpleado(emple, mibase);
						mibase.cerrar();
						crearCuenta(mibase, emple, 1);
					}
					catch(ErrorTecnicoExcepcion e){
					System.out.println(e.getMessage());
					}
					catch(CineExcepcion e){
					System.out.println(e.getMessage());
					}
					continuar();
					break;
	
//case 2 TERMINADO Y PROBADO						
					case 2: //Cerrar Cine
						
						
						System.out.println("Indica el Código del Cine:");
						cod=Consola.LeeEntero();
					try{
							
						mibase.abrir();
						BDCine.borrarCine(cod, mibase);
						mibase.cerrar();
						System.out.println("\tCINE BORRADO");
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						
						continuar();
					break;
	
//case 3 TERMINADO Y PROBADO						
					case 3: //Trasladar Cine
						
						
						System.out.println("Indica el Código del Cine:");
						cod=Consola.LeeEntero();
						try{
							
							mibase.abrir();
							BDCine.buscarCodCine(mibase, cod);
							mibase.cerrar();
							
							System.out.println("TRASLADAR EL CINE: ");
							do{
							System.out.print("Introduce la nueva Provincia del Cine: ");
							provincia=Consola.LeeCadena();
							System.out.print("Introduce la nueva Poblacion del Cine: ");
							poblacion=Consola.LeeCadena();
							System.out.print("Introduce la nueva Direccion del Cine: ");
							direccion=Consola.LeeCadena();
							System.out.print("¿Los datos son correctos?(SI/NO)");
							resp=Consola.LeeCadena();
							resp=resp.toUpperCase();
							while (!resp.equals("NO") && !resp.equals("SI")){
								System.out.println("Lo siento, no entiendo tu respuesta");
								System.out.print("¿Los datos son correctos?(SI/NO)");
								resp=Consola.LeeCadena();
								resp=resp.toUpperCase();
							}
							}while(resp.equals("NO"));
							if (resp.equals("SI")){
								mibase.abrir();
								BDCine.trasladarCine(provincia, poblacion, direccion, cod, mibase);
								mibase.cerrar();
								System.out.println("CINE TRASLADADO");
							}
							
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(CineExcepcion e){
						System.out.println(e.getMessage());
						}
						continuar();
					break;

//case 4 TERMINADO Y PROBADO					
					case 4: //Informacion de un Cine. 
						System.out.println("Indica el Código del Cine:");
						cod=Consola.LeeEntero();
						try{
							
						mibase.abrir();
						BDCine.buscarCodCine(mibase, cod);
						cine=BDCine.informacionCine(mibase, cod);
						System.out.println(cine.toString());
						mibase.cerrar();
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(CineExcepcion e){
						System.out.println(e.getMessage());
						}
						continuar();
					break;

//case 5 TERMINADO Y PROBADO						
					case 5: //Contratar un Empleado
						
						System.out.print("Introduce el Código del Cine: ");
						cod=Consola.LeeEntero();
						System.out.print("Introduce el Nombre del Empleado: ");
						nombre=Consola.LeeCadena();
						System.out.print("Introduce los Apellidos del Empleado: ");
						String apellidos=Consola.LeeCadena();
						System.out.print("Introduce el DNI del Empleado: ");
						String dni=Consola.LeeCadena();
						do{
						System.out.print("Introduce el Oficio del Empleado (TAQUILLERO/GERENTE): ");
						oficio=Consola.LeeCadena();
						oficio=oficio.toUpperCase();
						}while(!oficio.equals("TAQUILLERO") && !oficio.equals("GERENTE"));
						emple = new Empleado(cod, nombre, apellidos, dni, oficio);
						try{
							
							mibase.abrir();
							BDCine.buscarCodCine(mibase, cod);
							BDEmpleado.buscarEmpleado(mibase, dni);
							BDEmpleado.añadirEmpleado(emple, mibase);
							mibase.cerrar();
							crearCuenta(mibase, emple, 1);
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(EmpleadoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(CineExcepcion e){
						System.out.println(e.getMessage());	
						}
						continuar();
						
					break;

//case 6 TERMINADO Y PROBADO	
					case 6: //Modificar Datos de un Empleado
						
						System.out.println("Indica el DNI del Empleado:");
						dni=Consola.LeeCadena();
						try{
							
							mibase.abrir();
							idemple=BDEmpleado.buscarIdEmple(mibase , dni);
							mibase.cerrar();
							
							System.out.println("MODIFICAR DATOS DEL EMPLEADO Nº"+idemple+" :");
							
							do{
								System.out.println("1. Trasladar Empleado a otro Cine");
								System.out.println("2. Modificar Datos del Empleado (Nombre, apellidos, DNI) ");
								System.out.println("3. Cambiar de Oficio al Empleado");
								System.out.println("4. No modificar Datos / Salir");
								op3=Consola.LeeEntero();
								
								switch (op3){
								
								case 1:
									System.out.print("Introduce el nuevo Codigo del Cine: ");
									cod=Consola.LeeEntero();	
									mibase.abrir();
									BDCine.buscarCodCine(mibase, cod);
									BDEmpleado.trasladarEmpleado(idemple, cod, mibase);
									mibase.cerrar();
									System.out.println("TU EMPLEADO HA SIDO TRASLADADO");
									continuar();
									break;
									
								case 2:
									System.out.print("Nombre del Empleado: ");
									nombre=Consola.LeeCadena();
									System.out.print("Apellidos del Empleado: ");
									apellidos=Consola.LeeCadena();
									System.out.print("DNI del Empleado: ");
									dni=Consola.LeeCadena();
									mibase.abrir();
									BDEmpleado.SetdatosEmpleado(idemple, nombre, apellidos, dni, mibase);
									mibase.cerrar();						
									System.out.println("TU EMPLEADO HA SIDO MODIFICADO");	
									continuar();
									break;
									
									
								case 3:
									do{
										System.out.print("Introduce el Oficio del Empleado (TAQUILLERO/GERENTE): ");
										oficio=Consola.LeeCadena();
										oficio=oficio.toUpperCase();
										}while(!oficio.equals("TAQUILLERO") && !oficio.equals("GERENTE"));
									
									mibase.abrir();
									BDEmpleado.OficioEmpleado(idemple, oficio, mibase);
									emple=BDEmpleado.informacionEmple(mibase, idemple);
									mibase.cerrar();
									
									crearCuenta(mibase, emple, 2);
									System.out.println("TU EMPLEADO HA CAMBIADO AL OFICIO "+ oficio);
									continuar();
									break;
								
								}
																
							}while(op3!=4);
							
						}
							catch(ErrorTecnicoExcepcion e){
							System.out.println(e.getMessage());
							}
							catch( EmpleadoExcepcion e){
							System.out.println(e.getMessage());
							
							}
							catch( CineExcepcion e){
							System.out.println(e.getMessage());
							}
						continuar();
						
					break;
							
					case 7: //Despedir Empleado. SOLO BORRAMOS SU CUENTA Y EL EMPLEADO, ¡¡¡LAS FACTURAS NO!!!
						System.out.println("DESPEDIR EMPLEADO. Precaución, al hacer esta operación pueden pasar los siguientes casos:");
						do{
						System.out.println(" 1) Las facturas asociadas al Empleado que desea despedir no serán eliminadas. " +
								"El ID del Empleado que aparece en la factura se le pasará a un Gerente de su respectivo Cine");
						System.out.println(" 2) Si desea despedir un GERENTE y es el único de su respectivo Cine," +
								" la aplicación asignará a otro EMPLEADO de ese mismo Cine como GERENTE");
						System.out.println(" 3) La Cuenta del EMPLEADO asignado como GERENTE no variará, " +
								"seguirá con una cuenta restringida de USUARIO");
						System.out.print("¿Desea Continuar?(SI/NO)");
						resp=Consola.LeeCadena();
						resp=resp.toUpperCase();
						while (!resp.equals("NO") && !resp.equals("SI")){
							System.out.println("Lo siento, no entiendo tu respuesta");
							System.out.print("¿Desea Continuar?(SI/NO)");
							resp=Consola.LeeCadena();
							resp=resp.toUpperCase();
						}
						}while(resp.equals("NO"));
						if(resp.equals("SI")){
							System.out.println("Indica el DNI del Empleado:");
							dni=Consola.LeeCadena();
							try{
								
								mibase.abrir();
								idemple=BDEmpleado.buscarIdEmple(mibase , dni);
								mibase.cerrar();
								
								System.out.println("BORRAR EL EMPLEADO Nº"+idemple+" :");
								
								
								System.out.println("Lo sentimos, en estos momentos no puedes hacer esta Operación");
								continuar();
								break;
								
								
							}	
							catch(ErrorTecnicoExcepcion e){
								System.out.println(e.getMessage());
							}
							catch( EmpleadoExcepcion e){
							System.out.println(e.getMessage());
							}
						}
						
						
						
						break;
								
					case 8: //Informacion de Empleados de un Cine
						System.out.println("Indica el Código del Cine:");
						cod=Consola.LeeEntero();
						try{
						mibase.abrir();
						BDCine.buscarCodCine(mibase, cod);
						emples=BDEmpleado.EmpleadoVector(mibase, cod);
						mibase.cerrar();
						if(emples.isEmpty())
							System.out.println("No hay empleados");
						else
							for(int i=0;i<emples.size();i++)
								System.out.println(emples.get(i));
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(CineExcepcion e){
						System.out.println(e.getMessage());
						}
						continuar();
					break;
	
//case 9 TERMINADO Y PROBADO						
					case 9: //Contratar Película. 
						System.out.println("Indica el Código del Cine:");
						cod=Consola.LeeEntero();
						try{
							
						mibase.abrir();
						BDCine.buscarCodCine(mibase, cod);
						mibase.cerrar();
						
						System.out.println("Titulo de la Pelicula: ");
						nombre=Consola.LeeCadena();
						System.out.println("Genero de la Pelicula: ");
						String genero=Consola.LeeCadena();
						
						pelicula= new Pelicula (cod, nombre, genero);
						
						mibase.abrir();
						BDPelicula.buscarPelicula(mibase, nombre);
						BDPelicula.añadirPelicula(pelicula, mibase);
						mibase.cerrar();
						
						System.out.println("PELICULA CONTRATADA");
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(CineExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(PeliculaExcepcion e){
						System.out.println(e.getMessage());
						}
						continuar();
						
						break;
						
//case 10 TERMINADO	Y PROBADO					
					case 10: //Deshechar Película
						
						System.out.println("Indica el Código de la Pelicula: ");
						cod=Consola.LeeEntero();
						try{
							
						mibase.abrir();
						BDPelicula.buscarCodPelicula(mibase, cod);
						BDPelicula.borrarPelicula(cod, mibase);
						mibase.cerrar();
						System.out.println("PELICULA DESECHADA");
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(PeliculaExcepcion e){
						System.out.println(e.getMessage());
						}
						continuar();
						break;
						
					case 11: //Mostrar Película/s de un Cine
						System.out.println("En estos momentos es imposible conectar con nuestra Base de Datos");
						   System.out.println("   ____________");
		                   System.out.println("  |LO SENTIMOS |            (__)          (__)          (__)");
		                   System.out.println("  | MUUUUUUCHO |            (uu)          (uu)          (uu)");
		                   System.out.println("  |    :-(     |     /-------**    /-------**    /-------** ");
		                   System.out.println("  |____________|    / |     ||    / |     ||    / |     ||   ");
		                   System.out.println("        ||         *  ||----||   *  ||----||   *  ||----||   ");
		                   System.out.println("        ||            ~~    ~~      ~~    ~~      ~~    ~~   ");
		                   continuar();
						break;
	
//case 12 TERMINADO Y PROBADO							
					case 12: //Añadir Sala
						System.out.println("Indica el Código del Cine:");
						cod=Consola.LeeEntero();
						try{
							
						mibase.abrir();
						BDCine.buscarCodCine(mibase, cod);
						mibase.cerrar();
						System.out.println("Asigna una Sala para tu Cine");
						System.out.print("Introduce la Capacidad de la Sala: ");
						int capacidad=Consola.LeeEntero();
						
						sala= new Sala (capacidad, cod);
						mibase.abrir();
						BDSala.añadirSala(sala, mibase);
						mibase.cerrar();
						System.out.println("SALA CREADA");
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(CineExcepcion e){
						System.out.println(e.getMessage());
						}
						
						continuar();
						break;

//case 13 TERMINADO	Y PROBADO					
					case 13: //Cerrar Sala
						System.out.println("Indica el Código de la Sala:");
						cod=Consola.LeeEntero();
						try{
							
							mibase.abrir();
							BDSala.buscarSala(mibase, cod);
							BDSala.borrarSala(cod, mibase);
							mibase.cerrar();
							
							System.out.println("SALA BORRADA");
						}
						catch(ErrorTecnicoExcepcion e){
						System.out.println(e.getMessage());
						}
						catch(CineExcepcion e){
						System.out.println(e.getMessage());
						}
						continuar();
						
						break;

					case 14: //Mostrar Sala/s de un Cine
						System.out.println("En estos momentos es imposible conectar con nuestra Base de Datos");
						   System.out.println("   ____________");
		                   System.out.println("  |LO SENTIMOS |            (__)          (__)          (__)");
		                   System.out.println("  | MUUUUUUCHO |            (uu)          (uu)          (uu)");
		                   System.out.println("  |    :-(     |     /-------**    /-------**    /-------** ");
		                   System.out.println("  |____________|    / |     ||    / |     ||    / |     ||   ");
		                   System.out.println("        ||         *  ||----||   *  ||----||   *  ||----||   ");
		                   System.out.println("        ||            ~~    ~~      ~~    ~~      ~~    ~~   ");
		                   continuar();
						break;

					case 15: //Mostrar Beneficios
						System.out.println("En estos momentos es imposible conectar con nuestra Base de Datos");
						   System.out.println("   ____________");
		                   System.out.println("  |LO SENTIMOS |            (__)          (__)          (__)");
		                   System.out.println("  | MUUUUUUCHO |            (uu)          (uu)          (uu)");
		                   System.out.println("  |    :-(     |     /-------**    /-------**    /-------** ");
		                   System.out.println("  |____________|    / |     ||    / |     ||    / |     ||   ");
		                   System.out.println("        ||         *  ||----||   *  ||----||   *  ||----||   ");
		                   System.out.println("        ||            ~~    ~~      ~~    ~~      ~~    ~~   ");
		                   continuar();
						break;
						
					case 16: //Mostrar Último Informe

						System.out.println("                             ___");
						System.out.println("                     /======/  ");
						System.out.println("            ______  //     /___");
						System.out.println("             |  |  //           :,");  
						System.out.println("     |_______|__|_//            ;:; Seccion en Construccion:");
						System.out.println("    _L_____________|o           ;;;;  El Informe no se genará si no han pasado 30 días");
						System.out.println("____(CCCCCCCCCCCCCC)___________;;;;;;____________________________________________________");
						continuar();
						break;
						
					case 18:
					System.exit(0);			
					break;
				
				}
			}while(menu!=17);
			}
		else{
			do{
				menu=menuUsu();
				switch(menu){

				case 1: // Compra de Entrada
					System.out.println("En estos momentos es imposible conectar con nuestra Base de Datos");
					   System.out.println("   ____________");
	                   System.out.println("  |LO SENTIMOS |            (__)          (__)          (__)");
	                   System.out.println("  | MUUUUUUCHO |            (uu)          (uu)          (uu)");
	                   System.out.println("  |    :-(     |     /-------**    /-------**    /-------** ");
	                   System.out.println("  |____________|    / |     ||    / |     ||    / |     ||   ");
	                   System.out.println("        ||         *  ||----||   *  ||----||   *  ||----||   ");
	                   System.out.println("        ||            ~~    ~~      ~~    ~~      ~~    ~~   ");
	                   continuar();
					break;
					
				case 2: //Mostrar Facturas
					System.out.println("En estos momentos es imposible conectar con nuestra Base de Datos");
					   System.out.println("   ____________");
	                   System.out.println("  |LO SENTIMOS |            (__)          (__)          (__)");
	                   System.out.println("  | MUUUUUUCHO |            (uu)          (uu)          (uu)");
	                   System.out.println("  |    :-(     |     /-------**    /-------**    /-------** ");
	                   System.out.println("  |____________|    / |     ||    / |     ||    / |     ||   ");
	                   System.out.println("        ||         *  ||----||   *  ||----||   *  ||----||   ");
	                   System.out.println("        ||            ~~    ~~      ~~    ~~      ~~    ~~   ");
	                   continuar();
					break;
					
				case 3: //Mostrar Películas del Catálogo
					System.out.println("En estos momentos es imposible conectar con nuestra Base de Datos");
					   System.out.println("   ____________");
	                   System.out.println("  |LO SENTIMOS |            (__)          (__)          (__)");
	                   System.out.println("  | MUUUUUUCHO |            (uu)          (uu)          (uu)");
	                   System.out.println("  |    :-(     |     /-------**    /-------**    /-------** ");
	                   System.out.println("  |____________|    / |     ||    / |     ||    / |     ||   ");
	                   System.out.println("        ||         *  ||----||   *  ||----||   *  ||----||   ");
	                   System.out.println("        ||            ~~    ~~      ~~    ~~      ~~    ~~   ");
	                   continuar();
					break;
					
				case 4: //Información de un Cine
					System.out.println("En estos momentos es imposible conectar con nuestra Base de Datos");
					   System.out.println("   ____________");
	                   System.out.println("  |LO SENTIMOS |            (__)          (__)          (__)");
	                   System.out.println("  | MUUUUUUCHO |            (uu)          (uu)          (uu)");
	                   System.out.println("  |    :-(     |     /-------**    /-------**    /-------** ");
	                   System.out.println("  |____________|    / |     ||    / |     ||    / |     ||   ");
	                   System.out.println("        ||         *  ||----||   *  ||----||   *  ||----||   ");
	                   System.out.println("        ||            ~~    ~~      ~~    ~~      ~~    ~~   ");
	                   continuar();
					break;
					
				
				case 6:
					System.exit(0);			
					break;
				
				}
			}while(menu!=5);
		}
}while(op!=17);
	}
	/**
     * continuar
     * Método estático para regular el flujo de texto.
     */
	public static void continuar() {
		System.out.println("Continuar (ENTER)");
		continua=Consola.LeeCadena();
	}
	
   /**
     * menuAdmin
     * Método estático donde se muestra el menú de un Gerente de un Cine. 
     * @return op Retorna la opcion elegida del menú.
     */
	public static int menuAdmin(){
		int op=0;
		do{
			System.out.println("\t\n MENÚ ADMINISTRADOR");
			System.out.println("\n Administrar Cines \n");
			System.out.println("\t1. Añadir Cine");
			System.out.println("\t2. Cerrar Cine");
			System.out.println("\t3. Trasladar Cine");
			System.out.println("\t4. Información de un Cine");
			System.out.println("\n Administrar Empleados \n");
			System.out.println("\t5. Contratar Empleado");
			System.out.println("\t6. Modificar Datos de un Empleado");
			System.out.println("\t7. Despedir Empleado");
			System.out.println("\t8. Informacion de Empleados de un Cine");
			System.out.println("\n Administrar Películas \n");
			System.out.println("\t9. Contratar Película");
			System.out.println("\t10. Deshechar Película");
			System.out.println("\t11. Mostrar Película/s de un Cine");
			System.out.println("\n Administrar Salas \n");
			System.out.println("\t12. Añadir Sala");
			System.out.println("\t13. Cerrar Sala");
			System.out.println("\t14. Mostrar Sala/s de un Cine");	
			System.out.println("\n INFORMES \n");
			System.out.println("\t15. Mostrar Beneficios");
			System.out.println("\t16. Mostrar Último Informe");
			System.out.println("17.Cambiar de Usuario");
			System.out.println("18.Salir");
			op=Consola.LeeEntero();
	}while(op< 1 || op>18);
		return op;
}
	
	 /**
     * menuUsu
     * Método estático donde se muestra el menú de un Taquillero de un Cine. 
     * @return op Retorna la opcion elegida del menú.
     */
	public static int menuUsu(){
		int op=0;
		do{
			System.out.println("\t\n MENÚ USUARIO");
			System.out.println("\n Compra de Entradas \n");
			System.out.println("\t1. Compra de Entrada");
			System.out.println("\t2. Mostrar Facturas");
			System.out.println("\n Películas \n");
			System.out.println("\t3. Mostrar Películas del Catálogo");
			System.out.println("\t4. Información de un Cine\n");
			System.out.println("5.Cambiar de Usuario");
			System.out.println("6. Salir");
			op=Consola.LeeEntero();
		}while(op<1 || op>6);
		return op;
	}
	/**
     * escribirFecha
     * Método estático donde se muestra la fecha del sistema.
     * @param fecha Recibe la fecha del sistema.
     */
	
    static void escribirFecha(GregorianCalendar fecha){
        int dia=fecha.get(Calendar.DATE);
        int mes=fecha.get(Calendar.MONTH);
        int anyo=fecha.get(Calendar.YEAR);
        int hora=fecha.get(Calendar.HOUR);
        int min=fecha.get(Calendar.MINUTE);
        int seg=fecha.get(Calendar.SECOND);
        System.out.println(dia+"/"+mes+"/"+anyo);
        System.out.println(hora+":"+min+":"+seg);
    }
    
    /**
     * crearCuenta
     * Método estático donde se crean cuentas para un determinado Cine
     * @param BaseDatos Recibe una Base de Datos a la que se le aplica el metodo.
     * @param Empleado Recibe un Objeto empleado al que se le aplica la creación de la Cuenta.
     */
    static void crearCuenta(BaseDatos mibase, Empleado emple, int m){
   
    	
    	try{
			mibase.abrir();
			idemple=BDEmpleado.buscarIdEmple(mibase, emple.getDni());
			mibase.cerrar();
		}
		catch(ErrorTecnicoExcepcion e){
		System.out.println(e.getMessage());
		}
		catch(EmpleadoExcepcion e){
		System.out.println(e.getMessage());
		}
		String nombre=emple.getNombre().toUpperCase();
		String apellidos=emple.getApellidos().toUpperCase();
		String usuario=nombre.substring(0, 2)+apellidos.substring(0, 2)+"0"+idemple;
		System.out.println("USUARIO: "+ usuario);
		String contraseña=idemple+usuario;
		System.out.println("CONTRASEÑA: "+ contraseña);
		if (emple.getOficio().equals("TAQUILLERO")){
			System.out.println("\n\t CUENTA DE USUARIO PARA EL TAQUILLERO "+ nombre + "  " + apellidos+ ", con ID "+ idemple);
		try{
			
			switch (m){
			case 1:
				mibase.abrir();
				BDEmpleado.añadirCuenta(idemple, "USU"+usuario, contraseña, "USUARIO", mibase);
				mibase.cerrar();
				break;
				
			case 2:
				mibase.abrir();
				BDEmpleado.actualizarCuenta(idemple, "USU"+usuario, contraseña, "USUARIO", mibase);
				mibase.cerrar();
				break;
			}
			
		}
		catch(ErrorTecnicoExcepcion e){
		System.out.println(e.getMessage());
		}
		}
		else{
			System.out.println("\n\t CUENTA DE ADMINISTRADOR PARA EL GERENTE "+ nombre + "  " + apellidos);
		try{
			switch (m){
			case 1:
			mibase.abrir();
			BDEmpleado.añadirCuenta(idemple, "ADM"+usuario, contraseña, "ADMINISTRADOR", mibase);
			mibase.cerrar();
			break;
			case 2:
				mibase.abrir();
				BDEmpleado.actualizarCuenta(idemple, "ADM"+usuario, contraseña, "ADMINISTRADOR", mibase);
				mibase.cerrar();
				break;
			}	
		}
		catch(ErrorTecnicoExcepcion e){
		System.out.println(e.getMessage());
		}	
		}
    	
    }
    
    
}
