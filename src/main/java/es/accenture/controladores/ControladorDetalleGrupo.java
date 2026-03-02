package es.accenture.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import es.accenture.emisora.Grupo;
import es.accenture.interfaz.IControlador;
import es.accenture.modelo.ModeloGrupo;

/**
 * ControladorDetalleGrupo:
 * Controlador que recupera y muestra el detalle de un grupo musical específico.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ControladorDetalleGrupo implements IControlador{

	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Crear objeto de tipo 'ModeloGrupo'
	private ModeloGrupo modelogrupo;
	
	//Constructor con parametros.
	public ControladorDetalleGrupo(DataSource poolConexiones) { 
		this.poolConexiones = poolConexiones; 
		this.modelogrupo = new ModeloGrupo(poolConexiones);
	}

	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			
			//Recuperar el id del grupo que se desea mostrar.
			int id = Integer.parseInt(request.getParameter("idGrupo"));
	        
			//Recuperamos el grupo con el id indicado y guardar en objeto de tipo 'Grupo'.
	        Grupo grupo = modelogrupo.getGrupo(id);
	        
	        //Guardar el grupo en la request para que el JSP la use.
	        request.setAttribute("detalleGrupo", grupo);
	        
		} catch (Exception e) {
			
			//Guardar mensaje de la excepción para mostrarlo en la vista o consola
	        request.setAttribute("mensajeError", "Error al mostrar los detalles del grupo: " + e.getMessage());
	        
	        //Imprimir pila de llamadas, para que en el caso de que haya un error, nos dice donde.
            e.printStackTrace();
	    }
	    
		//Devolver 'vista' DetalleGrupo.jsp
		return "DetalleGrupo.jsp";
	}
}

