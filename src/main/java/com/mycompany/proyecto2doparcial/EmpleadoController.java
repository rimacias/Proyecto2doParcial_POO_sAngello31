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
public class EmpleadoController implements Initializable{
    
    //Atributos FXML
    @FXML
    private TableView<Empleado> tablaEmpleados;
    @FXML
    private TableColumn<Empleado, String> colCedula;
    @FXML
    private TableColumn<Empleado, String> colNombre;
    @FXML
    private TableColumn<Empleado, String> colTelef;
    @FXML
    private TableColumn<Empleado, String> colEmail;
    @FXML
    private TableColumn<Empleado, Boolean> colEstado;
    
    //Atributos Java
    public ObservableList<Empleado> lista;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTableView();
        
    }
    
    @FXML
    private void cambiarMain() throws IOException{
        App.setRoot("main");
    }
    
    //Ventana para agregar o editar Empleados.
    @FXML
    private void agregarEmpleado() throws IOException{
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("Empleados/agregarEmpleado.fxml"));
        AgregarEmpleadoController ct = new AgregarEmpleadoController();
        
        fxml.setController(ct);
        Parent root = (Parent) fxml.load();
        App.changeRoot(root);
    }
    
    @FXML
    private void editarEmpleado() throws IOException{
        Empleado emp = (Empleado)tablaEmpleados.getSelectionModel().getSelectedItem();
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("Empleados/agregarEmpleado.fxml"));
        EditarEmpleadoController ct = new EditarEmpleadoController();
        
        fxml.setController(ct);
        BorderPane root = (BorderPane) fxml.load();
        ct.llenarContenido(emp);
        
        App.changeRoot(root);
    }
    
    //Aun se debe refrescar automaticamente para notar el cambio
    @FXML
    private void eliminarEmpleado(){
        ArrayList<Empleado> lista = Empleado.cargarEmpleados(App.pathEmpleados);
        Empleado emp = (Empleado) tablaEmpleados.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setContentText("Seguro que deseas eliminar a: " + emp.getNombre());
        Optional <ButtonType> bt = alerta.showAndWait();
        
        if(bt.get() == ButtonType.OK){
            lista.remove(emp);
            
            try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmpleados))){
                out.writeObject(lista);
                out.flush();
            }
            
            catch(IOException e){
                System.out.println(e.getMessage());
            }
            
            Alert alerta1 = new Alert(Alert.AlertType.INFORMATION);
            alerta1.setContentText("Se ha eliminado correctamente");
            alerta1.showAndWait();
            
            llenarTableView();
            
        }
        
    }
    @FXML
    private void llenarTableView(){
        
        tablaEmpleados.getItems().clear();
        
        lista = FXCollections.observableArrayList();
        ArrayList<Empleado> cargaEmpleados = Empleado.cargarEmpleados(App.pathEmpleados);
        for(Empleado em:cargaEmpleados){
            lista.add(em);
        }
        
        //Crear Tabla
        colEstado.setCellValueFactory(new PropertyValueFactory<Empleado, Boolean>("estado"));
        colCedula.setCellValueFactory(new PropertyValueFactory<Empleado, String>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        colTelef.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telef"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Empleado, String>("email"));
        
        tablaEmpleados.setItems(lista);
    } 
    
    
}
