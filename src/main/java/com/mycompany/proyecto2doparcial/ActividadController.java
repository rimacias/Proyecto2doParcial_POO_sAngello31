/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2doparcial;
 
import clases.Actividad;
import clases.Atencion;
import clases.Cita;
import clases.personas.Cliente;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Angello Bravo
 */
public class ActividadController implements Initializable{
    
    //Atributos
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbTiempo;
    @FXML
    private Label lbNumero;
    @FXML
    private GridPane cuadricula;
    
            
    //Atributos no FXML
    //int[] arrNumeros = new int[20];
    ArrayList<Integer> arrNumeros;
    public static boolean iniciar;
    public static int hora=0, min=0, seg=0;
    int fallas;
    int aciertos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         arrNumeros = generarArreglo(1, 40);
         iniciar = true;
         fallas = 0;
         aciertos = 0;
         int aleatorio = (int) (Math.random()*arrNumeros.size());
         lbNumero.setText(Integer.toString(arrNumeros.get(aleatorio)));

         getChildrenFromGridPane();
        
        Reloj rj = new Reloj(lbTiempo);
        rj.setDaemon(true);
        rj.start();
         
    }
    
    //Metodo que generara el arreglo con numeros aleatorios sin repeticiones
    public ArrayList<Integer> generarArreglo(int min, int max){
        ArrayList<Integer> x = new ArrayList<>();
        while(x.size() != 20){
            int numero = (int) (Math.random()*max)+min;
            if(!x.contains(numero)){
                x.add(numero);
            }
        }
        return x;
    }
    
    public void llenarEspacios(Atencion t){
        lbNombre.setText("Paciente: " +t.getCita().getCliente().toString());
        lbTiempo.setText("0:0:0");
        
    }
    
    //Metodo que obtendra los nodos del grid pane y agregara los valores aleatorios a los botones junto con el evento correspondiente
    @FXML
   private void getChildrenFromGridPane(){
       int contador = 0;
       for(Node node: cuadricula.getChildren()){
           Button bt = (Button)node;
           bt.setText(Integer.toString(arrNumeros.get(contador)));
           contador++;
           
           //Agregar el evento
           bt.setOnAction(e -> {
               int valorBt = Integer.valueOf(bt.getText());
               int valorLabel = Integer.valueOf(lbNumero.getText());
               
               if(valorBt == valorLabel){
                   //Eliminar valor ya seleccionado
                   int index = arrNumeros.indexOf(valorLabel);
                   arrNumeros.remove(index);
                   
                   //Cambiar el color del boton
                   bt.setStyle("-fx-background-color: #23F751;");
                   aciertos++;
                   
                   //Cambiar el numero del Label
                   try{
                       int aleatorio = (int) (Math.random()*arrNumeros.size());
                       lbNumero.setText(Integer.toString(arrNumeros.get(aleatorio)));
                   }
                   catch(IndexOutOfBoundsException ex){
                       System.out.println(ex.getMessage());
                   }
               }
               
               else{
                   fallas++;
                   System.out.println(fallas);
               }
               
               if(arrNumeros.isEmpty()){
                   iniciar = false;
                    System.out.println("Juego acabado");

                    //Guardar Informacion
                    Cliente cl = RegistrarAtencionController.citaParaAtencion.getCliente();
                    LocalDate fecha = RegistrarAtencionController.citaParaAtencion.getFecha();
                    Actividad ac = new Actividad(cl, lbTiempo.getText(), fecha, "Bingo",fallas, aciertos);

                    //Guardar en Texto
                    String clString = cl.getDatos() + ";" + cl.getCedula() + ";" + cl.getNombre() + ";" + cl.getTelef() +";" + cl.getEmail();
                    String line = clString + ";" + lbTiempo.getText() +";"+ fecha + ";" + "Bingo" +";" + fallas + ";" + aciertos + "\n";

                    try(FileWriter fl = new FileWriter(App.pathActividades, true)){
                        fl.write(line);
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                    
                    //Alerta
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Information Dialog");
                    alerta.setHeaderText("Felicitaciones");
                    alerta.setContentText("Haz completado la actividad en un tiempo de: " + lbTiempo.getText());
                    alerta.showAndWait();
                    
                    ArrayList<Cita> lista = Cita.cargarCitas(App.pathCitas);

                    int index = lista.indexOf(RegistrarAtencionController.citaParaAtencion);
                    lista.get(index).setEstado(false);
                    Cita.serializarCita(lista);
                        

                    try{
                        App.setRoot(App.pathFXMLCitas);
                    }
                    catch(IOException ex ){
                        System.out.println(ex.getMessage());
                    }
               }
           });
       }
   }
   
    //Clase interna que creara un hilo para el cronometro
    private class Reloj extends Thread{
        @FXML
        Label e;
        Reloj(Label e){
            this.e = e;
        }
        @Override
        public void run(){
            try{
                int x = 0;
                while(iniciar){
                    ejecutarReloj(x);
                    Thread.sleep(1000);
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        
        public void ejecutarReloj(int x){
            seg++;
            if(seg >59){
                seg =0;
                min++;
            }
            if(min>59){
                seg=0;
                min=0;
                hora++;
            }
            
            //System.out.println(hora + ":" + min + ":" + seg);
            Platform.runLater(() -> e.setText(hora +":" + min + ":" + seg));
        }
        
    }
    
    
    
    
    
    
}
