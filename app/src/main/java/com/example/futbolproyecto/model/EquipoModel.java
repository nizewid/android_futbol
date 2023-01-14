package com.example.futbolproyecto.model;

public class EquipoModel {
    private int id_equipo;
    private String nombre;
    private String patrocinador;
    private String presidente;
    private String localidad;
    private boolean activo;

    public EquipoModel(int id_equipo, String nombre, String patrocinador, String presidente, String localidad, boolean activo) {
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.patrocinador = patrocinador;
        this.presidente = presidente;
        this.localidad = localidad;
        this.activo = activo;
    }

    public EquipoModel(String nombre, String patrocinador, String presidente, String localidad, boolean activo) {
        this.nombre = nombre;
        this.patrocinador = patrocinador;
        this.presidente = presidente;
        this.localidad = localidad;
        this.activo = activo;
    }

    @Override
    public String toString() {
        String equipo = "";
        equipo = id_equipo+"\n"+nombre.toUpperCase()+" |Patrocinador: "+patrocinador+"\n|Presidente: "+presidente+" |Localidad: "+localidad+"\n|Equipo Activo->: "+activo;
        return equipo;
    }

    public EquipoModel() {
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
