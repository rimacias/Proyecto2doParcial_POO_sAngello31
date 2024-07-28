/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.Atencion;
import clases.Cita;
import clases.Servicio;
import clases.personas.Empleado;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Angello Bravo
 */
public class AtencionController implements Initializable{
    
    @FXML
    private TableView<Atencion> tablaAtenciones;
    @FXML
    private TableColumn<Atencion, Servicio> colServicio;
    @FXML
    private TableColumn<Atencion, Cita> colCita;
    @FXML
    private TableColumn<Atencion, Integer> colDuracion;
    @FXML
    private TableColumn<Atencion, Empleado> colEmpleado;
    @FXML
    private Button btFiltrar;
    @FXML
    private TextField txtFiltrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colServicio.setCellValueFactory(new PropertyValueFactory<>("servicio"));
        colCita.setCellValueFactory(new PropertyValueFactory<>("cita"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<>("emp"));
        
        tablaAtenciones.getItems().setAll(Atencion.cargarAtenciones(App.pathAtenciones));
    }
    
    //Metodo que ayudara a filtrar por fecha o cedula las atenciones
    @FXML
    private void filtrar() throws IOException{
        tablaAtenciones.getItems().clear();
        String cedula = txtFiltrar.getText();
        ArrayList<Atencion> lista = Atencion.cargarAtenciones(App.pathAtenciones);
        ArrayList<Atencion> listaFiltrada = new ArrayList<>();
        
        for(Atencion at: lista){
            String fecha = at.getCita().getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if(at.getCita().getCliente().getCedula().equals(cedula)){
                listaFiltrada.add(at);
            }
            
            else if(at.getEmp().getCedula().equals(cedula)){
                listaFiltrada.add(at);
            }
            else if(fecha.equals(cedula)){
                listaFiltrada.add(at);
            }
            
        }
        
        //Se agrega la lista Filtrada a la TableView
        tablaAtenciones.getItems().setAll(listaFiltrada);
    }
    
    
    
    @FXML
    private void regresar() throws IOException{
        App.setRoot("main");
    }

}
