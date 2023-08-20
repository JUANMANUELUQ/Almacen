package co.uniquindio.almacen.model;
import java.util.List;
import java.time.LocalDate;

/**
 * La clase Venta representa una transacción de venta que contiene información sobre el código de la venta,
 * la fecha de la transacción, el total de la venta, el IVA aplicado, la lista de detalles de la venta y la lista de clientes involucrados.
 */
public class Venta {
	
	private String codigo;
	private LocalDate fecha;
	private float total;
	private double iva;
	private List<DetalleVenta> listaDetallesVenta;
	private Cliente cliente;
	private Almacen ownedByAlmacen;
	
	/**
	 * Constructor por defecto de la clase Venta.
	 * Crea una instancia de Venta sin inicializar ningún atributo.
	 */
	public Venta() {
		// Sin implementación adicional en este constructor.
	}

	/**
	 * Constructor de la clase Venta con parámetros.
	 * Crea una instancia de Venta con la información proporcionada.
	 *
	 * @param codigo            El código de la venta.
	 * @param fecha             La fecha de la transacción.
	 * @param total             El total de la venta.
	 * @param iva               El valor del IVA aplicado.
	 * @param listaDetallesVenta La lista de detalles de la venta.
	 * @param cliente     La lista de clientes involucrados en la venta.
	 */
	public Venta(String codigo, LocalDate fecha, float total, double iva, List<DetalleVenta> listaDetallesVenta,
			Cliente cliente) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
		this.iva = iva;
		this.listaDetallesVenta = listaDetallesVenta;
		this.cliente = cliente;
	}

	/**
	 * Obtiene el código de la venta.
	 *
	 * @return El código de la venta.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Establece el código de la venta.
	 *
	 * @param codigo El nuevo código de la venta.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene la fecha de la transacción de la venta.
	 *
	 * @return La fecha de la transacción de la venta.
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha de la transacción de la venta.
	 *
	 * @param fecha La nueva fecha de la transacción de la venta.
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene el total de la venta.
	 *
	 * @return El total de la venta.
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * Establece el total de la venta.
	 *
	 * @param total El nuevo total de la venta.
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * Obtiene el valor del IVA aplicado en la venta.
	 *
	 * @return El valor del IVA aplicado en la venta.
	 */
	public double getIva() {
		return iva;
	}

	/**
	 * Establece el valor del IVA aplicado en la venta.
	 *
	 * @param iva El nuevo valor del IVA aplicado en la venta.
	 */
	public void setIva(double iva) {
		this.iva = iva;
	}

	/**
	 * Obtiene la lista de detalles de la venta.
	 *
	 * @return La lista de detalles de la venta.
	 */
	public List<DetalleVenta> getListaDetallesVenta() {
		return listaDetallesVenta;
	}

	/**
	 * Establece la lista de detalles de la venta.
	 *
	 * @param listaDetallesVenta La nueva lista de detalles de la venta.
	 */
	public void setListaDetallesVenta(List<DetalleVenta> listaDetallesVenta) {
		this.listaDetallesVenta = listaDetallesVenta;
	}

	/**
	 * Obtiene la lista de clientes involucrados en la venta.
	 *
	 * @return La lista de clientes involucrados en la venta.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Establece la lista de clientes involucrados en la venta.
	 *
	 * @param cliente La nueva lista de clientes involucrados en la venta.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Obtiene la instancia del almacén al que pertenece esta entidad.
 	 *
 	 * @return La instancia del almacén al que pertenece esta entidad.
	 */
	public Almacen getOwnedByAlmacen() {
		return ownedByAlmacen;
	}
		
	/**
	 * Establece el almacén al que pertenece esta entidad.
	 *
	 * @param ownedByAlmacen La instancia del almacén al que se asociará esta entidad.
	 */
	public void setOwnedByAlmacen(Almacen ownedByAlmacen) {
		this.ownedByAlmacen = ownedByAlmacen;
	}

	/**
	 * Guarda los detalles de la venta en los productos correspondientes.
	 * Llama al método 'guardarDatosEnProducto' en cada detalle de venta para actualizar
	 * los datos de los productos asociados a esta venta.
	 */
	public void guardarDetallesEnProductos() {
		for (DetalleVenta detalleVenta : listaDetallesVenta) {
			detalleVenta.guardarDatosEnProducto();
		}
	}
	
	
}
