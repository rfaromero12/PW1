package ejercicio1main;

import java.util.ArrayList;
import java.util.Scanner;

import ejercicio1datos.Critica;
import ejercicio1operaciones.GestorCriticas;


public class Main {
	
	public static void main(String [] args) {
		
		GestorCriticas CManager = GestorCriticas.getInstance();
		
		CManager.load();
		String username = "";
		String password = "";
		String mail = "";
		String name = "";
		String resenia = "";
		Scanner read = new Scanner(System.in);
		
		int loged = 0;
		
		do {
			
			System.out.println("INICIAR SESIÓN / REGISTRARSE.");
			System.out.println("1. Iniciar Sesión.\n2. Registrarse.");
			int option = read.nextInt();
			
			if(option == 1) {
				
				System.out.println("Usuario:");
				 username = read.next();
				
				System.out.println("Contraseña:");
				password = read.next();
				
				loged = CManager.login(username.toLowerCase(), password);
				
				if(loged == 0) {
					
					System.out.println("Usuario incorrecto.\n");
				}
				
			}
			else if(option == 2) {
				
				int avaiableUser;
				
				do {
					
					System.out.println("Usuario:");
					username = read.next();
					
					avaiableUser = CManager.verifyUser(username.toLowerCase());
					
					if (avaiableUser == 0) {
						
						System.out.println("Este usuario no está disponible.");
					}
				} while(avaiableUser == 0);
				
				
				int avaiableMail;
				
				
				
				do {
					
					System.out.println("Correo electrónico:");
					mail = read.next();
					
					avaiableMail = CManager.verifyMail(mail.toLowerCase());
					
					if (avaiableMail == 0) {
						
						System.out.println("Este correo electrónico no está disponible.");
					}
					
				} while (avaiableMail == 0);
				
				System.out.println("Nombre y apellidos:");
				read.nextLine();
				name = read.nextLine(); 				
				
				System.out.println("Contraseña:");
				
				password = read.next(); //pasar md5
				
				loged = CManager.crearUsuario(mail.toLowerCase(), name,username.toLowerCase(), password);
				
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
				System.out.println("6. Ver datos de usuario. ");
				System.out.println("7. Actualizar datos. ");
				System.out.println("8. Salir.");

				int option = read.nextInt();
				read.nextLine();
				String titulo;
				boolean receptor;
				switch (option) {
				
				case 1 :
					
					System.out.println("CREAR CRÍTICA");
					
					System.out.println("Introduzca el titulo de la crítica");
					titulo = read.nextLine();
					
					System.out.println("Introduzca la reseña de la crítica");
					resenia = read.nextLine();
					
			
					
					CManager.crearCritica(titulo, resenia, username.toLowerCase());
				
					break;
				
				case 2 :
					
					System.out.println("ELIMINAR CRÍTICA");
					
					System.out.println("Introduzca el titulo de la crítica");
					titulo = read.nextLine();
					
					receptor = CManager.borrarCritica(username.toLowerCase(), titulo);
					if(receptor == true) {
						System.out.println("La critica ha sido borrada con exito");
					}
					else {
						System.out.println("La critica no ha podido ser borrada");
					}
					break;
				
				case 3 :
					ArrayList<String> critica = new ArrayList<String>();
					System.out.println("VER CRÍTICA");
					critica = CManager.verCriticas();
					if(critica.size() == 0) {
						System.out.println("No hay criticas en la base");
					}
					else {
						for(int i = 0; i < critica.size(); i++) {
							System.out.println(critica.get(i) + "");
						}
					}
					break;
					
				case 4 :
					
					System.out.println("VALORAR CRÍTICA");
					
					System.out.println("Introduzca el titulo de la crítica");
					titulo = read.nextLine();
					
					System.out.println("Introduzca la puntuacion del espectaculo");
					int valoracion = read.nextInt();
					read.nextLine();
					
					receptor = CManager.votarCritica(titulo, username.toLowerCase(), valoracion);
					if(receptor == true) {
						System.out.println("La critica ha sido votada con exito");
					}
					else {
						System.out.println("La critica no ha podido ser votada.No puede votar su propia critica");
					}
					
					break;
				
				case 5 :
					ArrayList<Critica> buscador = new ArrayList<Critica>();
					System.out.println("BUSCAR CRÍTICA");
					
					System.out.println("Introduzca el nombre del autor de la crítica");
					
					String nickcreador = read.nextLine();
					buscador = CManager.buscarCriticas(nickcreador);
					for(int i = 0; i < buscador.size(); i++) {
						System.out.println(buscador.get(i) + "");
					}
					break;
				case 6:
					String datos;
					datos = CManager.verDatos(username);
					System.out.println(datos + "");
					break;
				case 7:
					String newNick,newPassword,newNombre;
					System.out.println("Introduce los nuevos datos: ");
					System.out.println("Nick: ");
					newNick = read.next();
					read.nextLine();
					System.out.println("Nombre: ");
					newNombre = read.nextLine();
					
					System.out.println("Password: ");
					newPassword = read.next();
					CManager.actualizarDatos(username, newNick, newNombre, newPassword);
					username = newNick;
					break;
					
				case 8 :
					
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