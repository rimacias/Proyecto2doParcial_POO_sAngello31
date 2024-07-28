/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import clases.personas.Cliente;
import com.mycompany.proyecto2doparcial.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Angello Bravo
 */
public class Actividad {
    //Atributos
    Cliente cl;
    String tiempo;
    LocalDate fecha;
    String actividad;
    int errores;
    int aciertos;

    public Actividad(Cliente cl, String tiempo, LocalDate fecha, String actividad ,int errores, int aciertos) {
        this.cl = cl;
        this.tiempo = tiempo;
        this.errores = errores;
        this.aciertos = aciertos;
        this.fecha = fecha;
        this.actividad = actividad;
    }

    public Cliente getCl() {
        return cl;
    }

    public String getTiempo() {
        return tiempo;
    }

    public int getErrores() {
        return errores;
    }

    public int getAciertos() {
        return aciertos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getActividad() {
        return actividad;
    }

    public void setCl(Cliente cl) {
        this.cl = cl;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public void setErrores(int errores) {
        this.errores = errores;
    }
    
    //Metodos
    public static ArrayList<Actividad> cargarActividades(){
        ArrayList<Actividad> lista = new ArrayList<>();
        
        try(BufferedReader bf = new BufferedReader(new FileReader(App.pathActividades))){
            String line;
            while((line = bf.readLine()) != null){
                String [] datos = line.split(";");
                Cliente cl = new Cliente(datos[0], datos[1], datos[2], datos[3], datos[4]);
                LocalDate fecha = LocalDate.parse(datos[6]);
                Actividad a = new Actividad(cl, datos[5],fecha , datos[7], Integer.valueOf(datos[8]), Integer.valueOf(datos[9]));
                lista.add(a);
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        
        
        return lista;
    }
    
    
    
}
