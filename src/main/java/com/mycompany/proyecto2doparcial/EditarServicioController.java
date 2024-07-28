/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.Servicio;
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
public class EditarServicioController {
    @FXML
    private Label lbTitulo;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtDuracion;
    
    //Metodos
    @FXML
    private void regresar() throws IOException{
        App.setRoot(App.pathFXMLServicios);
    }
    
    @FXML
    private void guardarServicio() throws IOException{
        ArrayList<Servicio> lista = Servicio.cargarServicios(App.pathServicios);
        Servicio s = new Servicio(txtTipo.getText(), Float.parseFloat(txtPrecio.getText()), Float.parseFloat(txtDuracion.getText()), true);
        
        int index = lista.indexOf(s);
        lista.set(index, s);
        
        //Serializacion
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathServicios))){
            out.writeObject(lista);
            out.flush();
            
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Information Dialog");
            alerta.setHeaderText("Resultado de la operación");
            alerta.setContentText("Servicio  editado exitosamente");
            alerta.showAndWait();
            
            App.setRoot(App.pathFXMLServicios);
            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void llenarCampos(Servicio s){
        lbTitulo.setText("Editar: " + s.getTipo());
        txtTipo.setText(s.getTipo());
        String precio = String.valueOf(s.getPrecio());
        txtPrecio.setText(precio);
        String duracion = String.valueOf(s.getDuracion());
        txtDuracion.setText(duracion);
    }
    
    
}
