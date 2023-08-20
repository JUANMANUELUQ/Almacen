package co.uniquindio.almacen.model;

import java.time.LocalDate;

/**
 * La clase ProductoPerecedero representa una sub-clase de producto que es perecedero y contiene información adicional
 * como la fecha de vencimiento.
 */
public class ProductoPerecedero extends Producto {
	
	private LocalDate fechaVencimiento;
	
	/**
	 * Constructor por defecto de la clase ProductoPerecedero.
	 * Crea una instancia de ProductoPerecedero sin inicializar ningún atributo.
	 */
	public ProductoPerecedero() {
		// Sin implementación adicional en este constructor.
	}

	/**
	 * Constructor de la clase ProductoPerecedero con parámetros.
	 * Crea una instancia de ProductoPerecedero con la información proporcionada.
	 *
	 * @param codigo            El código del producto perecedero.
	 * @param nombre            El nombre del producto perecedero.
	 * @param descripcion       La descripción del producto perecedero.
	 * @param valorUnitario     El valor unitario del producto perecedero.
	 * @param cantidadExistencia La cantidad en existencia del producto perecedero.
	 * @param fechaVencimiento  La fecha de vencimiento del producto perecedero.
	 */
	public ProductoPerecedero(String codigo, String nombre, String descripcion, float valorUnitario,
			int cantidadExistencia, LocalDate fechaVencimiento) {
		super(codigo, nombre, descripcion, valorUnitario, cantidadExistencia);
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Obtiene la fecha de vencimiento del producto perecedero.
	 *
	 * @return La fecha de vencimiento del producto perecedero.
	 */
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Establece la fecha de vencimiento del producto perecedero.
	 *
	 * @param fechaVencimiento La nueva fecha de vencimiento del producto perecedero.
	 */
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
