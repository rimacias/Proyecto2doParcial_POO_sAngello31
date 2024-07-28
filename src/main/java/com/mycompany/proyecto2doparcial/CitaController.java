package com.mycompany.proyecto2doparcial;


import clases.Atencion;
import clases.Cita;
import clases.Servicio;
import clases.personas.Cliente;
import clases.personas.Empleado;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Angello Bravo
 */
public class CitaController implements Initializable{
    //Atributos
    @FXML
    private TableView<Cita> tablaCitas;
    @FXML
    private TableColumn<Cita, Cliente> colNombre;
    @FXML
    private TableColumn<Cita, Empleado> colEmpleado;
    @FXML
    private TableColumn<Cita, Servicio> colServicio;
    @FXML
    private TableColumn<Cita, LocalDate> colFecha;
    @FXML
    private TableColumn<Cita, LocalTime> colHora;
    @FXML
    private TableColumn<Cita, Boolean> colEstado;
    @FXML
    private TextField txtFiltrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colServicio.setCellValueFactory(new PropertyValueFactory<>("servicio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        tablaCitas.getItems().setAll(Cita.cargarCitas(App.pathCitas));
    }
    
    //Cambiara a la ventana para registrar la atencion de la cita seleccionada.-
    @FXML
    private void registrarAtencion() throws IOException{
        
        Cita c = (Cita)tablaCitas.getSelectionModel().getSelectedItem();
        
        if(c.isEstado().equals("Pendiente")){
            FXMLLoader fxmlloader = new FXMLLoader(App.class.getResource("Citas/registrarAtencion.fxml"));
            RegistrarAtencionController ct = new RegistrarAtencionController();

            RegistrarAtencionController.citaParaAtencion = c;
            fxmlloader.setController(ct);
            BorderPane root = (BorderPane) fxmlloader.load();

            ct.llenarCampos(c);
            App.changeRoot(root);
        }
        
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("No se puede generar una Atencion de una cita ya cerrada");
            alerta.showAndWait();
        }
    }
    
    @FXML
    private void filtrar() throws IOException{
        tablaCitas.getItems().clear();
        String cedula = txtFiltrar.getText();
        ArrayList<Cita> lista = Cita.cargarCitas(App.pathCitas);
        ArrayList<Cita> listaFiltrada = new ArrayList<>();
        for(Cita c: lista){
            String fecha = c.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if(c.getCliente().getCedula().equals(cedula)){
                listaFiltrada.add(c);
            }
            else if(fecha.equals(cedula)){
                listaFiltrada.add(c);
            }
            
        }
        Cita.serializarCita(lista);
        tablaCitas.getItems().setAll(listaFiltrada);
    }
    
    @FXML
    private void eliminarCita() throws IOException{
        ArrayList<Cita> lista = Cita.cargarCitas(App.pathCitas);
        Cita c = (Cita)tablaCitas.getSelectionModel().getSelectedItem();
        
        
        if(!c.isEstado().equals("Pendiente")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("La cita no se puede eliminar, debido a que ya fue llevada a cabo");
            a.showAndWait();
        }
        
        else{
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setContentText("Seguro que deseas eliminar");
            Optional <ButtonType> bt = alerta.showAndWait();
        
            if(bt.get() == ButtonType.OK){
                lista.remove(c);

                Cita.serializarCita(lista);

                Alert alerta1 = new Alert(Alert.AlertType.INFORMATION);
                alerta1.setContentText("Se ha eliminado correctamente");
                alerta1.showAndWait();

                tablaCitas.getItems().clear();
                tablaCitas.getItems().setAll(lista);

            }
        
        }
    }
    
    @FXML
    private void cambiarRegistrarAtencion() throws IOException{
        App.setRoot("Citas/registrarAtencion");
    }
    
    @FXML
    private void cambiarAgregar() throws IOException{
        App.setRoot("Citas/agregarCita");
    }
    
    @FXML
    private void cambiarConsultar() throws IOException{
        App.setRoot("Actividad/consultarActividades");
    }
    
    
    @FXML
    private void cambiarMain() throws IOException{
        App.setRoot("main");
    }
}
