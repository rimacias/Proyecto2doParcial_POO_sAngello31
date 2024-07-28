package com.mycompany.proyecto2doparcial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    
    public static String pathServicios = "archivos/servicios.ser";
    public static String pathEmpleados = "archivos/empleados.ser";
    public static String pathClientes = "archivos/clientes.ser";
    public static String pathCitas = "archivos/citas.ser";
    public static String pathActividades = "archivos/actividades.txt";
    public static String pathAtenciones = "archivos/atenciones.ser";
    
    public static String pathFXMLClientes = "Clientes/clientes";
    public static String pathFXMLEmpleados = "Empleados/empleados";
    public static String pathFXMLServicios = "Servicios/servicios";
    public static String pathFXMLCitas = "Citas/citas";
    public static String pathFXMLAtenciones = "Atenciones/atenciones";
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 640, 480);
        //scene.getStylesheets().add(App.class.getResource("css/Styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Sistema Terapeutico ESPOL");
        stage.setMinHeight(600);
        stage.setMinWidth(600);
        stage.setMaxHeight(600);
        stage.setMaxWidth(600);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void changeRoot(Parent noodRoot){
        scene.setRoot(noodRoot);
    }

}