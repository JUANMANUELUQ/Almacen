package co.uniquindio.almacen.model;

import java.util.List;
import java.util.ArrayList;

/**
 * La clase Cliente representa información básica sobre un cliente, incluyendo su dirección y número de teléfono.
 */
public class Cliente extends Persona{

    // Atributos
    private String direccion;
    private String telefono;
    private List<Venta> listaVentas;
    private Almacen ownedByAlmacen;

    /**
     * Constructor por defecto de la clase Cliente.
     * Crea una instancia de Cliente sin inicializar ningún atributo.
     */
    public Cliente() {
        // constructor vacio
    }

    /**
     * Constructor de la clase Cliente con parámetros.
     * Crea una instancia de Cliente con la información proporcionada.
     * @param nombre        El nombre del cliente.
     * @param apellidos     Los apellidos del cliente.
     * @param identificacion    La identificación única del cliente.
     * @param direccion     La dirección del cliente.
     * @param telefono      El número de teléfono del cliente.
     */
    	public Cliente(String nombre, String apellidos, String identificacion, String direccion, String telefono) {
		super(nombre, apellidos, identificacion);
		this.direccion = direccion;
		this.telefono = telefono;
		this.listaVentas=new ArrayList<Venta>();
	}

    /**
     * Obtiene la dirección del cliente.
     * @return La dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     * @param direccion La nueva dirección del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * @return El número de teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     * @param telefono El nuevo número de teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la lista de ventas
     * @return La lista de ventas
     */
	public List<Venta> getListaVentas() {
		return listaVentas;
	}

	/**
	 * Establece la lista de ventas
	 * @param listaVentas La lista de ventas
	 */
	public void setListaVentas(List<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}

	/**
	 * Obtiene el almacen al que hace parte
	 * @return El almacen al que hace parte
	 */
	public Almacen getOwnedByAlmacen() {
		return ownedByAlmacen;
	}

	/**
	 * Establece el almacen al que hace parte
	 * @param ownedByAlmacen Nuevo almacen al que hace parte
	 */
	public void setOwnedByAlmacen(Almacen ownedByAlmacen) {
		this.ownedByAlmacen = ownedByAlmacen;
	}

	/**
	 * Verifica si la identificación proporcionada coincide con la identificación del cliente actual.
	 * 
	 * @param identificacion La identificación a verificar.
	 * @return true si la identificación coincide, false si no coincide.
	 */
	public boolean verificarIdentificacion(String identificacion) {
	    boolean respuesta = false;
	    if (this.getIdentificacion().equals(identificacion)) {
	        respuesta = true;
	    }
	    return respuesta;
	}
	
	/**
	 * Obtiene el tipo de cliente basado en la instancia actual de la clase.
	 * 
	 * @return El tipo de cliente como una cadena de texto ("Persona natural" o "Persona jurídica").
	 */
	public String obtenerTipoCliente() {
	    String tipoCliente = "";
	    if (this instanceof ClienteNatural) {
	        tipoCliente = "Persona natural";
	    }
	    if (this instanceof ClienteJuridico) {
	        tipoCliente = "Persona jurídica";
	    }
	    return tipoCliente;
	}

}
