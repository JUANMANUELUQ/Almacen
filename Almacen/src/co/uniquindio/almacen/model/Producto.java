package co.uniquindio.almacen.model;

import java.util.List;
import java.util.ArrayList;

/**
 * La clase Producto representa un artículo que puede ser vendido.
 * Contiene información como código, nombre, descripción, valor unitario y cantidad en existencia.
 */
public class Producto {
	
	private String codigo;
	private String nombre;
	private String descripcion;
	private float valorUnitario;
	private int cantidadExistencia;
	private List<DetalleVenta> listaDetallesVenta;
	private Almacen ownedByAlmacen;
	
	/**
	 * Constructor por defecto de la clase Producto.
	 * Crea una instancia de Producto sin inicializar ningún atributo.
	 */
	public Producto() {
		// Sin implementación adicional en este constructor.
	}

	/**
	 * Constructor de la clase Producto con parámetros.
	 * Crea una instancia de Producto con la información proporcionada.
	 *
	 * @param codigo            El código del producto.
	 * @param nombre            El nombre del producto.
	 * @param descripcion       La descripción del producto.
	 * @param valorUnitario     El valor unitario del producto.
	 * @param cantidadExistencia La cantidad en existencia del producto.
	 */
	public Producto(String codigo, String nombre, String descripcion, float valorUnitario, int cantidadExistencia) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valorUnitario = valorUnitario;
		this.cantidadExistencia = cantidadExistencia;
		this.listaDetallesVenta=new ArrayList<DetalleVenta>();
	}

	/**
	 * Obtiene el código del producto.
	 *
	 * @return El código del producto.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Establece el código del producto.
	 *
	 * @param codigo El nuevo código del producto.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el nombre del producto.
	 *
	 * @return El nombre del producto.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del producto.
	 *
	 * @param nombre El nuevo nombre del producto.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la descripción del producto.
	 *
	 * @return La descripción del producto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción del producto.
	 *
	 * @param descripcion La nueva descripción del producto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el valor unitario del producto.
	 *
	 * @return El valor unitario del producto.
	 */
	public float getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * Establece el valor unitario del producto.
	 *
	 * @param valorUnitario El nuevo valor unitario del producto.
	 */
	public void setValorUnitario(float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * Obtiene la cantidad en existencia del producto.
	 *
	 * @return La cantidad en existencia del producto.
	 */
	public int getCantidadExistencia() {
		return cantidadExistencia;
	}

	/**
	 * Establece la cantidad en existencia del producto.
	 *
	 * @param cantidadExistencia La nueva cantidad en existencia del producto.
	 */
	public void setCantidadExistencia(int cantidadExistencia) {
		this.cantidadExistencia = cantidadExistencia;
	}

	public List<DetalleVenta> getListaDetallesVenta() {
		return listaDetallesVenta;
	}

	public void setListaDetallesVenta(List<DetalleVenta> listaDetallesVenta) {
		this.listaDetallesVenta = listaDetallesVenta;
	}

	public Almacen getOwnedByAlmacen() {
		return ownedByAlmacen;
	}

	public void setOwnedByAlmacen(Almacen ownedByAlmacen) {
		this.ownedByAlmacen = ownedByAlmacen;
	}

	/**
	 * Verifica si el código proporcionado coincide con el código del producto.
	 *
	 * @param codigo El código a verificar.
	 * @return true si el código coincide, false si no coincide.
	 */
	public boolean verificarCodigo(String codigo) {
		boolean respuesta = false;
		if (this.codigo.equals(codigo)) {
			respuesta = true;
		}
		return respuesta;
	}

	/**
	 * Obtiene el tipo de producto basado en la instancia actual de la clase.
	 *
	 * @return El tipo de producto como una cadena de texto ("Producto perecedero", "Producto refrigerado" o "Producto envasado").
	 */
	public String obtenerTipoProducto() {
    	String tipoProducto = "";
    	if (this instanceof ProductoPerecedero) {
    		tipoProducto = "Producto perecedero";
    	}
    	if (this instanceof ProductoRefrigerado) {
    		tipoProducto = "Producto refrigerado";
    	}
    	if (this instanceof ProductoEnvasado) {
    		tipoProducto = "Producto envasado";
    	}
    	return tipoProducto;
    }
	
	/**
	 * Agrega un objeto de DetalleVenta a la lista de detalles de esta venta.
	 *
	 * @param detalleVenta El DetalleVenta que se agregará a la lista de detalles de la venta.
	 */
	public void agregarDetalleVenta(DetalleVenta detalleVenta) {
		listaDetallesVenta.add(detalleVenta);
	}
}

