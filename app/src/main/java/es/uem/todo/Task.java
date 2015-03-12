package es.uem.todo;

import java.io.Serializable;

/**
 * Created by carlosfernandez on 12/03/15.
 */
public class Task implements Serializable {

    private String nombre;
    private String fecha;
    private int prioridad;

    public Task(int prioridad, String nombre, String fecha) {
        this.prioridad = prioridad;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Task(){
        this(0,"","");
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
