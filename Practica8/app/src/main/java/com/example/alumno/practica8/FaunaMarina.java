package com.example.alumno.practica8;

public class FaunaMarina {
    int ref;
    String nombre;
    String latin;
    String tamano;
    String habitat;

    public FaunaMarina(int ref, String nombre, String latin, String tamano, String habitat) {
        this.ref = ref;
        this.nombre = nombre;
        this.latin = latin;
        this.tamano = tamano;
        this.habitat = habitat;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
