package co.uniquindio.almacen.model;

import java.time.LocalDate;

/**
 * La clase ProductoEnvasado representa un tipo de producto que está envasado y contiene información adicional
 * como la fecha de envasado, el peso del envase y el país de origen.
 */
public class ProductoEnvasado extends Producto {
	
	private LocalDate fechaEnvasado;
	private double pesoEnvase;
	private PaisOrigen paisOrigen;
	
	/**
	 * Constructor por defecto de la clase ProductoEnvasado.
	 * Crea una instancia de ProductoEnvasado sin inicializar ningún atributo.
	 */
	public ProductoEnvasado() {
		// Sin implementación adicional en este constructor.
	}

	/**
	 * Constructor de la clase ProductoEnvasado con parámetros.
	 * Crea una instancia de ProductoEnvasado con la información proporcionada.
	 *
	 * @param codigo            El código del producto envasado.
	 * @param nombre            El nombre del producto envasado.
	 * @param descripcion       La descripción del producto envasado.
	 * @param valorUnitario     El valor unitario del producto envasado.
	 * @param cantidadExistencia La cantidad en existencia del producto envasado.
	 * @param fechaEnvasado     La fecha de envasado del producto envasado.
	 * @param pesoEnvase        El peso del envase del producto envasado.
	 * @param paisOrigen        El país de origen del producto envasado.
	 */
	public ProductoEnvasado(String codigo, String nombre, String descripcion, float valorUnitario,
			int cantidadExistencia, LocalDate fechaEnvasado, double pesoEnvase, PaisOrigen paisOrigen) {
		super(codigo, nombre, descripcion, valorUnitario, cantidadExistencia);
		this.fechaEnvasado = fechaEnvasado;
		this.pesoEnvase = pesoEnvase;
		this.paisOrigen = paisOrigen;
	}

	/**
	 * Obtiene la fecha de envasado del producto envasado.
	 *
	 * @return La fecha de envasado del producto envasado.
	 */
	public LocalDate getFechaEnvasado() {
		return fechaEnvasado;
	}

	/**
	 * Establece la fecha de envasado del producto envasado.
	 *
	 * @param fechaEnvasado La nueva fecha de envasado del producto envasado.
	 */
	public void setFechaEnvasado(LocalDate fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}

	/**
	 * Obtiene el peso del envase del producto envasado.
	 *
	 * @return El peso del envase del producto envasado.
	 */
	public double getPesoEnvase() {
		return pesoEnvase;
	}

	/**
	 * Establece el peso del envase del producto envasado.
	 *
	 * @param pesoEnvase El nuevo peso del envase del producto envasado.
	 */
	public void setPesoEnvase(double pesoEnvase) {
		this.pesoEnvase = pesoEnvase;
	}

	/**
	 * Obtiene el país de origen del producto envasado.
	 *
	 * @return El país de origen del producto envasado.
	 */
	public PaisOrigen getPaisOrigen() {
		return paisOrigen;
	}

	/**
	 * Establece el país de origen del producto envasado.
	 *
	 * @param paisOrigen El nuevo país de origen del producto envasado.
	 */
	public void setPaisOrigen(PaisOrigen paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	/**
	 * Obtiene el país de origen del producto envasado en formato de cadena de texto.
	 *
	 * @return El país de origen del producto envasado como cadena de texto.
	 */
	public String obtenerPaisOrigenCadena() {
		String paisOrigenCadena = "";
		switch (this.paisOrigen) {
			case COLOMBIA: paisOrigenCadena = "Colombia"; break;
			case ARGENTINA: paisOrigenCadena = "Argentina"; break;
			case CHILE: paisOrigenCadena = "Chile"; break;
			case ECUADOR: paisOrigenCadena = "Ecuador"; break;
			case PERU: paisOrigenCadena = "Perú"; break;
		}
		return paisOrigenCadena;
	}
}

