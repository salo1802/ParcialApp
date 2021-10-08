package com.example.parcialapp.model;

public class Grupos {
    private String nombre;
    private int cantidad,x,y;
    private int[] color;

    public Grupos(String nombre, int cantidad, int x, int y, int[] color) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Grupos() { }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }
}
