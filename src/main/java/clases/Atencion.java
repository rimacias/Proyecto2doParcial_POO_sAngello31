/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import clases.personas.Empleado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Angello Bravo
 */
public class Atencion implements Serializable{
    //Atributos
    private Servicio servicio;
    private Cita cita;
    private int duracion; //Duracion esta expresada en minutos
    private Empleado emp;
    public static ArrayList<Atencion> listaAtenciones = new ArrayList<Atencion>();
    
    //Constructor
    public Atencion(Servicio servicio, int duracion, Empleado emp, Cita cita) {
        this.servicio = servicio;
        this.duracion = duracion;
        this.emp = emp;
        this.cita = cita;
    }
    
    //Getters
    public Servicio getServicio() {
        return servicio;
    }

    public int getDuracion() {
        return duracion;
    }

    public Empleado getEmp() {
        return emp;
    }

    public Cita getCita() {
        return cita;
    }
    
    
    //Setters

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
    
    @Override
    public String toString(){
        return getCita().getCliente().getCedula() + " - " + getCita().getCliente().getNombre() + " - " + getDuracion() + " min - " + getServicio().getTipo() + " | Empleado: " +  getEmp().getNombre();
    }
    
    public static ArrayList<Atencion> cargarAtenciones(String ruta){
        ArrayList<Atencion> lista = new ArrayList<>();
        
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(ruta))){
            lista = (ArrayList<Atencion>) input.readObject();
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        return lista;
    }
    
    
    
}
