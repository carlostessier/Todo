package es.uem.todo.model;

import java.io.Serializable;

/**
 * Created by carlosfernandez on 12/03/15.
 */
public class Task implements Serializable {

    private int id;
    private String nombre;
    private String fecha;
    private int prioridad;

    public Task(int id, int prioridad, String nombre, String fecha ) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.prioridad = prioridad;
    }

    public Task(int prioridad, String nombre, String fecha) {
        this(-1, prioridad,nombre, fecha);
    }

    public Task(){
        this(-1,0,"","");
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return nombre;
    }
}
