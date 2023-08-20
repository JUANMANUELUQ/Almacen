package co.uniquindio.almacen.model;
/**
 * La clase Persona representa a una clase abstracta con información básica como nombre, apellidos e identificación.
 */
public class Persona {
	
	private String nombre;
	private String apellidos;
	private String identificacion;

	/**
	 * Constructor por defecto de la clase Persona.
	 * Crea una instancia de Persona sin inicializar ningún atributo.
	 */
	public Persona() {
		// Sin implementación adicional en este constructor.
	}
	
	/**
	 * Constructor de la clase Persona con parámetros.
	 * Crea una instancia de Persona con la información proporcionada.
	 *
	 * @param nombre        El nombre de la persona.
	 * @param apellidos     Los apellidos de la persona.
	 * @param identificacion    La identificación única de la persona.
	 */
	public Persona(String nombre, String apellidos, String identificacion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.identificacion = identificacion;
	}

	/**
	 * Obtiene el nombre de la persona.
	 *
	 * @return El nombre de la persona.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la persona.
	 *
	 * @param nombre El nuevo nombre de la persona.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene los apellidos de la persona.
	 *
	 * @return Los apellidos de la persona.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece los apellidos de la persona.
	 *
	 * @param apellidos Los nuevos apellidos de la persona.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Obtiene la identificación de la persona.
	 *
	 * @return La identificación de la persona.
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * Establece la identificación de la persona.
	 *
	 * @param identificacion La nueva identificación de la persona.
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
}
