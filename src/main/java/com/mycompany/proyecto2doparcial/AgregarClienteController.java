/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.personas.Cliente;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Angello Bravo
 */
public class AgregarClienteController implements Initializable, Serializable{
    
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelef;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDatos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(""); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBod
        
    }
    
    @FXML
    private void regresar() throws IOException{
        App.setRoot(App.pathFXMLClientes);
    }
    
    
    @FXML
    private void guardarCliente(){
        ArrayList<Cliente> listaClientes = Cliente.cargarClientes(App.pathClientes);
        
        try{
            
            if(txtDatos.getText().equals("")){
                txtDatos.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,0,0) , 5, 0.0 , 0 , 1 );");
            }
            if(txtCedula.getText().equals("")){
                txtCedula.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,0,0) , 5, 0.0 , 0 , 1 );");
            }
            if(txtNombre.getText().equals("")){
                txtNombre.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,0,0) , 5, 0.0 , 0 , 1 );");
            }
            if(txtTelef.getText().equals("")){
                txtTelef.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,0,0) , 5, 0.0 , 0 , 1 );");
            }
            if(txtEmail.getText().equals("")){
                txtEmail.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,0,0) , 5, 0.0 , 0 , 1 );");
            }
            
            if(!txtDatos.getText().equals("") && !txtCedula.getText().equals("") && !txtNombre.getText().equals("") && !txtTelef.getText().equals("") && !txtEmail.getText().equals("")){
                Cliente cl = new Cliente(txtDatos.getText(), txtCedula.getText(), txtNombre.getText(), txtTelef.getText(), txtEmail.getText());
                listaClientes.add(cl);

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Information Dialog");
                alerta.setHeaderText("Resultado de la operación");
                alerta.setContentText("Persona: " + txtNombre.getText() + " agregada exitosamente");
                alerta.showAndWait();
                
                Cliente.serializarCliente(listaClientes);
                App.setRoot(App.pathFXMLClientes);
            }
            
            
            
        }
        
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        catch(Exception e){
            System.out.println("No se pudo agregar el cliente");
        }
        
        finally{
            Cliente.serializarCliente(listaClientes);
        }
        
    }
    
}
