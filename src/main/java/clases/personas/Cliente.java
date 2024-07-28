/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.personas;

import com.mycompany.proyecto2doparcial.App;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Angello Bravo
 */
public class Cliente extends Persona implements Serializable{
    
    //Atributos
    private String datos;
    
    //Constructor
    public Cliente(String datos, String cedula, String nombre, String telef, String email) {
        super(cedula, nombre, telef, email);
        this.datos = datos;
    }
    
    //getters
    public String getDatos() {
        return datos;
    }
    
    //Setters
    public void setDatos(String datos) {
        this.datos = datos;
    }
    
    
    
    //Metodos
    //Metodo toString que presentara la informacion de los clientes.
    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    public static void serializarCliente(ArrayList<Cliente> lista){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathClientes))){
            out.writeObject(lista);
            out.flush();    
        }
        catch(IOException e){
            //System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    
    //Metodo que cargara los datos de los archivos.
    public static ArrayList<Cliente> cargarClientes(String ruta){
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(ruta))){
            listaClientes = (ArrayList<Cliente>)input.readObject();
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
        
        return listaClientes;
        
    }
   
    
}
