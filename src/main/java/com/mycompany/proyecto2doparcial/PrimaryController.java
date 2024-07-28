package com.mycompany.proyecto2doparcial;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable{
    
    @FXML
    private ImageView iv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            InputStream input = App.class.getResource("img/logo.png").openStream();
            Image img = new Image(input);
            iv.setImage(img);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void cambiarServicios() throws IOException {
        App.setRoot(App.pathFXMLServicios);
    }
    
    @FXML
    private void cambiarEmpleados() throws IOException{
        App.setRoot(App.pathFXMLEmpleados);
    }
    
    @FXML
    private void cambiarClientes() throws IOException{
        App.setRoot(App.pathFXMLClientes);
    }
    
    @FXML
    private void cambiarCitas() throws IOException{
        App.setRoot(App.pathFXMLCitas);
    }
    
    @FXML
    private void cambiarAtenciones() throws IOException{
        App.setRoot(App.pathFXMLAtenciones);
    }
    
}
