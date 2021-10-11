package main;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;

import data.Session;
import managers.ReviewsManager;
import managers.SpectaclesManager;
import managers.UsersManager;

/**
 * The main program.
 * @author Francisco Ruiz Roldán
 * @author Rafael Romero Pérez
 * @author Miguel Ángel Ruiz Fernández
 * */
public class mainProgram {
	
	public static void main(String[] args) {
		
		//Iniciamos el gestor de usuarios
		UsersManager UManager = UsersManager.getInstance();
		
		//Cargamos los usuarios del fichero
		UManager.load();
		
		Scanner read = new Scanner(System.in);
		
		int loged = 0;
		
		String usernameSession = "";
		
		do {
			
			System.out.println("INICIAR SESIÓN / REGISTRARSE.");
			System.out.println("1. Iniciar Sesión.\n2. Registrarse."); 
			
			//try {
				int option = read.nextInt();
			//}
			//catch(InputMismatchException e) {
				//System.out.println("Error. Debe introducir un número.");
			//}
			
			if(option == 1) {
				
				System.out.println("Usuario:");
				String user = read.next();
				
				usernameSession = user;
				
				System.out.println("Contraseña:");
				String password = read.next();
				
				loged = UManager.login(user.toLowerCase(), password);
				
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
					
					avaiableUser = UManager.verifyUser(username.toLowerCase());
					
					if (avaiableUser == 0) {
						
						System.out.println("Este usuario no está disponible.");
					}
					
				} while(avaiableUser == 0);
				
				usernameSession = username;
				
				int avaiableMail = 1;
				
				String mail;
				
				do {
					
					System.out.println("Correo electrónico:");
					mail = read.next();
					
					avaiableMail = UManager.verifyMail(mail.toLowerCase());
					
					if (avaiableMail == 0) {
						
						System.out.println("Este correo electrónico no está disponible.");
					}
					
				} while (avaiableMail == 0);
				
				read.nextLine();
				
				System.out.println("Nombre y apellidos:");
				String name = read.nextLine(); //Solo coge una palabra
				
				System.out.println("Contraseña:");
				String password = read.next();
				
				loged = UManager.register(username.toLowerCase(), mail.toLowerCase(), name, password, 0);
				
				System.out.println("Usuario creado correctamente.\n");
				
			}
			else {
				
				System.out.println("Opción incorrecta.\n");
			}
			
		} while (loged == 0);

		//Iniciamos el gestor de espectáculos
		SpectaclesManager SManager = SpectaclesManager.getInstance();
		//Iniciamos el gestor de críticas
		ReviewsManager RManager = ReviewsManager.getInstance();
		
		//Cargamos espectáculos
		SManager.load();
		//Cargamos críticas
		RManager.load();
		
		//Menú de usuario no administrador
		if(loged == 1) {
			
			int exit = 0;
			
			do {
			
				System.out.println("\nMENU");
				System.out.println("1. Crear crítica.");
				System.out.println("2. Eliminar crítica.");
				System.out.println("3. Ver críticas.");
				System.out.println("4. Valorar crítica.");
				System.out.println("5. Ver entradas vendidas para una sesión de un espectáculo.");
				System.out.println("6. Ver entradas disponibles para una sesión de un espectáculo.");
				System.out.println("7. Buscar espectáculo.");
				System.out.println("8. Ver próximos espectáculos con entradas disponibles.");
				System.out.println("9. Salir.");

				int option = read.nextInt();
				
				
				switch (option) {
				
				case 1 :
					
					System.out.println("\nCREAR CRÍTICA");
					
					System.out.println("Introduzca el identificador del espectáculo:");
					int id = read.nextInt();
					
					if(SManager.verifyId(id)) {
						
						System.out.println("Introduzca la puntuación otrorgada al espectáculo:");
						int score = read.nextInt();
						read.nextLine();
						
						System.out.println("Introduzca el texto de su reseña:");
						String review = read.nextLine();
						
						RManager.create(id, score, review, usernameSession);
						
						System.out.println("Crítica añadida correctamente.");
					}
					else {
						System.out.println("No existe ningún espectáculo con identificador " + id);
					}					
					
					break;
				
				case 2 :
					
					System.out.println("\nELIMINAR CRÍTICA");
										
					if(RManager.getReviewsUser(usernameSession).equals("")) {
						
						System.out.println("No existen críticas.");
					}
					else {
						System.out.println("\nTus críticas:" + RManager.getReviewsUser(usernameSession));
						
						System.out.println("\nIntroduzca el identificador de la crítica que desea borrar:");
						int deleteId = read.nextInt();
						
						int status = RManager.delete(deleteId, usernameSession);
						
						if(status == 1) {
							
							System.out.println("Crítica eliminada correctamente.");
							
						}
						else if(status == 0){
							
							System.out.println("No puede eliminar críticas de otros usuarios.");
						}
						else if(status == -1) {
							
							System.out.println("La crítica con identificador " + deleteId + " no existe.");
						}
						
					}
					
					break;
				
				case 3 :
					
					System.out.println("\nBUSCAR CRÍTICA");
					System.out.println("1. Buscar por identificador.");
					System.out.println("2. Buscar por espectáculo.");
					System.out.println("3. Buscar por usuario.");
					
					int searchReviewOption = read.nextInt();
					
					switch(searchReviewOption) {
					
					case 1 :
						
						System.out.println("\nIntroduzca el identificador de la crítica:");
						int searchById = read.nextInt();
						
						if(RManager.getReviewsId(searchById).equals("")) {
							
							System.out.println("La crítica con identificador " + searchById + " no existe.");
							
						}
						else {
							
							System.out.println(RManager.getReviewsId(searchById));
						}
						
						break;
						
					case 2 :
						
						System.out.println("Introduzca el identificador del espectáculo:");
						int searchBySpectacle = read.nextInt();
						
						if(RManager.getReviewsSpectacle(searchBySpectacle).equals("")) {
							
							System.out.println("No existen críticas para el espectáculo con identificador " + searchBySpectacle);
						}
						else {
							
							System.out.println(RManager.getReviewsSpectacle(searchBySpectacle));
						}
						
						break;
						
					case 3 :
						
						System.out.println("Introduzca el usuario:");
						String searchByUser = read.next();
						
						if(RManager.getReviewsUser(searchByUser).equals("")) {
							
							System.out.println("No existen críticas para el usuario: " + searchByUser);
						}
						else {
							
							System.out.println(RManager.getReviewsUser(searchByUser));
						}
						
						break;
					
					}
					
					break;
					
				case 4 :
					
					System.out.println("\nVALORAR CRÍTICA");
					
					System.out.println("Introduzca el id de la crítica que desea valorar:");
					int valuateId = read.nextInt();
					
					System.out.println("Introduzca la valoración que desea otorgarle (0 - 10)");
					int valuation = read.nextInt();
					
					if(valuation<0 || valuation>10) {
						
						System.out.println("Error. Introduzca un valor en el rango 0 - 10");
					}
					else {
						
						int status = RManager.valuate(valuateId, usernameSession, valuation);
						if( status == 1) {
							
							System.out.println("Valoración añadida correctamente.");
							
						}
						else if(status == 0){
							
							System.out.println("No es posible valorar críticas propias.");
							
						}
						else if(status == -1) {
							
							System.out.println("La crítica con identificador " + valuateId + " no existe.");
						}
					}
					
					break;
					
				case 5 :
					
					System.out.println("VER ENTRADAS VENDIDAS PARA UNA SESIÓN DE UN ESPECTÁCULO");
					
					System.out.println("Introduzca el identificador del espectáculo:");
					int idV = read.nextInt();
					
					System.out.println(SManager.printSessions(idV));
					
					read.nextLine();
					System.out.println("Introduzca la fecha con formato dd/mm/yyyy HH:mm");
			        String dateS = read.nextLine();
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date date = null;
					
					try {
						date = df.parse(dateS);
					} catch (Exception e) { System.out.println("Formato de fecha inválido");}
					
					if(SManager.soldTickets(idV, date) == -1) {
						
						System.out.println("No se ha encontrado ninguna sesión con la fecha especificada");
						
					}
					else {
						
						 System.out.println("Entradas vendidas: " + SManager.soldTickets(idV, date));
						
					}      	
					
					break;
					
				case 6 :
					
					System.out.println("\nVER ENTRADAS DISPONIBLES PARA UNA SESION DE UN ESPECTÁCULO");
					
					System.out.println("Introduzca el identificador del espectáculo:");
					int idA = read.nextInt();
					
					System.out.println(SManager.printSessions(idA));
					
					read.nextLine();
					System.out.println("Introduzca la fecha para la cual desea consultar las entradas (dd/MM/yyyy HH:mm):");
					String dateSA = read.nextLine();
					SimpleDateFormat dfA = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date dateA = null;
					
					try {
						dateA = dfA.parse(dateSA);
					} catch (Exception e) { System.out.println("Formato de fecha inválido");}
					
					if(SManager.soldTickets(idA, dateA) == -1) {
						
						System.out.println("No se ha encontrado ninguna sesión con la fecha especificada");
						
					}
					else {
						
						System.out.println("Entradas disponibles: " + SManager.avaiableTickets(idA, dateA));
						
					}      	
										
					break;
				
				case 7 : 
					
					read.nextLine();
					System.out.println("\nBUSCAR ESPECTÁCULO");
					System.out.println("Introduzca el título o categoría:");
					String search = read.nextLine();
					
					String result = SManager.search(search.toLowerCase());
					
					if(result == "") {
						
						System.out.println("No se ha encontrado ningún espectáculo con ese título o categoría.");
					}
					else {
						System.out.println(result);
					}
					
					break;
					
				case 8 :
					
					System.out.println("\nPRÓXIMOS ESPECTÁCULOS DISPONIBLES");
					
					if(SManager.nextAvaiableSessions() == "") {
						
						System.out.println("No existen espectáculos disponibles.");
					}
					else {
						
						System.out.println(SManager.nextAvaiableSessions());
					}
					
					break;
					
				case 9 :
					
					exit = 1;
					
					break;
				
				default :
					
					System.out.println("Opción incorrecta.");
					
					break;
					
				}
				
				
			} while(exit == 0);
			
		}
		//Menú de usuario administrador
		else if(loged == 2) {
			
			int exit = 0;
			
			do {
			
				System.out.println("\nADMIN MENU");
				System.out.println("1. Añadir espectáculo.");
				System.out.println("2. Cancelar espectáculo.");
				System.out.println("3. Actualizar espectáculo.");
				System.out.println("4. Buscar espectáculo.");
				System.out.println("5. Ver todos los espectáculos.");
				System.out.println("6. Hacer admin a un usuario.");
				System.out.println("7. Salir.");
				int option = read.nextInt();
				
				switch (option) {
				
				case 1 :
					
					System.out.println("\nCREAR ESPECTÁCULO");
					System.out.println("Introduzca los datos del espectáculo.");
					
					read.nextLine();
					System.out.println("Título:");
					String title = read.nextLine();
					
					System.out.println("Descripción:");
					String description = read.nextLine();
					
					System.out.println("Categoría:");
					String category = read.nextLine();
					
					System.out.println("Aforo:");
					int capacity = read.nextInt();
					
					System.out.println("Tipo:");
					System.out.println("1 - Puntual\n2 - Múltiple\n3 - Temporada");
					
					int type;
					
					//do {
						
						type = read.nextInt();
						
						if (type==1) {
							
							read.nextLine();
							System.out.println("Introduzca la fecha del espectáculo (dd/MM/yy HH:mm):");
							String sdate = read.nextLine();
							SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
							Date date = null;
							
							try {
								date = df.parse(sdate);
							} catch (Exception e) { System.out.println("Formato de fecha inválido");}
							
							
							System.out.println("Introduzca el número de entradas vendidas:");
							int sold = read.nextInt();
							
							Session s = new Session(date, sold);
							
							SManager.create(title, description, category, capacity, s);
							
							System.out.println("Espectáculo añadido correctamente.");

						}
						else if (type==2) {
							
							System.out.println("Introduzca el número de sesiones que desea añadir.");
							
							int sessions = read.nextInt();
							
							ArrayList<Session> sessionsArray = new ArrayList<Session>(); 
							
							for(int i=1; i<=sessions; i++) {
								
								read.nextLine();
								System.out.println("\nIntroduzca la fecha de la sesión " + i + " (dd/MM/yy HH:mm):");
								String sdate = read.nextLine();
								SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
								Date date = null;
								
								try {
									date = df.parse(sdate);
								} catch (Exception e) { System.out.println("Formato de fecha inválido");}
								
								System.out.println("Introduzca las entradas vendidas para la sesión " + i + ":");
								int sold = read.nextInt();

								Session s = new Session(date, sold);
								
								sessionsArray.add(s);
								
							}
							
							SManager.create(title, description, category, capacity, sessionsArray);
							
							System.out.println("Espectáculo añadido correctamente.");
							
						}
						else if (type==3) {
							
							System.out.println("Introduzca la fecha de inicio (dd/MM/yyyy).");
							String sdate = read.next();
							SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							Date start = null;
							
							try {
								start = df.parse(sdate);
							} catch (Exception e) { System.out.println("Formato de fecha inválido");}
							
							System.out.println("Introduzca la fecha de finalización (dd/MM/yyyy).");
							sdate = read.next();
							Date end = null;
							
							try {
								end = df.parse(sdate);
							} catch (Exception e) { System.out.println("Formato de fecha inválido");}
							
							System.out.println("Introduzca el día de la semana en el que tendrá lugar el espectáculo:");
							System.out.println("1. Lunes");
							System.out.println("2. Martes");
							System.out.println("3. Miércoles");
							System.out.println("4. Jueves");
							System.out.println("5. Viernes");
							System.out.println("6. Sábado");
							System.out.println("7. Domingo");
							int day = read.nextInt();
							
							System.out.println("Introduzca la hora de representación:");
							String time = read.next();
							
							SManager.create(title, description, category, capacity, start, end, time, day);
							
							System.out.println("Espectáculo añadido correctamente.");
							
						}
						else {
							System.out.println("Tipo inválido. Vuelva a introducirlo:");
						}
						
					//} while ((type != 1) || (type != 2) || (type != 3));
				
					break;
				
				case 2 :
					
					System.out.println("\nCANCELAR ESPECTÁCULO");
					System.out.println("Introduzca el identificador del espectáculo que desea cancelar:");
					int deleteId = read.nextInt();
					
					//Imprimir espectaculo y sesiones
					System.out.println(SManager.printInfo(deleteId));
					
					System.out.println("\n1. Cancelar espectáculo completo (todas las sesiones).");
					System.out.println("2. Cancelar las sesiones seleccionadas.");
					int num = read.nextInt();
					
					if(num == 1) {
					
						SManager.deleteAll(deleteId);
					}
					else if(num == 2) {
						
						System.out.println("Introduzca las sesiones que desea cancelar. Por ejemplo: 1 2 5");
						read.nextLine();
						String sessions = read.nextLine();
						String[] sessionsArray = sessions.split(" ");
						
						SManager.deleteSessions(deleteId, sessionsArray);
						
						System.out.println("Sesiones eliminadas correctamente");
					}
					else {
						
						System.out.println("Opción inválida.");
					}
					
					
					break;
				
				case 3 :
					
					System.out.println("\nACTUALIZAR ESPECTÁCULO");
					System.out.println("Introduzca el identificador del espectáculo que desea actualizar:");
					int id = read.nextInt();
					
					System.out.println(SManager.printInfo(id));
					
					if(SManager.printInfo(id).equals("")) {
						
						System.out.println("No existe ningún espectáculo con identificador " + id);
						
					}
					else {
					
						System.out.println("\nIntroduzca el campo que desea actualizar:");
						System.out.println("1. Título");
						System.out.println("2. Descripción");
						System.out.println("3. Categoría");
						System.out.println("4. Aforo");
						System.out.println("5. Sesión");
						System.out.println("6. Añadir sesión");
						int update = read.nextInt();
						
						switch(update) {
						
						case 1 :
							
							System.out.println("\nIntroduzca el nuevo título:");
							String updateTitle = read.next();
							SManager.setTitle(id, updateTitle);
							System.out.println("Título actualizado correctamente.");

							
							break;
							
						case 2 :
							
							System.out.println("\nIntroduzca la nueva descripción:");
							String updateDescription = read.next();
							SManager.setDescription(id, updateDescription);
							System.out.println("Descripción actualizada correctamente.");

							
							break;
							
						case 3 :
							
							System.out.println("\nIntroduzca la nueva categoría:");
							String updateCategory = read.next();
							SManager.setCategory(id, updateCategory);
							System.out.println("Categoría actualizada correctamente.");

							
							break;
							
						case 4 :
							
							System.out.println("\nIntroduzca el nuevo aforo:");
							int updateCapacity = read.nextInt();
							SManager.setCapacity(id, updateCapacity);
							System.out.println("Aforo actualizado correctamente.");
							
							break;
						
						case 5 :
							
							System.out.println("\nIntroduzca el número de sesión que desea actualizar:");
							int sessionNum = read.nextInt();
							
							System.out.println("\nIntroduzca el campo que desea actualizar:");
							System.out.println("1. Fecha");
							System.out.println("2. Entradas vendidas");
							int sessionOption = read.nextInt();
							
							switch(sessionOption) {
							
							case 1 :
								
								read.nextLine();
								System.out.println("\nIntroduzca la nueva fecha (dd/MM/yy HH:mm):");
								String newDateS = read.nextLine();
								SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
								Date newDate = null;
								try {
									newDate = df.parse(newDateS);
								} catch (Exception e) { System.out.println("Formato de fecha inválido");}
								
								SManager.setDate(id, sessionNum-1, newDate);
								
								break;
								
							case 2 :
								
								System.out.println("\nIntroduzca el número de entradas vendidas:");
								int newSold = read.nextInt();
								
								SManager.setSold(id, sessionNum-1, newSold);
								
								break;

							}
							
							break;
						
						case 6 :
							
							read.nextLine();
							System.out.println("\nIntroduzca la nueva fecha (dd/MM/yy HH:mm):");
							String newSessionDateS = read.nextLine();
							SimpleDateFormat dfS = new SimpleDateFormat("dd/MM/yyyy HH:mm");
							Date newSessionDate = null;
							try {
								newSessionDate = dfS.parse(newSessionDateS);
							} catch (Exception e) { System.out.println("Formato de fecha inválido");}
							
							System.out.println("Introduzca el número de entradas vendidas:");
							int newSessionSold = read.nextInt();
							
							Session s = new Session(newSessionDate, newSessionSold);
							
							SManager.addSession(id, s);
							
							break;
						
						}
						
					}
					
					break;
					
				case 4 :
					
					read.nextLine();
					System.out.println("\nBUSCAR ESPECTÁCULO");
					System.out.println("Introduzca el título o categoría:");
					String search = read.nextLine();
					
					String result = SManager.search(search.toLowerCase());
					
					if(result == "") {
						
						System.out.println("No se ha encontrado ningún espectáculo con ese título o categoría.");
					}
					else {
						System.out.println("\nResultado de la búsqueda:\n" + result);
					}
					
					break;
				
				case 5 :
					
					System.out.println("\nESPECTÁCULOS");
					
					String all = SManager.allSpectacles();
					
					if(all.equals("")) {
						System.out.println("Aún no hay espectáculos.");
					}
					else {
						System.out.println(all);
					}
					
					break;
					
				case 6 :
					
					System.out.println("\nIntroduzca el nombre de usuario que quiera convertir en admin:");
					String newAdmin = read.next();
					
					if(UManager.newAdmin(newAdmin.toLowerCase()) == 1) {
						
						System.out.println("Nuevo administrador añadido.");
					}
					else {
						System.out.println("No existe el usuario " + newAdmin);
					}
					
					break;
				
				case 7 :
					
					exit = 1;
					
					break;
					
				default :
					
					System.out.println("Opción incorrecta.");
					
					break;
				}
				
			} while (exit == 0);
			
			
		}
	
	read.close();	

	}	
	
}
