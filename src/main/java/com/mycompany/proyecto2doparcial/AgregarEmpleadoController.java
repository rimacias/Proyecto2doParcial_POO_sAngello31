/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.personas.Empleado;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
public class AgregarEmpleadoController implements Initializable{
    
    //Atributos
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelef;
    @FXML
    private TextField txtEmail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(""); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Metodos
    @FXML
    private void regresar() throws IOException{
        App.setRoot(App.pathFXMLEmpleados);
    }
    
    @FXML
    private void guardarEmpleado(){
        ArrayList<Empleado> listaEmpleados = Empleado.cargarEmpleados(App.pathEmpleados);
        
        try{
            
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
            
            if(!txtCedula.getText().equals("") && !txtNombre.getText().equals("") && !txtTelef.getText().equals("") && !txtEmail.getText().equals("")){
            
                Empleado emp = new Empleado(true, txtCedula.getText(), txtNombre.getText(), txtTelef.getText(), txtEmail.getText());
                listaEmpleados.add(emp);

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Information Dialog");
                alerta.setHeaderText("Resultado de la operación");
                alerta.setContentText("Persona: " + txtNombre.getText() + " agregada exitosamente");
                alerta.showAndWait();
                
                Empleado.serializarEmpleados(listaEmpleados);
                App.setRoot(App.pathFXMLEmpleados);
            }
        }
        
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        finally{
            Empleado.serializarEmpleados(listaEmpleados);
        }
    }
}
