package co.uniquindio.almacen.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.awt.event.ActionEvent;
import java.util.List;

import co.uniquindio.almacen.application.Aplicacion;
import co.uniquindio.almacen.model.*;
import co.uniquindio.almacen.util.ClaseUtilitaria;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de ingreso de clientes en un almacén de alimentos.
 */
public class IngresoClienteController {

	@FXML
    private TextField txtNombre;
	
    @FXML
    private TextField txtApellidos;
    
    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtDireccion;
    
    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private DatePicker fechaNacimiento;
    
    @FXML
    private TextField txtNit;
    

    @FXML
    private Text apellidosField;

    @FXML
    private Text identificacionField;

    @FXML
    private Button btnAgregar;

    @FXML
    private Text direccionField;

    @FXML
    private Text nombreField;

    @FXML
    private TableView<Cliente> tableClientes;
    
    @FXML
    private TableColumn<Cliente,String> columnNombre;
    
    @FXML
    private TableColumn<Cliente,String> columnApellidos;
    
    @FXML
    private TableColumn<Cliente,String> columnDocumento;
    
    @FXML
    private TableColumn<Cliente,String> columnDireccion;
    
    @FXML
    private TableColumn<Cliente,String> columnTelefono;
    
    @FXML
    private TableColumn<Cliente,String> columnTipo;

    @FXML
    private Label gestionClientesField;

    @FXML
    private Button btnVolver;

    @FXML
    private Text telefonoField;

    @FXML
    private Text tipoClienteField;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;

    @FXML
    private ComboBox cmbTipoCliente;
    
    private Stage ventana;
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Aplicacion aplicacion=mfm.getAplicacion();

	/**
     * Inicializa el controlador después de que se haya cargado el FXML.
     */
	@FXML
	private void initialize() {
		ObservableList<String> tiposClientes=FXCollections.observableArrayList();
		tiposClientes.add("Persona natural");
		tiposClientes.add("Persona juridica");
		cmbTipoCliente.setItems(tiposClientes);
		bloquearEntradasExtra();
		fechaNacimiento.setEditable(false);
		actualizarTabla();
	}
	
	/**
     * Pone los datos del cliente seleccionado en los campos de entrada.
     */
    @FXML
    void ponerDatos() {
    	int selectedIndex = tableClientes.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
			txtNombre.setText(cliente.getNombre());
			txtApellidos.setText(cliente.getApellidos());
			txtIdentificacion.setText(cliente.getIdentificacion());
			txtDireccion.setText(cliente.getDireccion());
			txtTelefono.setText(cliente.getTelefono());
			cmbTipoCliente.setValue(cliente.obtenerTipoCliente());
			bloquearDatosCorrespondienetes();
			switch(""+cmbTipoCliente.getValue()) {
				case "Persona natural": 
					txtEmail.setText(((ClienteNatural)cliente).getEmail());
					fechaNacimiento.setValue(((ClienteNatural)cliente).getFechaNacimiento());
					break;
				case "Persona juridica": 
					txtNit.setText(((ClienteJuridico)cliente).getNit());
					break;
			}
		}
    }

	/**
     * Elimina el cliente seleccionado.
     */
    @FXML
    void eliminar() {
    	int selectedIndex = tableClientes.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
			mfm.eliminarCliente(cliente.getIdentificacion());
			tableClientes.getItems().remove(selectedIndex);
		} else {
			ClaseUtilitaria.mostrarMensaje("No hay un cliente seleccionado", "No hay un cliente seleccionado", "Elija un cliente", AlertType.ERROR);
		}
    }
	
	/**
     * Actualiza los datos del cliente seleccionado.
     */
    @FXML
    void actualizar() {
    	int selectedIndex = tableClientes.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			if (verificarDatos()) {
				Cliente clienteSeleccionado = tableClientes.getSelectionModel().getSelectedItem();
				if (mfm.existeCliente(txtIdentificacion.getText()) && !clienteSeleccionado.verificarIdentificacion(txtIdentificacion.getText())) {
					ClaseUtilitaria.mostrarMensaje("Usuario ya existente", "Usuario ya existente", "Ya existe un usuario con ese documento", AlertType.ERROR);
				} else {
					clienteSeleccionado.setNombre(txtNombre.getText());
					clienteSeleccionado.setApellidos(txtApellidos.getText());
					clienteSeleccionado.setIdentificacion(txtIdentificacion.getText());
					clienteSeleccionado.setDireccion(txtDireccion.getText());
					clienteSeleccionado.setTelefono(txtTelefono.getText());
					switch(""+cmbTipoCliente.getValue()) {
						case "Persona natural": 
							((ClienteNatural)clienteSeleccionado).setEmail(txtEmail.getText());
							((ClienteNatural)clienteSeleccionado).setFechaNacimiento(fechaNacimiento.getValue());
							break;
						case "Persona juridica": 
							((ClienteJuridico)clienteSeleccionado).setNit(txtNit.getText());
							break;
					}
					actualizarTabla();
					
				}
			}
		} else {
			ClaseUtilitaria.mostrarMensaje("No hay un cliente seleccionado", "No hay un cliente seleccionado", "Elija un cliente", AlertType.ERROR);
		}
    }
	
	/**
     * Agrega un nuevo cliente a la lista y la tabla.
     */
    @FXML
    void agregar() {
    	if (verificarDatos()) {
			if (mfm.existeCliente(txtIdentificacion.getText())) {
				ClaseUtilitaria.mostrarMensaje("Cliente ya existente", "Cliente ya existente", "Ya existe un cliente con ese documento", AlertType.ERROR);
			} else {
				Cliente cliente=null;
				switch(""+cmbTipoCliente.getValue()) {
				case "Persona natural": 
					cliente = new ClienteNatural(txtNombre.getText(),txtApellidos.getText(),txtIdentificacion.getText(),
					txtDireccion.getText(),txtTelefono.getText(),txtEmail.getText(), fechaNacimiento.getValue());
					break;
				case "Persona juridica": 
					cliente = new ClienteJuridico(txtNombre.getText(),txtApellidos.getText(),txtIdentificacion.getText(),
					txtDireccion.getText(),txtTelefono.getText(),txtNit.getText());
					break;
				}
				mfm.agregarCliente(cliente);
				actualizarTabla();
			}
		}
    }
	
	/**
     * Cierra la ventana actual y muestra la ventana principal.
     */
    @FXML
    void volver() {
    	ventana.close();
    	aplicacion.mostrarVentanaPrincipal();
    }
    
    /**
     * Bloquea las entradas correspondientes al tipo de cliente seleccionado.
     */
	@FXML
	private void bloquearDatosCorrespondienetes() {
		bloquearEntradasExtra();
		switch(""+cmbTipoCliente.getValue()) {
			case "Persona natural": 
				desbloquearEntrada(txtEmail);
				desbloquearEntrada(fechaNacimiento);
				break;
			case "Persona juridica": 
				desbloquearEntrada(txtNit);
				break;
		}
	}
	
	/**
     * Bloquea todas las entradas adicionales.
     */
	public void bloquearEntradasExtra() {
		bloquearEntrada(txtEmail);
		bloquearEntrada(fechaNacimiento);
		bloquearEntrada(txtNit);
	}
    
    /**
     * Verifica la validez de los datos ingresados por el usuario.
     * @return true si los datos son válidos, false en caso contrario.
     */
    public boolean verificarDatos() {
        boolean sonValidos=false;
        String msj="";
        if (txtNombre.getText()==null || txtNombre.getText().trim().equals("")) {
            msj+="El nombre no debe estar vacio";
        }
        if (txtApellidos.getText()==null || txtApellidos.getText().trim().equals("")) {
            msj+="\n\nLos apellidos no deben estar vacios";
        }
        if (txtIdentificacion.getText()==null || txtIdentificacion.getText().trim().equals("")) {
            msj+="\n\nEl documento no debe estar vacio";
        } else if(!txtIdentificacion.getText().trim().matches("[0-9]+")){
            msj+="\n\nDocumento no valido";
        }
        if (txtDireccion.getText()==null || txtDireccion.getText().trim().equals("")) {
            msj+="\n\nLa direccion no debe estar vacia";
        }
        if (txtTelefono.getText()==null || txtTelefono.getText().trim().equals("")) {
            msj+="\n\nEl nuemro no debe estar vacio";
        } else if(!txtTelefono.getText().trim().matches("[0-9]+")){
            msj+="\n\nNumero no valido";
        }
        if (cmbTipoCliente.getValue()==null || (""+cmbTipoCliente.getValue()).equals("")) {
			msj+="\n\nLa informacion del tipo de cliente no debe estar vacio";
		} else {
			switch(""+cmbTipoCliente.getValue()) {
				case "Persona natural":
					if (txtEmail.getText()==null || txtEmail.getText().trim().equals("")) {
			            msj+="\n\nEl correo electronico no debe estar vacio";
			        }
					if (fechaNacimiento.getValue()==null) {
						msj+="\n\nLa fecha de nacimiento no debe estar vacia";
					}
					break;
				case "Persona juridica": 
					if (txtNit.getText()==null || txtNit.getText().trim().equals("")) {
			            msj+="\n\nEl nit no debe estar vacio";
			        }
					break;
			}
		}
        if (msj.equals("")) {
            sonValidos=true;
        } else {
        	ClaseUtilitaria.mostrarMensaje("Entradas no validas", "Entradas no validas", msj, AlertType.ERROR);
        }
        return sonValidos;
    }
    
    /**
     * Actualiza la tabla de clientes con los datos actuales.
     */
	public void actualizarTabla() {
		tableClientes.setItems(FXCollections.observableArrayList(mfm.getAlmacen().getListaClientes()));
		columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getNombre()));
		columnApellidos.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getApellidos()));
		columnDocumento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
		columnDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
		columnTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
		columnTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTipoCliente()));
		tableClientes.refresh();
	}
    
    /**
     * Bloquea la entrada de texto en un campo de texto y establece el estilo de fondo como gris.
     * @param texto El campo de texto a bloquear.
     */
	public void bloquearEntrada(TextField texto) {
		texto.setEditable(false);
		texto.setStyle("-fx-background-color: #aaaaaa;");
		texto.setText("N/A");
	}
	
	/**
     * Bloquea la entrada de texto en un campo de texto y establece el estilo de fondo como gris.
     * @param texto El campo de fecha a bloquear.
     */
	public void bloquearEntrada(DatePicker fecha) {
		fecha.setMouseTransparent(true);
		fecha.setFocusTraversable(false);
		fecha.getEditor().setText("N/A");
		fecha.getEditor().setStyle("-fx-background-color: #aaaaaa;");
	}
	
    /**
     * Desbloquea la entrada de texto en un campo de texto y restablece el estilo de fondo predeterminado.
     * @param texto El campo de texto a desbloquear.
     */
	public void desbloquearEntrada(TextField texto) {
		texto.setEditable(true);
		texto.setStyle(null);
		texto.setText("");
	}
	
	/**
     * Desbloquea la entrada de texto en un campo de texto y restablece el estilo de fondo predeterminado.
     * @param texto El campo de texto a desbloquear.
     */
	public void desbloquearEntrada(DatePicker fecha) {
		fecha.setMouseTransparent(false);
		fecha.setFocusTraversable(true);
		fecha.getEditor().setText("");
		fecha.getEditor().setStyle("-fx-background-color: #cccccc;");
	}
    
    /**
     * Establece la ventana actual para este controlador
     * @param ventana La ventana actual
     */
    public void setVentana(Stage primaryStage) {
		this.ventana = primaryStage;
	}

}
