package co.uniquindio.almacen.model;

/**
 * La clase ProductoRefrigerado representa un tipo de producto que requiere refrigeración y contiene información adicional
 * como el código de aprobación y la temperatura de refrigeración recomendada.
 */
public class ProductoRefrigerado extends Producto {
	
	private String codigoAprobacion;
	private double tempRefrigeracionRecomendada;
	
	/**
	 * Constructor por defecto de la clase ProductoRefrigerado.
	 * Crea una instancia de ProductoRefrigerado sin inicializar ningún atributo.
	 */
	public ProductoRefrigerado() {
		// Sin implementación adicional en este constructor.
	}

	/**
	 * Constructor de la clase ProductoRefrigerado con parámetros.
	 * Crea una instancia de ProductoRefrigerado con la información proporcionada.
	 *
	 * @param codigo            El código del producto refrigerado.
	 * @param nombre            El nombre del producto refrigerado.
	 * @param descripcion       La descripción del producto refrigerado.
	 * @param valorUnitario     El valor unitario del producto refrigerado.
	 * @param cantidadExistencia La cantidad en existencia del producto refrigerado.
	 * @param codigoAprobacion   El código de aprobación del producto refrigerado.
	 * @param tempRefrigeracionRecomendada La temperatura de refrigeración recomendada del producto refrigerado.
	 */
	public ProductoRefrigerado(String codigo, String nombre, String descripcion, float valorUnitario,
			int cantidadExistencia, String codigoAprobacion, double tempRefrigeracionRecomendada) {
		super(codigo, nombre, descripcion, valorUnitario, cantidadExistencia);
		this.codigoAprobacion = codigoAprobacion;
		this.tempRefrigeracionRecomendada = tempRefrigeracionRecomendada;
	}

	/**
	 * Obtiene el código de aprobación del producto refrigerado.
	 *
	 * @return El código de aprobación del producto refrigerado.
	 */
	public String getCodigoAprobacion() {
		return codigoAprobacion;
	}

	/**
	 * Establece el código de aprobación del producto refrigerado.
	 *
	 * @param codigoAprobacion El nuevo código de aprobación del producto refrigerado.
	 */
	public void setCodigoAprobacion(String codigoAprobacion) {
		this.codigoAprobacion = codigoAprobacion;
	}

	/**
	 * Obtiene la temperatura de refrigeración recomendada del producto refrigerado.
	 *
	 * @return La temperatura de refrigeración recomendada del producto refrigerado.
	 */
	public double getTempRefrigeracionRecomendada() {
		return tempRefrigeracionRecomendada;
	}

	/**
	 * Establece la temperatura de refrigeración recomendada del producto refrigerado.
	 *
	 * @param tempRefrigeracionRecomendada La nueva temperatura de refrigeración recomendada del producto refrigerado.
	 */
	public void setTempRefrigeracionRecomendada(double tempRefrigeracionRecomendada) {
		this.tempRefrigeracionRecomendada = tempRefrigeracionRecomendada;
	}
}
