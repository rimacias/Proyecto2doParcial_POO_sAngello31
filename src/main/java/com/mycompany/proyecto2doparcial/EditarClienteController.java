/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.personas.Cliente;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Angello Bravo
 */
public class EditarClienteController {
    //Atributos
    @FXML
    private Label lbTitulo;
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
    
    @FXML
    private void regresar() throws IOException{
        App.setRoot(App.pathFXMLClientes);
    }
    
    @FXML
    private void guardarCliente(){
        ArrayList<Cliente> listaClientes = Cliente.cargarClientes(App.pathClientes);
        Cliente cl = new Cliente(txtDatos.getText(), txtCedula.getText(), txtNombre.getText(), txtTelef.getText(), txtEmail.getText());
        
        int index = listaClientes.indexOf(cl);
        listaClientes.set(index, cl);
        
        
        //Serializacion
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathClientes))){
            out.writeObject(listaClientes);
            out.flush();
            
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Information Dialog");
            alerta.setHeaderText("Resultado de la operación");
            alerta.setContentText("Persona: " + txtNombre.getText() + " editada exitosamente");
            alerta.showAndWait();
            
            App.setRoot(App.pathFXMLClientes);
            
        }
        catch(IOException e){
            //System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    
    public void llenarContenido(Cliente cl){
        lbTitulo.setText("Editar Cliente: " + cl.getCedula());
        txtCedula.setEditable(false);
        txtCedula.setText(cl.getCedula());
        txtNombre.setText(cl.getNombre());
        txtTelef.setText(cl.getTelef());
        txtEmail.setText(cl.getEmail());
        txtDatos.setText(cl.getDatos());
    }
}
