/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.Servicio;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
public class AgregarServicioController implements Initializable, Serializable{
    
    //Atributos
    @FXML 
    private TextField txtTipo;
    @FXML
    private TextField txtDuracion;
    @FXML
    private TextField txtPrecio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(""); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @FXML
    private void regresar() throws IOException{
        App.setRoot(App.pathFXMLServicios);
    }
    
    @FXML
    private void guardarServicio(){
        ArrayList<Servicio> listaServicios = Servicio.cargarServicios(App.pathServicios);
        
        try{
            if(txtTipo.getText().equals("")){
                txtTipo.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,0,0) , 5, 0.0 , 0 , 1 );");
            }
            if(txtDuracion.getText().equals("")){
                txtDuracion.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,0,0) , 5, 0.0 , 0 , 1 );");
            }
            if(txtPrecio.getText().equals("")){
                txtPrecio.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(255,0,0) , 5, 0.0 , 0 , 1 );");
            }
            
            if(!txtTipo.getText().equals("") && !txtDuracion.getText().equals("") && !txtPrecio.getText().equals("")){
        
                Servicio ser = new Servicio(txtTipo.getText(), Float.parseFloat(txtDuracion.getText()), Float.parseFloat(txtPrecio.getText()), true);
                listaServicios.add(ser);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Information Dialog");
                alerta.setHeaderText("Resultado de la operación");
                alerta.setContentText("Servicio: " + txtTipo.getText() +  " agregada exitosamente");
                alerta.showAndWait();
                Servicio.serializarServicios(listaServicios);
                
                App.setRoot(App.pathFXMLServicios);
            }
        }
        catch(NumberFormatException e){
            System.out.println(e.getMessage());
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setContentText("Error al registrar el nuevo servicio");
            alerta.showAndWait();
        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        finally{
            Servicio.serializarServicios(listaServicios);
        }
        
    }
    
}
