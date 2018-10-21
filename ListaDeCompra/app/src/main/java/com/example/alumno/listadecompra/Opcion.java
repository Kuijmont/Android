package com.example.alumno.listadecompra;

public class Opcion {
    private String producto;
    private boolean comprado;


    public Opcion(String producto, boolean comprado){
        this.producto = producto;
        this.comprado = comprado;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }
}
