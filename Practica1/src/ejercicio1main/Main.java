package ejercicio1main;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import ejercicio1datos.Critica;
import ejercicio1datos.Usuario;
import ejercicio1operaciones.GestorCriticas;


public class Main {
	
	public static void main(String [] args) {
		
		GestorCriticas CManager = GestorCriticas.getInstance();
		
		CManager.load();
		
		Scanner read = new Scanner(System.in);
		
		int loged = 0;
		
		do {
			
			System.out.println("INICIAR SESIÓN / REGISTRARSE.");
			System.out.println("1. Iniciar Sesión.\n2. Registrarse.");
			int option = read.nextInt();
			
			if(option == 1) {
				
				System.out.println("Usuario:");
				String user = read.next();
				
				System.out.println("Contraseña:");
				String password = read.next(); //pasar a md5
				
				loged = CManager.login(user.toLowerCase(), password);
				
				if(loged == 0) {
					
					System.out.println("Usuario incorrecto.\n");
				}
				
			}
			else if(option == 2) {
				
				int avaiableUser = 1;
				
				String username;
				
				do {
					
					System.out.println("Usuario:");
					username = read.next();
					
					avaiableUser = CManager.verifyUser(username.toLowerCase());
					
					if (avaiableUser == 0) {
						
						System.out.println("Este usuario no está disponible.");
					}
				} while(avaiableUser == 0);
				
				
				int avaiableMail = 1;
				
				String mail;
				
				do {
					
					System.out.println("Correo electrónico:");
					mail = read.next();
					
					avaiableMail = CManager.verifyMail(mail.toLowerCase());
					
					if (avaiableMail == 0) {
						
						System.out.println("Este correo electrónico no está disponible.");
					}
					
				} while (avaiableMail == 0);
				
				System.out.println("Nombre y apellidos:");
				String name = read.next(); //Solo coge una palabra
				
				System.out.println("Contraseña:");
				String password = read.next(); //pasar md5
				
				loged = CManager.crearUsuario(mail.toLowerCase(), name, name, username.toLowerCase(), password);
				
				System.out.println("Usuario creado correctamente.\n");
				
			}
			else {
				
				System.out.println("Opción incorrecta.\n");
			}
			
		} while (loged == 0);
		
		
		if(loged == 1) {
			
			int exit = 0;
			
			do {
			
				System.out.println("\nMENU");
				System.out.println("1. Crear crítica.");
				System.out.println("2. Eliminar crítica.");
				System.out.println("3. Ver críticas.");
				System.out.println("4. Valorar crítica.");
				System.out.println("5. Buscar críticas.");
				System.out.println("6. Salir.");

				int option = read.nextInt();
				
				String titulo;
				String username;
				switch (option) {
				
				case 1 :
					
					System.out.println("CREAR CRÍTICA");
					
					System.out.println("Introduzca el titulo de la crítica");
					titulo = read.next();
					
					System.out.println("Introduzca la reseña de la crítica");
					String resenia = read.next();
					
					System.out.println("Introduzca su usuario");
					username = read.next();
					
					CManager.crearCritica(titulo, resenia, username.toLowerCase());
				
					break;
				
				case 2 :
					
					System.out.println("ELIMINAR CRÍTICA");
					
					System.out.println("Introduzca el titulo de la crítica");
					titulo = read.next();
					System.out.println("Introduzca su usuario");
					username = read.next();
					
					CManager.borrarCritica(username.toLowerCase(), titulo);
					
					break;
				
				case 3 :
					
					System.out.println("VER CRÍTICA");
					CManager.verCriticas();
					
					break;
					
				case 4 :
					
					System.out.println("VALORAR CRÍTICA");
					
					System.out.println("Introduzca el titulo de la crítica");
					titulo = read.next();
					System.out.println("Introduzca su usuario");
					username = read.next();
					
					System.out.println("Introduzca la puntuacion del espectaculo");
					int valoracion = read.nextInt();
					
					CManager.votarCritica(titulo, username.toLowerCase(), valoracion);
					
					break;
				
				case 5 :
					System.out.println("BUSCAR CRÍTICA");
					
					System.out.println("Introduzca el nombre del autor de la crítica");
					
					String nickcreador = read.next();
					CManager.buscarCriticas(nickcreador);
					
					break;
				case 6 :
					
					exit = 1;
					
					break;
				
				default :
					
					System.out.println("Opción incorrecta.");
					
					break;
					
				}
				
				
			} while(exit == 0);
		}
			
		read.close();	
			
		CManager.save();
		
	}
	
}