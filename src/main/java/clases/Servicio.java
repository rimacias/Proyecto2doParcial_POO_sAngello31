/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import com.mycompany.proyecto2doparcial.App;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


/**
 *
 * @author Angello Bravo
 */
public class Servicio implements Serializable{
    //Atributos
    private String tipo;
    private float duracion;
    private float precio;
    private boolean estado;
    public static ArrayList<Servicio> listaServicios = new ArrayList<>();
    
    //Constructor
    public Servicio(String tipo, float duracion, float precio, boolean estado) {
        this.tipo = tipo;
        this.duracion = duracion;
        this.precio = precio;
        this.estado = estado;
    }
    
    //Getters
    public String getTipo() {
        return tipo;
    }

    public float getDuracion() {
        return duracion;
    }

    public float getPrecio() {
        return precio;
    }

    public String isEstado() {
        if(estado){
            return "Activo";
        }
        return "Inactivo";
    }
    
    //Setters
    public void setTipo(String tipo) {
        this.tipo = (tipo);
    }

    public void setDuracion(float duracion) {
        this.duracion = (duracion);
    }

    public void setPrecio(float precio) {
        this.precio = (precio);
    }

    public void setEstado(boolean estado) {
        this.estado = (estado);
    }
    
    //Metodos

    @Override
    public boolean equals(Object obj) {
         if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Servicio other = (Servicio) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString(){
        return this.getTipo();
    }
    
    public static void serializarServicios(ArrayList<Servicio> lista){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathServicios))){
        //Serializacion
            out.writeObject(lista);
            out.flush();
            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Servicio> cargarServicios(String ruta){
        ArrayList<Servicio> lista = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(ruta))){
            lista = (ArrayList<Servicio>)input.readObject();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e){
            System.out.println("Clase no encontrada");
        }
        
        return lista;
    }
    
}
