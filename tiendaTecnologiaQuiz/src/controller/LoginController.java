package controller;

import java.sql.Connection;

import application.Main;
import data.UsuarioDAO;
import data.DBConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private TextField txtUsuario;
    
    @FXML
    private Button BtoEnter, RegistUser;
    
    private Connection connection = DBConnection.getInstance().getConnection();
    private UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
    
    @FXML
    public void IngresoUser(ActionEvent event) {
    	String nickname1 = txtUsuario.getText().trim();
    	String password1 = txtContraseña.getText().trim();
    	
    	if (!validarCampos(nickname1, password1)) {
    		return;
    	}
    	
    	try {
    		if (usuarioDAO.authenticate1(nickname1)){
    			mostrarAlerta("Error", "Valores duplicados", "Esta identificación ya está registrada.");
    		}
    		Usuario user = new Usuario(nickname1, password1);
    		
    	}
    }
    
    public boolean validarCampos(String nickname1, String password1) {
    	if (nickname1.isEmpty() || password1.isEmpty()) {
    		mostrarAlerta("Error", "Campos vacíos", "Todos los campos son obligatorios.");
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @FXML
    void iniciarSesion(ActionEvent event) {
    	 Main.loadView("/view/RegistroProductos.fxml");
    }

    private void mostrarAlerta(String titulo, String cabecera, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}