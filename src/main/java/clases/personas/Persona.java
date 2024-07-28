/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases.personas;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Angello Bravo
 */
public abstract class Persona implements Serializable{
    //Atributos
    private String cedula;
    private String nombre;
    private String telef;
    private String email;
    
    //Constructor
    public Persona(String cedula, String nombre, String telef, String email){
        this.cedula = cedula;
        this.nombre = nombre;
        this.telef = telef;
        this.email = email;
    }
    
    //getters
    public String getCedula(){
        return cedula;
    }
    public String getNombre(){
        return nombre;
    }
    public String getTelef(){
        return telef;
    }
    public String getEmail(){
        return email;
    }
    
    //Setters

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString(){
        return getNombre();
    }

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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }
    
    
}
