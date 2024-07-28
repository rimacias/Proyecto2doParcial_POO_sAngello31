/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.personas.Cliente;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Angello Bravo
 */
public class ClienteController implements Initializable{
    
    @FXML
    TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colCedula;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTelef;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TableColumn<Cliente, String> colDatos;
    
    @FXML
    private Button btAgregar;
    
    public ObservableList<Cliente> lista;
    
    
    //Metodos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelef.setCellValueFactory(new PropertyValueFactory<>("telef"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDatos.setCellValueFactory(new PropertyValueFactory<>("datos"));
        
        tablaClientes.getItems().setAll(Cliente.cargarClientes(App.pathClientes));
        
    }
    
    @FXML
    private void agregarView() throws IOException{
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("Clientes/agregarCliente.fxml"));
        AgregarClienteController ct = new AgregarClienteController();
        
        fxml.setController(ct);
        Parent root = (Parent) fxml.load();
        
        App.changeRoot(root);
    }
    
    @FXML
    private void cambiarMain() throws IOException{
        App.setRoot("main");
    }
    
    @FXML
    private void editarCliente() throws IOException{
        //Obtiene un cliente seleccionado de la tableview
        Cliente cl = (Cliente)tablaClientes.getSelectionModel().getSelectedItem();
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("Clientes/agregarCliente.fxml"));
        EditarClienteController ct = new EditarClienteController();
        
        fxml.setController(ct);
        BorderPane root = (BorderPane) fxml.load();
        ct.llenarContenido(cl);
        
        App.changeRoot(root);
    }

    
}
