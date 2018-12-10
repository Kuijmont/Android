package com.example.alumno.mibiblioteca2018;

public class Libro {
    /*
     * ATRIBUTOS
     */
    int id;
    String titulo;
    String autor;
    int portada;
    int fav;

    /*
     * CONSTRUCTOR
     */
    public Libro(int id, String titulo, String autor, int portada, int fav) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.portada = portada;
        this.fav = fav;
    }

    /*
     * MÉTODOS
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPortada() {
        return portada;
    }

    public void setPortada(int portada) {
        this.portada = portada;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }
}
