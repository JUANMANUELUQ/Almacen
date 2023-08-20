package co.uniquindio.almacen.model;
/**
 * La clase ClienteJuridico representa un tipo de cliente que es una entidad jurídica o empresa.
 * Además de la información básica de un cliente, esta clase incluye un número de identificación tributaria (NIT).
 */
public class ClienteJuridico extends Cliente {

    private String nit;

    /**
     * Constructor por defecto de la clase ClienteJuridico.
     * Crea una instancia de ClienteJuridico sin inicializar ningún atributo.
     */
    public ClienteJuridico() {
        // constructor vacio
    }

    /**
     * Constructor de la clase ClienteJuridico con parámetros.
     * Crea una instancia de ClienteJuridico con la información proporcionada, incluido el NIT.
     *
     * @param nombre        El nombre del cliente jurídico.
     * @param apellidos     Los apellidos del cliente jurídico.
     * @param identificacion    La identificación única del cliente jurídico.
     * @param direccion     La dirección del cliente jurídico.
     * @param telefono      El número de teléfono del cliente jurídico.
     * @param nit           El número de identificación tributaria del cliente jurídico.
     */
    public ClienteJuridico(String nombre, String apellidos, String identificacion, String direccion, String telefono,
                           String nit) {
        super(nombre, apellidos, identificacion, direccion, telefono); // Llama al constructor de la clase padre.
        this.nit = nit;
    }

    /**
     * Obtiene el número de identificación tributaria (NIT) del cliente jurídico.
     *
     * @return El NIT del cliente jurídico.
     */
    public String getNit() {
        return nit;
    }

    /**
     * Establece el número de identificación tributaria (NIT) del cliente jurídico.
     *
     * @param nit El nuevo NIT del cliente jurídico.
     */
    public void setNit(String nit) {
        this.nit = nit;
    }
}