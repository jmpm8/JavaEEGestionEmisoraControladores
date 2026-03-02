package es.accenture.controladores;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ControladorActualizarGrupo implements IControlador{
	
	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Crear objeto de tipo 'ModeloGrupo'
	private ModeloGrupo modelogrupo;
		
	//Constructor con parametros.
	public ControladorActualizarGrupo(DataSource poolConexiones) { 
	    this.poolConexiones = poolConexiones; 
	    this.modelogrupo = new ModeloGrupo(poolConexiones);
	}

	/**
	 * Recoge los datos del formulario, crea objeto 'Grupo' actualizado y 
	 * lo guarda en la BBDD.
	 * @return 'GruposMusicales.jsp' vista .jsp que muestra.
	 */
	@Override
	public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
		
		try {
	        	
	        //Recuperar los datos del id del grupo a actualizar de la request
			int id = Integer.parseInt(request.getParameter("idGrupo"));
			String nombre = request.getParameter("nombre");
	        int creacion = Integer.parseInt(request.getParameter("creacion"));
	        String origen = request.getParameter("origen");
	        String genero = request.getParameter("genero");

	        //Crear el objeto con la información actualizada
	        Grupo grupoActualizado = new Grupo(id, nombre, creacion, origen, genero);

	        //Realizar la modificación en la BBDD usando el Modelo.
	        modelogrupo.actualizarGrupo(grupoActualizado);

	        //Recuperar la lista de grupos actualizada
	        List<Grupo> lista = modelogrupo.getGrupos();

	        //Guardar la lista en la request para que el JSP la use.
	        request.setAttribute("gruposMusicales", lista);
	            
		} catch (Exception e) {
	        	
	        //Guardar mensaje de la excepción para mostrarlo en la vista o consola
	        request.setAttribute("mensajeError", "Error al insertar: " + e.getMessage());
	        
	        //Imprimir pila de llamadas, para que en el caso de que haya un error, nos dice donde.
	        e.printStackTrace();
	    }
	        
	      //Devolver 'vista' GruposMusicales.jsp
	      return "GruposMusicales.jsp";
	}
}
