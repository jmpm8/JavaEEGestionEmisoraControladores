package es.accenture.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import es.accenture.emisora.Grupo;
import es.accenture.interfaz.IControlador;
import es.accenture.modelo.ModeloGrupo;

/**
 * ControladorAltaGrupo:
 * Controlador que gestiona la inserción de un nuevo grupo musical en la BBDD.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ControladorAltaGrupo implements IControlador{
	
	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Crear objeto de tipo 'ModeloGrupo'
	private ModeloGrupo modelogrupo;
	
	//Constructor con parametros.
    public ControladorAltaGrupo(DataSource poolConexiones) { 
    	this.poolConexiones = poolConexiones;
    	this.modelogrupo = new ModeloGrupo(poolConexiones);
    }

    /**
     * Este metodo obtiene los datos del formulario
     * crea el objeto 'Grupo' y lo inserta en la BBDD.
     * @return 'GruposMusicales.jsp' vista .jsp que muestra.
     */
    @Override
    public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
    	
        try {
        	
        	//Recuperar los datos modificados del grupo de la request
	        String nombre = request.getParameter("nombre");
	        int creacion = Integer.parseInt(request.getParameter("creacion"));
	        String origen = request.getParameter("origen");
	        String genero = request.getParameter("genero");
            
	        //Crear el objeto con la información del formulario.
            Grupo grupoNuevo = new Grupo(0, nombre, creacion, origen, genero);
            
            //Insertar el nuevo grupo en la BBDD con el objeto 'modelogrupo'
            modelogrupo.insertarGrupo(grupoNuevo);
            
            //Guardar la lista de los grupos recuperados de la BBDD en la 'lista'.
            List<Grupo> lista = modelogrupo.getGrupos();
            
            ////Guardar la 'lista' en el request para que el JSP la use.
            request.setAttribute("gruposMusicales", lista);
            
        } catch (Exception e) {
        	
        	//Guardar mensaje de la excepción para mostrarlo en la vista o consola
            request.setAttribute("mensajeError", "Error al actualizar: " + e.getMessage());
            
            //Imprimir pila de llamadas, para que en el caso de que haya un error, nos dice donde.
            e.printStackTrace();
        }
        
      //Devolver 'vista' GruposMusicales.jsp
      return "GruposMusicales.jsp";
    }
}

