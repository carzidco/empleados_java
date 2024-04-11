package com.empleados.empleados;

/**
 *
 * @author Johel Gomez
 * 
 */
import java.util.ArrayList;

public class Empleado {
    String cedula;
    String nombre;
    ArrayList<Double> salarios;
    
    public Empleado(String nombre, String cedula){
        this.nombre = nombre;
        this.cedula = cedula;
        this.salarios = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public ArrayList<Double> getSalarios() {
        return salarios;
    }

    public void agregarSalario(Double salario){
        salarios.add(salario);
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
