package es.accenture.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import es.accenture.emisora.Componente;
import es.accenture.emisora.Grupo;

/**
 * Esta clase se encarga de conectar con la BBDD y
 * Esta clase realiza las operaciones que hagamos contra la tabla 'grupos' de la BBDD 'musicadb2'
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class ModeloGrupo {

	//Declarar variable de la clase, para almacenar el pool de conexiones.
	private DataSource poolConexiones;
	
	//Declarar variable de tipo 'ModeloComponente'.
	private ModeloComponente modeloComponente;

	//Constructor con parametros. Recibirá como parametro el pool de conexiones.
	public ModeloGrupo(DataSource poolConexiones) {
		this.poolConexiones = poolConexiones;
		this.modeloComponente = new ModeloComponente(poolConexiones);
	}
	
	//Constructor vacio.
	public ModeloGrupo() {
		
	}
	
	/**
	 * Este metodo devuelve un listado de todos los grupos musicales que tenemos en la BBDD
	 * Este metodo ademas lanza una excepcion
	 * @return 'listaGrupos', lista obtenida de la BBDD
	 * @throws Exception
	 */
	public List<Grupo> getGrupos() throws Exception{
		
		//Crear una lista de tipo 'Grupo'.
		List<Grupo> listaGrupos = new ArrayList<>();
		
		//Crear sentencia SQL para obtener los registros.
		String instruccionSQL = "SELECT grupoId, nombre, creacion, origen, genero FROM grupos";
		
		//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
		try (
			
			//Crear un objeto de tipo Connection. Establecer conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
			
			//Crear un objeto de tipo Statement.
			Statement miStatement = miConexion.createStatement();
			
			//Crear un objeto de tipo ResultSet. Ejecutar la sentencia SQL y la guardamos en el objeto ResultSet.
			ResultSet resultado = miStatement.executeQuery(instruccionSQL)){
			
			//Recorrer el ResultSet obtenido.
			while(resultado.next()) {
				
				//Crear objeto de tipo 'Grupo' para guardar el ResultSet.
				Grupo grupo = new Grupo(resultado.getInt("grupoId"), 
										resultado.getString("nombre"),
										resultado.getInt("creacion"), 
										resultado.getString("origen"), 
										resultado.getString("genero"));
				
				//Aññadir cada objeto del ResultSet a la 'listaGrupos'.
				listaGrupos.add(grupo);
			}
			
			//Lanzar excepcion si no hay resultados en la consulta SQL.
			if(listaGrupos.isEmpty()) {
				
				throw new Exception("No se han obtenido resultados en la consulta de grupos.");
			}
				
		}catch(SQLException e) {
			
			//Lanzar excepcion para que el Servlet la capture.
			throw new Exception("Error al leer grupos: " + e.getMessage());
			
		}
		
		//Devolver la lista obtenida de la BBDD.
		return listaGrupos;
	}
	
	/**
	 * Este metodo nos devuelve informacion sobre un grupo especifico.
	 * Este metodo lanza una ezcepcion.
	 * @param idGrupo, id del grupo que buscamos.
	 * @return 'grupoEncontrado', detalle del grupo encontrado con el parametro. 
	 * @throws Exception
	 */
	public Grupo getGrupo(int idGrupo) throws Exception{
		
		//Crear un objeto de tipo 'Grupo'.
		Grupo grupoEncontrado = null;
		
		//Crear la sentencia SQL.
		String instruccionSql = "SELECT grupoId, nombre, creacion, origen, genero FROM grupos WHERE grupoId=?";
		
		//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
		try (
			
			////Crear un objeto de tipo Connection. Establecer la conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
			
			//Crear objeto de tipo PreparedStatement y le pasamos la instruccion SQL.
			PreparedStatement consulta = miConexion.prepareStatement(instruccionSql)){
			
			//Pasar parametros a la consulta SQL.
			consulta.setInt(1, idGrupo);
			
			//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
			try(
				//Ejecutar la sentencia SQL y la guardamos en el objeto ResultSet.
				ResultSet resultado = consulta.executeQuery()){
				
				//Comprobar el resultado de la consulta.
				if(resultado.next()) {
					
					//Crear objeto tipo 'Grupo'
					grupoEncontrado = new Grupo(
									  resultado.getInt("grupoId"), 
									  resultado.getString("nombre"), 
							          resultado.getInt("creacion"), 
							          resultado.getString("origen"), 
							          resultado.getString("genero"));
					
					//Guardar en 'listaComponentes' el objeto obtenido con los componentes del 'idGrupo' pasado por parametro al metodo.
					List<Componente> listaComponentes = modeloComponente.getComponentes(idGrupo);
					
					//Metemos la lista en el objeto 'grupoEncontrado'.
	                grupoEncontrado.setComponentes(listaComponentes);
				}
			}
				
		}catch(SQLException e) {
			
			throw new Exception("Error al buscar el grupo " + idGrupo, e);
			
		}
		
		//Devolver detalle del grupo obtenido de la BBDD.
		return grupoEncontrado;
		
	}
	
	
	public void insertarGrupo(Grupo nuevoGrupo) throws Exception{
		
		//Crear la sentencia SQL.
		String instruccionSql = "INSERT INTO grupos(nombre, creacion, origen, genero)" +
					 			"VALUES (?,?,?,?)";
		
		try (
			
			////Crear un objeto de tipo Connection. Establecer la conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
		
			//Crear objeto de tipo PreparedStatement y le pasamos la instruccion SQL a la BBDD.
			PreparedStatement miStatement = miConexion.prepareStatement(instruccionSql)){
		
			//Pasar parametros a la instruccion SQL.
			miStatement.setString(1, nuevoGrupo.getNombre());
			miStatement.setInt(2, nuevoGrupo.getCreacion());
			miStatement.setString(3, nuevoGrupo.getOrigen());
			miStatement.setString(4, nuevoGrupo.getGenero());
			
			//Ejecutar instruccion SQL.
			miStatement.executeUpdate();
			
		}catch(SQLException e) {
			
			//Lanzar la excepción para que la capture el Servlet
	        throw new Exception("Error al insertar el grupo: " + e.getMessage());
	
		}  
	}
	  
	/**
	 * Este metodo actualiza los datos de un grupo existente en la BBDD.
	 * @param grupoActualizado, objeto 'Grupo' con los datos actualizados.
	 * @throws Exception
	 */
	public void actualizarGrupo(Grupo grupoActualizado) throws Exception{
	 
		//Crear la sentencia SQL.
		String instruccionSql = "UPDATE grupos SET nombre=?, creacion=?, origen=?, genero=? WHERE grupoId=?";
		
		try (
			
			//Establecer la conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
		    
			//Crear objeto de tipo PreparedStatement y le pasamos la instruccion SQL a la BBDD.	
			PreparedStatement miStatement = miConexion.prepareStatement(instruccionSql)) {
		    
			//Pasar parametros a la instruccion SQL.
		    miStatement.setString(1, grupoActualizado.getNombre());
		    miStatement.setInt(2, grupoActualizado.getCreacion());
		    miStatement.setString(3, grupoActualizado.getOrigen());
		    miStatement.setString(4, grupoActualizado.getGenero());
		    //Necesario para el WHERE
		    miStatement.setInt(5, grupoActualizado.getId()); 
		    
		    //Ejecutar instruccion SQL.
		    miStatement.executeUpdate();

		} catch (SQLException e) {
			
			//Lanzar la excepción para que la capture el Servlet
		    throw new Exception("Error al actualizar el grupo: " + e.getMessage());
		}
	}
	 
	/**
	 * Este metodo elimina un grupo de la BBDD.
	 * @param idEliminar, id del grupo a eliminar.
	 * @throws Exception
	 */
	public void eliminarGrupo(int idEliminar) throws Exception{
	 
		//Crear la sentencia SQL.
		String instruccionSql = "DELETE FROM grupos WHERE grupoId=?";
	 
		//Usar try-with-resources para que Java cierre los recursos automaticamente al cerrar el bloque.
		try (
		    
			//Crear un objeto de tipo Connection. Establecer la conexion con la BBDD usando el pool de conexiones.
			Connection miConexion = poolConexiones.getConnection();
		    
			//Crear objeto de tipo PreparedStatement y le pasamos la instruccion SQL a la BBDD.		
			PreparedStatement miStatement = miConexion.prepareStatement(instruccionSql)){
		    
			//Pasar parametros a la instruccion SQL.
			miStatement.setInt(1, idEliminar);
		    
			//Ejecutar instruccion SQL.
			miStatement.executeUpdate();

		} catch (SQLException e) {
			
			//Lanzar la excepción para que la capture el Servlet
		    throw new Exception("Error al eliminar el grupo con ID " + idEliminar + ": " + e.getMessage());
		}
	}
}
