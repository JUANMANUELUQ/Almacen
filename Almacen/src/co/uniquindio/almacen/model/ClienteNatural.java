package co.uniquindio.almacen.model;

import java.time.LocalDate;

/**
 * La clase ClienteNatural representa un tipo de cliente que es una persona natural.
 * Además de la información básica de un cliente, esta clase incluye un correo electrónico y fecha de nacimiento.
 */
public class ClienteNatural extends Cliente {

    private String email;
    private LocalDate fechaNacimiento;

    /**
     * Constructor por defecto de la clase ClienteNatural.
     * Crea una instancia de ClienteNatural sin inicializar ningún atributo.
     */
    public ClienteNatural() {
        // constructor vacio.
    }

    /**
     * Constructor de la clase ClienteNatural con parámetros.
     * Crea una instancia de ClienteNatural con la información proporcionada, incluido el correo electrónico y fecha de nacimiento.
     *
     * @param nombre          El nombre del cliente natural.
     * @param apellidos       Los apellidos del cliente natural.
     * @param identificacion  La identificación única del cliente natural.
     * @param direccion       La dirección del cliente natural.
     * @param telefono        El número de teléfono del cliente natural.
     * @param email           El correo electrónico del cliente natural.
     * @param fechaNacimiento La fecha de nacimiento del cliente natural.
     */
    public ClienteNatural(String nombre, String apellidos, String identificacion, String direccion, String telefono,
                          String email, LocalDate fechaNacimiento) {
        super(nombre, apellidos, identificacion, direccion, telefono); // Llama al constructor de la clase base.
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el correo electrónico del cliente natural.
     *
     * @return El correo electrónico del cliente natural.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente natural.
     *
     * @param email El nuevo correo electrónico del cliente natural.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente natural.
     *
     * @return La fecha de nacimiento del cliente natural.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del cliente natural.
     *
     * @param fechaNacimiento La nueva fecha de nacimiento del cliente natural.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
