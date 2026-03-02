package es.accenture.controladores;

import javax.servlet.http.*;
import javax.sql.DataSource;
import es.accenture.interfaz.IControlador;
import es.accenture.modelo.ModeloGrupo;

/**
 * ControladorModificarGrupo:
 * Controlador que carga el formulario de modificar con los datos actuales del grupo.
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ControladorModificarGrupo implements IControlador{

	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Crear objeto de tipo 'ModeloGrupo'
	private ModeloGrupo modelogrupo;
	
	//Constructor con parametros.
    public ControladorModificarGrupo(DataSource poolConexiones) { 
    	this.poolConexiones = poolConexiones; 
    	this.modelogrupo = new ModeloGrupo(poolConexiones);
    }

    /**
     * Este metodo carga los datos actuales en el grupo y los guarda en el request
     * @return 'ActualizarGrupo.jsp' vista .jsp que muestra.
     */
    @Override
    public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
       
    	try {
    		
    		//Recuperar los datos id del grupo que se desea modificar.
            int id = Integer.parseInt(request.getParameter("idGrupo"));
           
            //Guardar el grupo en la request para que el JSP la use.
            request.setAttribute("grupoModificar", modelogrupo.getGrupo(id));
            
        } catch (Exception e) {
        	
        	//Guardar mensaje de la excepción para mostrarlo en la vista o consola
            request.setAttribute("mensajeError", "Error al cargar modificación: " + e.getMessage());
            
            //Imprimir pila de llamadas, para que en el caso de que haya un error, nos dice donde.
            e.printStackTrace();
        }
    	
    	//Devolver 'vista' ActualizarGrupo.jsp
        return "ActualizarGrupo.jsp";
    }
}

