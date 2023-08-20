package co.uniquindio.almacen.model;

/**
 * La clase DetalleVenta representa un detalle específico de una venta, 
 * incluyendo la cantidad de productos vendidos,
 * el subtotal de esa venta y el producto asociado.
 */
public class DetalleVenta {
	
	private int cantProductosVendidos;
	private float subTotal;
	private Producto producto;
	
	/**
	 * Constructor por defecto de la clase DetalleVenta.
	 * Crea una instancia de DetalleVenta sin inicializar ningún atributo.
	 */
	public DetalleVenta() {
		// constructor vacio
	}
	
	/**
	 * Constructor de la clase DetalleVenta con parámetros.
	 * Crea una instancia de DetalleVenta con la información proporcionada.
	 *
	 * @param cantProductosVendidos La cantidad de productos vendidos en este detalle.
	 * @param subTotal              El subtotal de la venta asociada a este detalle.
	 * @param producto              El producto asociado a este detalle.
	 */
	public DetalleVenta(int cantProductosVendidos, float subTotal, Producto producto) {
		this.cantProductosVendidos = cantProductosVendidos;
		this.subTotal = subTotal;
		this.producto = producto;
	}

	/**
	 * Obtiene la cantidad de productos vendidos en este detalle.
	 *
	 * @return La cantidad de productos vendidos.
	 */
	public int getCantProductosVendidos() {
		return cantProductosVendidos;
	}

	/**
	 * Establece la cantidad de productos vendidos en este detalle.
	 *
	 * @param cantProductosVendidos La nueva cantidad de productos vendidos.
	 */
	public void setCantProductosVendidos(int cantProductosVendidos) {
		this.cantProductosVendidos = cantProductosVendidos;
	}

	/**
	 * Obtiene el subtotal de la venta asociada a este detalle.
	 *
	 * @return El subtotal de la venta.
	 */
	public float getSubTotal() {
		return subTotal;
	}

	/**
	 * Establece el subtotal de la venta asociada a este detalle.
	 *
	 * @param subTotal El nuevo subtotal de la venta.
	 */
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * Obtiene el producto asociado a este detalle.
	 *
	 * @return El producto asociado.
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * Establece el producto asociado a este detalle.
	 *
	 * @param producto El nuevo producto asociado.
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * Guarda los datos de este DetalleVenta en el Producto correspondiente.
	 * Agrega este DetalleVenta a la lista de detalles del Producto.
	 * Este método se utiliza para mantener una referencia bidireccional entre
	 * DetalleVenta y Producto.
	 */
	public void guardarDatosEnProducto() {
		producto.agregarDetalleVenta(this);
	}

}
