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
import java.util.ArrayList;

/**
 *
 * @author Angello Bravo
 */
public class Empleado extends Persona {
    
    //Atributos
    private boolean estado;
    public static ArrayList <Empleado> listaEmpleados = new ArrayList<Empleado>(); //Lista Estatica de Empleados
    
    //Constructor
    public Empleado(boolean estado, String cedula, String nombre, String telef, String email) {
        super(cedula, nombre, telef, email);
        this.estado = estado;
    }
    
    //Getters
    public String isEstado() {
        if(estado){
            return "Activo";
        }
        return "Inactivo";
    }
    
    //Setters
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    //Metodos
    
    @Override
    public String toString(){
        return this.getNombre();
    }
    
    public static void serializarEmpleados(ArrayList<Empleado> lista){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmpleados))){
            out.writeObject(lista);
            out.flush();

        }
        catch(IOException e){
            //System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    
    //Cargar arraylist de empleados
    public static ArrayList<Empleado> cargarEmpleados(String ruta){
        ArrayList<Empleado> lista = new ArrayList<>();
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(ruta))){
            lista = (ArrayList<Empleado>)input.readObject();
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
