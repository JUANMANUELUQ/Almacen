package co.uniquindio.almacen.controllers;

import java.time.LocalDate;

import co.uniquindio.almacen.application.Aplicacion;
import co.uniquindio.almacen.model.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Controlador de fábrica de modelos que gestiona la lógica y la interacción con los datos del sistema.
 */
public class ModelFactoryController {
	
	private Almacen almacen;
	private Aplicacion aplicacion;
	private List<Producto> listaProductosPosibles;
	
	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	/**
	 * Oibteer la instancia única de la clase ModelFactoryController.
	 * @return La instancia única de ModelFactoryController.
	 */
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	/**
	 * Constructor de la clase ModelFactoryController. Inicializa los datos del sistema.
	 */
	public ModelFactoryController() {
        inicializarDatos();
	}
	
	/**
	 * Inicializa los datos del sistema, agregando un cliente natural de ejemplo.
	 */
	private void inicializarDatos() {
		almacen=new Almacen("Almacen UQ");
		ClienteNatural cliente=new ClienteNatural("Juan Manuel","Isaza Vergara","1090272239","Mi casa",
		"3146748951","juan@gmail.com", LocalDate.of(2004, 5, 11));
		almacen.agregarCliente(cliente);
	}
	
	/**
	 * Establece la lista de productos posibles para las ventanas.
	 * @param listaTemporalProductosVentana Lista de productos posibles para mostrar en las ventanas.
	 */
	public void setListaPosiblesProductos(List<Producto> listaTemporalProductosVentana) {
		this.listaProductosPosibles=listaTemporalProductosVentana;
	}
	
	/**
	 * Obtiene la lista de productos posibles para las ventanas.
	 * @return Lista de productos posibles para mostrar en las ventanas.
	 */
	public List<Producto> getListaPosiblesProductos(){
		return this.listaProductosPosibles;
	}
	
	/**
	 * Obtiene el almacén del sistema.
	 * @return El almacén del sistema.
	 */
	public Almacen getAlmacen() {
		return almacen;
	}
	
	/**
	 * Establece el almacén del sistema.
	 * @param alamcen El almacén a establecer.
	 */
	public void setAlmacen(Almacen alamcen) {
		this.almacen = alamcen;
	}

	/**
	 * Obtiene la instancia de la aplicación.
	 * @return La instancia de la aplicación.
	 */
	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	/**
	 * Establece la instancia de la aplicación.
	 * @param aplicacion La instancia de la aplicación a establecer.
	 */
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Elimina un cliente del sistema por su identificación.
	 * @param identificacion Identificación del cliente a eliminar.
	 */
	public void eliminarCliente(String identificacion) {
		almacen.eliminarCliente(identificacion);
	}

	/**
	 * Verifica si un cliente existe en el sistema por su identificación.
	 * @param identificacion Identificación del cliente a verificar.
	 * @return true si el cliente existe, false si no existe.
	 */
	public boolean existeCliente(String identificacion) {
		return almacen.existeCliente(identificacion);
	}

	/**
	 * Agrega un cliente al sistema.
	 * @param clienteSeleccionado Cliente a agregar al sistema.
	 */
	public void agregarCliente(Cliente clienteSeleccionado) {
		almacen.agregarCliente(clienteSeleccionado);
	}

	/**
	 * Elimina un producto del sistema por su código.
	 * @param codigo Código del producto a eliminar.
	 */
	public void eliminarProducto(String codigo) {
		almacen.eliminarProducto(codigo);
	}

	/**
	 * Verifica si un producto existe en el sistema por su código.
	 * @param codigo Código del producto a verificar.
	 * @return true si el producto existe, false si no existe.
	 */
	public boolean existeProducto(String codigo) {
		return almacen.existeProducto(codigo);
	}
	
	/**
	 * Agrega un producto al sistema.
	 * @param productoSeleccionado Producto a agregar al sistema.
	 */
	public void agregarProducto(Producto productoSeleccionado){
		almacen.agregarProducto(productoSeleccionado);
	}

	/**
	 * Obtiene una copia de la lista de productos posibles.
	 * @return Lista de productos posibles en una nueva instancia.
	 */
	public List<Producto> retornarPosiblesProductos(){
		return this.listaProductosPosibles;
	}
	
	/**
	 * Actualiza la lista de productos en el almacén con la lista proporcionada.
	 * @param listaTemporalProductosVentana Nueva lista de productos para actualizar en el almacén.
	 */
	public void actualizarListaProductos(List<Producto> listaTemporalProductosVentana) {
		almacen.setListaProductos(listaTemporalProductosVentana);
		
	}
	
	/**
	 * Agrega una venta al sistema.
	 * @param venta Venta a agregar al sistema.
	 */
	public void agregarVenta(Venta venta) {
		almacen.realizarVenta(venta);
	}

	/**
	 * Obtiene una copia de la lista de productos en el almacén.
	 * @return Lista de productos en una nueva instancia.
	 */
	public List<Producto> obtenerCopiaProductos() {
		List<Producto> listaCopiada=new ArrayList<>();
		for (Producto producto : almacen.getListaProductos()) {
			listaCopiada.add(producto);
		}
		return listaCopiada;
	}
	
}
