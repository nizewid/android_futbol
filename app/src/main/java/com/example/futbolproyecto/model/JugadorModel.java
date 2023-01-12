package com.example.futbolproyecto.model;

public class JugadorModel {
    private int id;
    private String nombre;
    private int edad;
    private String picture;
    private boolean lesionado;

    public JugadorModel(int id, String nombre, int edad, String picture, boolean lesionado) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.picture = picture;
        this.lesionado = lesionado;
    }

    public JugadorModel() {
    }

    @Override
    public String toString() {
        return "JugadorModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", picture='" + picture + '\'' +
                ", lesionado=" + lesionado +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isLesionado() {
        return lesionado;
    }

    public void setLesionado(boolean lesionado) {
        this.lesionado = lesionado;
    }
}
