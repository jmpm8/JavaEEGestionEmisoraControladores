package es.accenture.emisora;

/**
 * Esta clase 'Componente' va a crear los componentes de cada grupo.
 * 
 * @author jorge martin perez moreno
 * @version 1.0
 */
public class Componente {

	//Declarar los atributos de la clase.
	private int id;
	private String nombre;
	private String instrumento;
		
	//Constructor vacio.
	public Componente() {
			
	}

	//Constructor con parametros.
	public Componente(int id, String nombre, String instrumento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.instrumento = instrumento;
	}
	
	//Constructor con parametros.
	public Componente(String nombre, String instrumento) {
		super();
		this.nombre = nombre;
		this.instrumento = instrumento;
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

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}
	
	
}
