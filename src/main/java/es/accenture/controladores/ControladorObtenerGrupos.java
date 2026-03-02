package es.accenture.controladores;

import java.util.List;
import javax.servlet.http.*;
import javax.sql.DataSource;
import es.accenture.emisora.Grupo;
import es.accenture.interfaz.IControlador;
import es.accenture.modelo.ModeloGrupo;

/**
 * ControladorObtenerGrupos: 
 * Controlador que recupera y muestra la lista de todos los grupos musicales.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ControladorObtenerGrupos implements IControlador{
	
	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;

	//Crear objeto de tipo 'ModeloGrupo'
	private ModeloGrupo modelogrupo;
	
	//Constructor con parametros. Recibe el pool de conexiones como parametro.
    public ControladorObtenerGrupos(DataSource poolConexiones) { 
    	this.poolConexiones = poolConexiones; 
    	this.modelogrupo = new ModeloGrupo(poolConexiones);
    }

    /**
     * Este metodo obtiene la lista de todos los grupos de la BBDD.
     * @return  'GruposMusicales.jsp' vista .jsp que muestra.
     */
    @Override
    public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
    	
        try {
        	//Guardar la lista de los grupos recuperados de la BBDD en la 'lista'.
            List<Grupo> lista = modelogrupo.getGrupos();
            
            //Guardar la 'lista' en el request para que el JSP la use.
            request.setAttribute("gruposMusicales", lista);
            
        } catch (Exception e) {
        	
        	//Guardar mensaje de la excepción para mostrarlo en la vista o consola
            request.setAttribute("mensajeError", "Error al obtener lista: " + e.getMessage());
            
            //Imprimir pila de llamadas, para que en el caso de que haya un error, nos dice donde.
            e.printStackTrace();
        }
        
        //Devolver 'vista' GruposMusicales.jsp
        return "GruposMusicales.jsp";
    }
	
	
}
