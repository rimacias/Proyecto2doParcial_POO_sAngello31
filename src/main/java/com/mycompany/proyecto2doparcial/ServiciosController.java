/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.Servicio;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author Angello Bravo
 */

public class ServiciosController implements Initializable{

    //Atributos FXML
    @FXML
    TableView<Servicio> tablaServicios;
  
    @FXML
    private TableColumn<Servicio, String> colServicios;
    @FXML
    private TableColumn<Servicio, Float> colDuracion;
    @FXML
    private TableColumn<Servicio, Float> colPrecio;
    @FXML
    private TableColumn<Servicio, Boolean> colEstado;
    
    //Atributos Java
    public ObservableList<Servicio> lista; //Lista que presentara en la tabla 
    
    //Metodos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTableView();
    }

    @FXML
    private void cambiarMain() throws IOException{
        App.setRoot("main");
    }
    
    @FXML
    private void cambiarAgregarServicio() throws IOException{
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("Servicios/agregarServicio.fxml"));
        AgregarServicioController ct = new AgregarServicioController();
        
        fxml.setController(ct);
        Parent root = (Parent)fxml.load();
        App.changeRoot(root);
    }
    
    @FXML
    private void editarServicio() throws IOException{
        Servicio s = (Servicio)tablaServicios.getSelectionModel().getSelectedItem();
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("Servicios/agregarServicio.fxml"));
        EditarServicioController ct = new EditarServicioController();
        
        fxml.setController(ct);
        BorderPane root = (BorderPane) fxml.load();
        ct.llenarCampos(s);
        App.changeRoot(root);
        
    }
    
    @FXML
    private void eliminarServicio() throws IOException{
        ArrayList<Servicio> lista = Servicio.cargarServicios(App.pathServicios);
        Servicio s = (Servicio) tablaServicios.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setContentText("Seguro que deseas eliminar a: " + s.getTipo());
        Optional <ButtonType> bt = alerta.showAndWait();
        
        if(bt.get() == ButtonType.OK){
            lista.remove(s);
            
            Servicio.serializarServicios(lista);
                
            Alert alerta1 = new Alert(Alert.AlertType.INFORMATION);
            alerta1.setContentText("Se ha eliminado correctamente");
            alerta1.showAndWait();
            
            
            llenarTableView();
            
        }
    }
    
    @FXML
    private void llenarTableView(){
        
        tablaServicios.getItems().clear();
        
        lista = FXCollections.observableArrayList();
        ArrayList<Servicio> listaServicio = Servicio.cargarServicios(App.pathServicios);
        for(Servicio s: listaServicio){
            lista.add(s);
        }
        
        //Crea la Tabla
        colServicios.setCellValueFactory(new PropertyValueFactory<Servicio, String>("tipo"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<Servicio, Float>("duracion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Servicio,Float>("precio"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Servicio,Boolean>("estado"));
//        
        tablaServicios.setItems(lista);
    }
}
