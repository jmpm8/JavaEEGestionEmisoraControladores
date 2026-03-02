package es.accenture.servlet;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import es.accenture.controladores.ControladorActualizarGrupo;
import es.accenture.controladores.ControladorAltaGrupo;
import es.accenture.controladores.ControladorBajaGrupo;
import es.accenture.controladores.ControladorDetalleGrupo;
import es.accenture.controladores.ControladorModificarGrupo;
import es.accenture.controladores.ControladorObtenerGrupos;
import es.accenture.interfaz.IControlador;

/**
 * Servlet implementation class ServletEmisora
 * Servlet recibe todas las peticiones y decide que controlador usar.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
@WebServlet("/ServletEmisora")
public class ServletEmisora extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	//Establecer el DataSource.
	@Resource(name="jdbc/Emisora")
	private DataSource miPoolConexiones;//Declarar variable de tipo DataSource para guardar el pool de conexiones.
	
	/**
	 * Gestiona las peticiones get.
	 * Lee los parametros y selecciona el controlador correspondiente.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Declarar variable para almacenar lo que llega desde el formulario (request).
		String accion = request.getParameter("accion");
				
		//Si es nula (primera vez), es decir, si no se envia el parametro, cargar grupos.
		if(accion == null) accion ="cargar";
		
		//Declarar variable usando la interfaz. 
		IControlador controlador = null;
        
		//Declarar variable para guardar la vista.
		String vista = "GruposMusicales.jsp";
		
		try {
			
			//Segun la accion recibida ejecutar al controlador correspondiente.
			switch(accion) {
				case "cargar":
					controlador = new ControladorObtenerGrupos(miPoolConexiones);
					break;
				case "detalle":
					controlador = new ControladorDetalleGrupo(miPoolConexiones);
					break;
				case "alta":
					controlador = new ControladorAltaGrupo(miPoolConexiones);
					break;
				case "modificar":
					controlador = new ControladorModificarGrupo(miPoolConexiones);
					break;
				case "actualizar":
					controlador = new ControladorActualizarGrupo(miPoolConexiones);
					break;
				case "baja":
					controlador = new ControladorBajaGrupo(miPoolConexiones);
					break;
				default:
					controlador = new ControladorObtenerGrupos(miPoolConexiones);
			}
			
		}catch(Exception e) {
			
			//Guardar el mensaje de la excepcion en el objeto request.
			request.setAttribute("error", e.getMessage());
			
			//Imprimimos la pila de llamadas, para que en el caso de que haya un error, nos dice donde.
			e.printStackTrace();
			
			//Si hay un error, forzamos que la vista sea la página principal para que el dispatcher no intente ir a "" (nada).
			if (vista.isEmpty()) {
				
		        //Almacenar en 'vista' el archivo 'GruposMusicales.jsp'
				vista = "GruposMusicales.jsp"; 
		    }
		}
		
		//Comprobar que el controlador no sea null.
		if(controlador != null) {
			//Ejecutar y obtener el nombre de la vista.
			vista = controlador.procesarPeticion(request, response);
		}
		
		//Crear variable 'dispatcher' de tipo RequestDispatcher para almacenar la peticion de la vista.
        RequestDispatcher dispatcher = request.getRequestDispatcher(vista);
        
      //Enviar la peticion al .jsp 
        dispatcher.forward(request, response);
	}
	
	/**
	 * Redirige las peticiones post a doGet().
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		doGet(request, response);
    }
}
