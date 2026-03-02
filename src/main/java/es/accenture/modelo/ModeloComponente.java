package es.accenture.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import es.accenture.emisora.Componente;

/**
 * Esta clase se va a encargar de conectar con la BBDD y
 * de realizar las operaciones que hagamos contra la tabla 'componentes' de la BBDD 'musicadb2'
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ModeloComponente {

	//Declaramos variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;

	//Creamos constructor con parametros, que recibirá como parametro el pool de conexiones.
	public ModeloComponente(DataSource poolConexiones) {
			
		this.poolConexiones = poolConexiones;
	}
		
	/**
	* Este metodo nos devuelve un listado de todos los grupos musicales que tenemos en la BBDD
	* Este metodo ademas lanza una excepcion
	* @return 'listaComponentes', la lista obtenida de la BBDD
	* @throws Exception
	*/
	public List<Componente> getComponentes(int idGrupo) throws Exception{
			
		//Crear una lista de tipo 'Componente'.
		List<Componente> listaComponentes = new ArrayList<>();
			
		//Crear sentencia SQL para obtener los registros.
		String instruccionSQL = "SELECT nombre, instrumento FROM componentes WHERE grupoId=?";
		
		//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
		try (
				
			//Crear un objeto de tipo Connection. Establecer conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
					
			//Crear un objeto de tipo PreparedStatement.
			PreparedStatement miStatement = miConexion.prepareStatement(instruccionSQL)){
			
			//Pasar parametros a la consulta SQL.
			miStatement.setInt(1, idGrupo);
		
			try (
					
				//Crear un objeto de tipo ResultSet. Ejecutar la sentencia SQL y la guardamos en el objeto ResultSet.
				ResultSet resultado = miStatement.executeQuery()){
				
				//Recorrer el ResultSet obtenido.
				while(resultado.next()) {
				
					//Crear objeto de tipo 'Grupo' para guardar el ResultSet.
					Componente componente = new Componente(resultado.getString("nombre"), 
														   resultado.getString("instrumento"));
				
					//Añadir el ResultSet a la 'listaGrupos'.
					listaComponentes.add(componente);
				}
			}
				
			//Lanzar excepcion si no hay resultados en la consulta SQL.
			if(listaComponentes.isEmpty()) {
					
				throw new Exception("No se han obtenido resultados en la consulta de componentes.");
			}
			
		}catch(SQLException e) {
			
			//Lanzar excepcion para que el Servlet la capture.	
			throw new Exception("Error al leer los componentes del grupo " + idGrupo + e.getMessage());		
		}
		
		//Devolver la lista obtenida de la BBDD.
		return listaComponentes;
	}
	
}
