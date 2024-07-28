/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;

import clases.personas.Empleado;
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
public class EditarEmpleadoController {
    
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
    
    //Metodos
    @FXML
    private void regresar() throws IOException{
        App.setRoot(App.pathFXMLEmpleados);
    }
    
    public void llenarContenido(Empleado emp){
        lbTitulo.setText("Editar Empleado: " + emp.getCedula());
        txtCedula.setEditable(false);
        txtCedula.setText(emp.getCedula());
        txtNombre.setText(emp.getNombre());
        txtTelef.setText(emp.getTelef());
        txtEmail.setText(emp.getEmail());
    }
    
    @FXML
    private void guardarEmpleado() throws IOException{
        ArrayList<Empleado> listaEmpleados = Empleado.cargarEmpleados(App.pathEmpleados);
        Empleado emp = new Empleado(true, txtCedula.getText(), txtNombre.getText(), txtTelef.getText(), txtEmail.getText());
        int index = listaEmpleados.indexOf(emp);
        listaEmpleados.set(index, emp);
        
        //Serializacion
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmpleados))){
            out.writeObject(listaEmpleados);
            out.flush();
            
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Information Dialog");
            alerta.setHeaderText("Resultado de la operación");
            alerta.setContentText("Persona: " + txtCedula.getText() + " editada exitosamente");
            alerta.showAndWait();
            
            App.setRoot(App.pathFXMLEmpleados);
            
        }
        catch(IOException e){
            //System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}

