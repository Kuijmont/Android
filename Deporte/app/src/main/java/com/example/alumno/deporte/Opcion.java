package com.example.alumno.deporte;

public class Opcion {

    private String nombre;
    private int icono;
    private boolean checkbox;

    //Constructor
    public Opcion(String nombre, int icono, boolean checkbox) {
        this.nombre = nombre;
        this.icono = icono;
        this.checkbox = checkbox;
    }

    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
}
