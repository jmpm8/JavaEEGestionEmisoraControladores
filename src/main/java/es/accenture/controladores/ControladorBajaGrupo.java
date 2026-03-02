package es.accenture.controladores;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import es.accenture.emisora.Grupo;
import es.accenture.interfaz.IControlador;
import es.accenture.modelo.ModeloGrupo;

/**
 * ControladorBajaGrupo:
 * Controlador que gestiona la eliminación de un grupo musical de la BBDD.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ControladorBajaGrupo implements IControlador{

	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Crear objeto de tipo 'ModeloGrupo'
	private ModeloGrupo modelogrupo;
	
	//Constructor con parametros.
    public ControladorBajaGrupo(DataSource poolConexiones) { 
    	this.poolConexiones = poolConexiones; 
    	this.modelogrupo = new ModeloGrupo(poolConexiones);
    }

    /**
     * Este metodo elimina el grupo con el id recibido del request.
     * Despues muestra la lista de grupos actualizada.
     * @return 'GruposMusicales.jsp' devuelve el JSP a mostrar.
     */
    @Override
    public String procesarPeticion(HttpServletRequest request, HttpServletResponse response) {
    	
        try {
        	
        	//Recuperar el id del grupo que se desea eliminar
            int id = Integer.parseInt(request.getParameter("idGrupo"));

            //Eliminar el grupo de la BBDD usando el Modelo
            modelogrupo.eliminarGrupo(id);

            //Recuperar la lista de grupos actualizada
            List<Grupo> lista = modelogrupo.getGrupos();

            //Guardar la lista de grupos en la request para que el JSP la use.
            request.setAttribute("gruposMusicales", lista);
            
        } catch (Exception e) {
        	
        	//Guardar mensaje de la excepción para mostrarlo en la vista o consola
            request.setAttribute("mensajeError", "Error al eliminar: " + e.getMessage());
            
            //Imprimir pila de llamadas, para que en el caso de que haya un error, nos dice donde.
            e.printStackTrace();
        }
        
        //Devolver 'vista' GruposMusicales.jsp
        return "GruposMusicales.jsp";
    }
	
}
