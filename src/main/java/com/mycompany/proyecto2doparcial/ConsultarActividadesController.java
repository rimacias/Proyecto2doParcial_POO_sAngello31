/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.Actividad;
import clases.personas.Cliente;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Angello Bravo
 */
public class ConsultarActividadesController implements Initializable{

    
    //Atributos
    @FXML
    private TableView<Actividad> tablaActividades;
    @FXML
    private TableColumn<Actividad, Cliente> colCliente;
    @FXML
    private TableColumn<Actividad, String> colActividad;
    @FXML
    private TableColumn<Actividad, LocalDate> colFecha;
    @FXML
    private TableColumn<Actividad, Integer> colAciertos;
    @FXML
    private TableColumn<Actividad, Integer> colFallas;
    @FXML
    private TableColumn<Actividad, String> colDuracion;
    @FXML
    private TextField txtFiltrar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cl"));
        colActividad.setCellValueFactory(new PropertyValueFactory<>("actividad"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colAciertos.setCellValueFactory(new PropertyValueFactory<>("aciertos"));
        colFallas.setCellValueFactory(new PropertyValueFactory<>("errores"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        
        tablaActividades.getItems().setAll(Actividad.cargarActividades());
    }
    
    @FXML
    private void filtrar() throws IOException{
        tablaActividades.getItems().clear();
        String cedula = txtFiltrar.getText();
        
        ArrayList<Actividad> lista = Actividad.cargarActividades();
        ArrayList<Actividad> listaFiltrada = new ArrayList<>();
        
        for(Actividad at: lista){
            if(at.getCl().getCedula().equals(cedula)){
                listaFiltrada.add(at);
            }
        }
        
        
        tablaActividades.getItems().setAll(listaFiltrada);
        
        
    }
    
    @FXML
    private void regresar() throws IOException{
        App.setRoot(App.pathFXMLCitas);
    }
}
