
package leerconsola;

import java.io.*;

public class Consola{
		
static BufferedReader br=new BufferedReader( new InputStreamReader(System.in) );
/*
 *import leerconsola.*;

                 * Consola.LeeCadena
                 * Consola.LeeByte
                 * Consola.LeeEntero
                 * Consola.LeeLong
                 * Consola.LeeFloat
                 * Consola.LeeDouble
                 * Consola.LeeBoolean
                 * retorna false, si has introducido algo incorrecto.
                 * Consola.LeeChar
                 * Consola.ComprobarCadena (String x)
                 */
				
public static String LeeCadena() {
String nombre="";
	try{
	nombre=br.readLine();
                
	} catch(IOException e){
	System.out.println("Error grave I/O");
	System.exit(0);
	}
	return nombre;
	}
		
public static byte LeeByte() {
	
boolean correcto=true;
byte b=0;
do
{
    try{
    correcto=true;
    b= Byte.valueOf(br.readLine().trim()).byteValue();
    }
    catch( NumberFormatException e){
    System.out.println("Introduce Correctamente el valor");
    correcto=false;
    }
    catch(IOException e){
    System.out.println("Error grave I/O");
    System.exit(0);
    }
}
while (!correcto);
return b;
}
		
public static int LeeEntero() {
	
boolean correcto=true;
int b=0;
do
{
    try{
    correcto=true;
    b= Integer.valueOf(br.readLine().trim()).intValue();
    }
    catch( NumberFormatException e){
    System.out.println("Introduce Correctamente el valor");
    correcto=false;
    }
    catch(IOException e){
    System.out.println("Error grave I/O");
    System.exit(0);
    }
}
while (!correcto);
return b;
}
		
public static long LeeLong() {
	
boolean correcto=true;
long b=0;
do
{
    try{
    correcto=true;
    b= Long.valueOf(br.readLine().trim()).longValue();
    }
    catch( NumberFormatException e){
    System.out.println("Introduce Correctamente el valor");
    correcto=false;
    }
    catch( IOException e){
    System.out.println("Error grave I/O");
    System.exit(0);
    }
}
while (!correcto);
return b;
}
		
		
public static float LeeFloat() {
boolean correcto=true;
float b=0;
do
{
    try{
    correcto=true;
    b= Float.valueOf(br.readLine().trim()).floatValue();
    }
    catch( NumberFormatException e){
    System.out.println("Introduce Correctamente el valor");
    correcto=false;
    }
    catch(IOException e){
    System.out.println("Error grave I/O");
    System.exit(0);
    }
}
while (!correcto);
return b;
}
		
public static double LeeDouble() {
boolean correcto=true;
double b=0;
do
{
    try{
    correcto=true;
    b= Double.valueOf(br.readLine().trim()).intValue();
    }
    catch( NumberFormatException e){
    System.out.println("Introduce Correctamente el valor");
    correcto=false;
    }
    catch(IOException e){
    System.out.println("Error grave I/O");
    System.exit(0);
    }
}
while (!correcto);
return b;
}
                
public static boolean LeeBoolean() {
boolean b=false;
    try{
    b= Boolean.valueOf(br.readLine().trim()).booleanValue();
    }
    catch(IOException e){
    System.out.println("Error grave I/O");
    System.exit(0);
    }
return b;
}
		
public static char LeeChar(){
char c=' ';
    try{
    c=br.readLine().charAt(0);
    }
    catch(IOException e){
    System.out.println("Error grave I/O");
    System.exit(0);	
    }
return c;
}
		
 static void ComprobarCadena (String x ) {
     boolean correcto; 
     x=x.toLowerCase();
      do{
      correcto=true;    
      for (int i=0;i<x.length();i++){
          if(x.charAt(i) <'a' || x.charAt(i) >'z'){
           System.out.println("Introduce Correctamente el valor");
           correcto=false;
           break;
          }
        
        if (!correcto)//if (correcto==false)
            x=Consola.LeeCadena();
          }
          
      
      }while (!correcto);

	}

}
