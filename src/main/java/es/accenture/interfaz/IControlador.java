package es.accenture.interfaz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Esta interfaz que deben implementar todos los controladores.
 * Todos los controladores deben tener un metodo procesarPeticion().
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public interface IControlador {

	//Metodo que todos los controladores deben implementar obligatoriamente.
	String procesarPeticion(HttpServletRequest request, HttpServletResponse response);
}
