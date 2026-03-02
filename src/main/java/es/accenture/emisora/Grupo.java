package es.accenture.emisora;

import java.util.List;

/**
 * En la clase 'Grupo' va a crear objetos de grupos musicales.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class Grupo {

	//Declarar los atributos de la clase.
	private int id;
	private String nombre;
	private int creacion;
	private String origen;
	private String genero;
	private List<Componente> componentes;
	
	//Constructor vacio.
	public Grupo() {
		
	}

	//Constructor con parametros.
	public Grupo(int id, String nombre, int creacion, String origen, String genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.creacion = creacion;
		this.origen = origen;
		this.genero = genero;
	
	}

	//Metodos getter y setter.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCreacion() {
		return creacion;
	}

	public void setCreacion(int creacion) {
		this.creacion = creacion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}
	
	
}
