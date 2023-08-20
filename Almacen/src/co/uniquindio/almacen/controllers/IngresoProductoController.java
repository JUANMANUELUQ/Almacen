package co.uniquindio.almacen.controllers;

import co.uniquindio.almacen.application.Aplicacion;
import co.uniquindio.almacen.model.*;
import co.uniquindio.almacen.util.ClaseUtilitaria;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de ingreso de productos en un almacén de alimentos.
 */
public class IngresoProductoController {
	
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextArea txtDescripcion;
	@FXML
	private TextField txtValorUnitario;
	@FXML
	private TextField txtCantidadExistencia;
	@FXML
	private ComboBox cmbTipoProducto;
	@FXML
	private DatePicker fechaVencimiento;
	@FXML
	private TextField txtCodigoAprobacion;
	@FXML
	private TextField txtTempRecomendadaRefrigeracion;
	@FXML
	private DatePicker fechaEnvasado;
	@FXML
	private TextField txtPesoEnvase;
	@FXML
	private ComboBox paisOrigen;
	@FXML
	private TableView<Producto> tableProductos;
	@FXML
	private TableColumn<Producto,String> columnCodigo;
	@FXML
	private TableColumn<Producto,String> columnNombre;
	@FXML
	private TableColumn<Producto,String> columnValorUnitario;
	@FXML
	private TableColumn<Producto,String> columnCantExistencia;
	@FXML
	private TableColumn<Producto,String> columnTipoProducto;
	
	private Stage ventana;
    private ModelFactoryController mfm = ModelFactoryController.getInstance();
	private Aplicacion aplicacion=mfm.getAplicacion();
	
	/**
     * Inicializa el controlador después de que se haya cargado el FXML.
     */
	@FXML
	private void initialize() {
		ObservableList<String> tiposProductos=FXCollections.observableArrayList();
		tiposProductos.add("Producto perecedero");
		tiposProductos.add("Producto refrigerado");
		tiposProductos.add("Producto envasado");
		cmbTipoProducto.setItems(tiposProductos);
		ObservableList<String> paisesOrigen=FXCollections.observableArrayList();
		paisesOrigen.add("Colombia");
		paisesOrigen.add("Argentina");
		paisesOrigen.add("Chile");
		paisesOrigen.add("Ecuador");
		paisesOrigen.add("Peru");
		paisOrigen.setItems(paisesOrigen);
		fechaVencimiento.setEditable(false);
		fechaVencimiento.setStyle("-fx-background-color: #aaaaaa;");
		fechaVencimiento.getEditor().setStyle("-fx-background-color: #cccccc;");
		fechaEnvasado.setEditable(false);
		fechaEnvasado.setStyle("-fx-background-color: #aaaaaa;");
		fechaEnvasado.getEditor().setStyle("-fx-background-color: #cccccc;");
		bloquearEntradasExtra();
		actualizarTabla();
	}
	
	/**
     * Actualiza la tabla de productos con los datos actuales.
     */
	public void actualizarTabla() {
		tableProductos.setItems(FXCollections.observableArrayList(mfm.getAlmacen().getListaProductos()));
		columnCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCodigo()));
		columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getNombre()));
		columnValorUnitario.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getValorUnitario()));
		columnCantExistencia.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCantidadExistencia()));
		columnTipoProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerTipoProducto()));
		tableProductos.refresh();
	}
	
	/**
     * Pone los datos del producto seleccionado en los campos de entrada.
     */
	@FXML
	private void ponerDatos() {
		int selectedIndex = tableProductos.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Producto cliente = tableProductos.getSelectionModel().getSelectedItem();
			txtCodigo.setText(cliente.getCodigo());
			txtNombre.setText(cliente.getNombre());
			txtValorUnitario.setText(""+cliente.getValorUnitario());
			txtCantidadExistencia.setText(""+cliente.getCantidadExistencia());
			cmbTipoProducto.setValue(cliente.obtenerTipoProducto());
			bloquearDatosCorrespondientes();
			switch(""+cmbTipoProducto.getValue()) {
				case "Producto perecedero": 
					fechaVencimiento.setValue(((ProductoPerecedero)cliente).getFechaVencimiento());
					break;
				case "Producto refrigerado": 
					txtCodigoAprobacion.setText(((ProductoRefrigerado)cliente).getCodigoAprobacion());
					txtTempRecomendadaRefrigeracion.setText(""+((ProductoRefrigerado)cliente).getTempRefrigeracionRecomendada());
					break;
				case "Producto envasado": 
					fechaEnvasado.setValue(((ProductoEnvasado)cliente).getFechaEnvasado());
					txtPesoEnvase.setText(""+((ProductoEnvasado)cliente).getPesoEnvase());
					paisOrigen.setValue(((ProductoEnvasado)cliente).obtenerPaisOrigenCadena());
					break;
			}
		}
	}
	
	/**
     * Bloquea las entradas correspondientes al tipo de producto seleccionado.
     */
	@FXML
	private void bloquearDatosCorrespondientes() {
		bloquearEntradasExtra();
		switch(""+cmbTipoProducto.getValue()) {
			case "Producto perecedero": 
				desbloquearEntrada(fechaVencimiento);
				break;
			case "Producto refrigerado": 
				desbloquearEntrada(txtCodigoAprobacion);
				desbloquearEntrada(txtTempRecomendadaRefrigeracion);
				break;
			case "Producto envasado": 
				desbloquearEntrada(fechaEnvasado);
				desbloquearEntrada(txtPesoEnvase);
				desbloquearEntrada(paisOrigen);
				break;
		}
	}
	
	/**
     * Desbloquea una entrada de texto.
     * @param texto El campo de texto a desbloquear.
     */
	public void desbloquearEntrada(TextField texto) {
		texto.setEditable(true);
		texto.setStyle(null);
		texto.setText("");
	}
	
	/**
     * Desbloquea un campo de fecha.
     * @param fecha El campo de fecha a desbloquear.
     */
	public void desbloquearEntrada(DatePicker fecha) {
		fecha.setMouseTransparent(false);
		fecha.setFocusTraversable(true);
		fecha.getEditor().setText("");
		fecha.getEditor().setStyle("-fx-background-color: #cccccc;");
	}
	
	/**
     * Desbloquea un combo box.
     * @param comboBox El combo box a desbloquear.
     */
	public void desbloquearEntrada(ComboBox comboBox) {
		comboBox.setMouseTransparent(false);
		comboBox.setFocusTraversable(true);
		comboBox.setStyle(null);
		comboBox.setValue("");
	}

	/**
     * Actualiza los datos del producto seleccionado en la tabla.
     */
	@FXML
	private void actualizar() {
		int selectedIndex = tableProductos.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			if (verificarDatos()) {
				Producto productoSeleccionado = tableProductos.getSelectionModel().getSelectedItem();
				if (mfm.existeProducto(txtCodigo.getText()) && !productoSeleccionado.verificarCodigo(txtCodigo.getText())) {
					ClaseUtilitaria.mostrarMensaje("Producto ya existente", "Producto ya existente", "Ya existe un producto con ese codigo", AlertType.ERROR);
				} else {
					productoSeleccionado.setCodigo(txtCodigo.getText());
					productoSeleccionado.setNombre(txtNombre.getText());
					productoSeleccionado.setDescripcion(txtDescripcion.getText());
					productoSeleccionado.setValorUnitario(Float.parseFloat(txtValorUnitario.getText()));
					productoSeleccionado.setCantidadExistencia(Integer.parseInt(txtCantidadExistencia.getText()));
					switch(""+cmbTipoProducto.getValue()) {
						case "Producto perecedero":
							((ProductoPerecedero)productoSeleccionado).setFechaVencimiento(fechaVencimiento.getValue());
							break;
						case "Producto refrigerado": 
							((ProductoRefrigerado)productoSeleccionado).setCodigoAprobacion(txtCodigoAprobacion.getText());
							((ProductoRefrigerado)productoSeleccionado).setTempRefrigeracionRecomendada(Double.parseDouble(
							txtTempRecomendadaRefrigeracion.getText()));
						case "Producto envasado": 
							((ProductoEnvasado)productoSeleccionado).setFechaEnvasado(fechaEnvasado.getValue());
							((ProductoEnvasado)productoSeleccionado).setPesoEnvase(Double.parseDouble(txtPesoEnvase.getText()));
							((ProductoEnvasado)productoSeleccionado).setPaisOrigen(ClaseUtilitaria.obtenerPaisOrigen(""+paisOrigen.getValue()));
							break;
					}
					actualizarTabla();
					
				} 
			}
		} else {
			ClaseUtilitaria.mostrarMensaje("No hay un producto seleccionado", "No hay un producto seleccionado", "Elija un producto", AlertType.ERROR);
		}
		
	}
	
	/**
     * Verifica si los datos ingresados en los campos son válidos.
     * @return true si los datos son válidos, false si no lo son.
     */
	public boolean verificarDatos() {
        boolean sonValidos=false;
        String msj="";
        if (txtNombre.getText()==null || txtNombre.getText().trim().equals("")) {
            msj+="El nombre no debe estar vacio";
        }
        if (txtCodigo.getText()==null || txtCodigo.getText().trim().equals("")) {
            msj+="\n\nLos apellidos no deben estar vacios";
        }
        try {
			if (Double.parseDouble(txtValorUnitario.getText())<0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (txtValorUnitario.getText().trim().equals("")) {
				msj+="\n\nEl valor unitario no debe estar vacio";
			} else {
				msj+="\n\nValor unitario no valido";
			}
		}
        try {
			if (Integer.parseInt(txtCantidadExistencia.getText())<=0) {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			if (txtCantidadExistencia.getText().trim().equals("")) {
				msj+="\n\nLa cantidad no debe estar vacia";
			} else {
				msj+="\n\nCantidad no valido";
			}
		}
  
        if (cmbTipoProducto.getValue()==null || (""+cmbTipoProducto.getValue()).equals("")) {
			msj+="\n\nLa informacion del tipo de producto no debe estar vacia";
		} else {
			switch(""+cmbTipoProducto.getValue()) {
				case "Producto perecedero":
					if (fechaVencimiento.getValue()==null) {
						msj+="\n\nLa fecha de vencimiento no debe estar vacia";
					}
					
					break;
				case "Producto refrigerado": 
					if (txtCodigoAprobacion.getText()==null || txtCodigoAprobacion.getText().trim().equals("")) {
			            msj+="\n\nEl codigo de aprobacion no debe estar vacio";
			        }
			        try {
						if (Double.parseDouble(txtTempRecomendadaRefrigeracion.getText())<0) {
							throw new Exception("Valor invalido");
						}
					} catch (Exception e) {
						if (txtTempRecomendadaRefrigeracion.getText().trim().equals("")) {
							msj+="\n\nLa temperatura de refrigeracion recomendada no debe estar vacia";
						} else {
							msj+="\n\nLa temperatura de refrigeracion recomendada no valida";
						}
					}
					break;
				case "Producto envasado": 
					try {
						if (Double.parseDouble(txtPesoEnvase.getText())<0) {
							throw new Exception("Valor invalido");
						}
					} catch (Exception e) {
						if (txtPesoEnvase.getText().trim().equals("")) {
							msj+="\n\nEl peso del envase no debe estar vacio";
						} else {
							msj+="\n\nPeso del envase no valido";
						}
					}
			        if (fechaEnvasado.getValue()==null) {
						msj+="\n\nLa fecha de envasado no debe estar vacia";
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
     * Elimina el producto seleccionado de la lista y la tabla.
     */
	@FXML
	private void eliminar() {
		int selectedIndex = tableProductos.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Producto producto = tableProductos.getSelectionModel().getSelectedItem();
			mfm.eliminarProducto(producto.getCodigo());
			tableProductos.getItems().remove(selectedIndex);
		} else {
			ClaseUtilitaria.mostrarMensaje("No hay un producto seleccionado", "No hay un producto seleccionado", "Elija un producto", AlertType.ERROR);
		}
	}
	
	/**
     * Agrega un nuevo producto a la lista y la tabla.
     */
	@FXML
	private void agregar() {
		if (verificarDatos()) {
			if (mfm.existeProducto(txtCodigo.getText())) {
				ClaseUtilitaria.mostrarMensaje("Producto ya existente", "producto ya existente", "Ya existe un producto con ese codigo", AlertType.ERROR);
			} else {
				Producto producto=null;
				switch(""+cmbTipoProducto.getValue()) {
				case "Producto perecedero": 
					producto = new ProductoPerecedero(txtCodigo.getText(), txtNombre.getText(), txtDescripcion.getText(), Float.parseFloat(txtValorUnitario.getText()),
					Integer.parseInt(txtCantidadExistencia.getText()), fechaVencimiento.getValue());
					break;
				case "Producto refrigerado": 
					producto = new ProductoRefrigerado(txtCodigo.getText(), txtNombre.getText(), txtDescripcion.getText(), Float.parseFloat(txtValorUnitario.getText()),
					Integer.parseInt(txtCantidadExistencia.getText()), txtCodigoAprobacion.getText(), Double.parseDouble(txtTempRecomendadaRefrigeracion.getText()));
					break;
				case "Producto envasado":
					producto = new ProductoEnvasado(txtCodigo.getText(), txtNombre.getText(), txtDescripcion.getText(), Float.parseFloat(txtValorUnitario.getText()),
					Integer.parseInt(txtCantidadExistencia.getText()), fechaEnvasado.getValue(), Double.parseDouble(txtPesoEnvase.getText()), ClaseUtilitaria.obtenerPaisOrigen(""+paisOrigen.getValue()));
					break;
				}
				mfm.agregarProducto(producto);
				actualizarTabla();
			}
		}
		
	}
	
	/**
     * Cierra la ventana actual y muestra la ventana principal.
     */
	@FXML
	private void volver() {
		ventana.close();
		aplicacion.mostrarVentanaPrincipal();
	}
	
	/**
     * Muestra información adicional del producto seleccionado en la tabla.
     */
	@FXML
	private void verDatosExtra() {
		
	}
	
	/**
     * Bloquea todos los campos de entrada adicionales.
     */
	public void bloquearEntradasExtra() {
		bloquearEntrada(fechaVencimiento);
		bloquearEntrada(txtCodigoAprobacion);
		bloquearEntrada(txtTempRecomendadaRefrigeracion);
		bloquearEntrada(fechaEnvasado);
		bloquearEntrada(txtPesoEnvase);
		bloquearEntrada(paisOrigen);
	}
	
	/**
     * Bloquea un campo de entrada de texto.
     * @param texto El campo de texto a bloquear.
     */
	public void bloquearEntrada(TextField texto) {
		texto.setEditable(false);
		texto.setStyle("-fx-background-color: #aaaaaa;");
		texto.setText("N/A");
	}
	
	/**
     * Bloquea un campo de fecha.
     * @param fecha El campo de fecha a bloquear.
     */
	public void bloquearEntrada(DatePicker fecha) {
		fecha.setMouseTransparent(true);
		fecha.setFocusTraversable(false);
		fecha.getEditor().setText("N/A");
		fecha.getEditor().setStyle("-fx-background-color: #aaaaaa;");
	}
	
	/**
     * Bloquea un combo box.
     * @param comboBox El combo box a bloquear.
     */
	public void bloquearEntrada(ComboBox comboBox) {
		comboBox.setMouseTransparent(true);
		comboBox.setFocusTraversable(false);
		comboBox.setStyle("-fx-background-color: #aaaaaa;");
		comboBox.setValue("N/A");
	}

	/**
     * Establece la ventana actual para el controlador.
     * @param ventana La ventana actual.
     */
	public void setVentana(Stage ventana) {
		this.ventana=ventana;
	}
	
}
