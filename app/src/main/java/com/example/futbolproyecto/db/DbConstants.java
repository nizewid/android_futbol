package com.example.futbolproyecto.db;

public class DbConstants {

    public static final String DB_NOMBRE="FUTBOLAPP";

    //Tabla Equipos
    public static final String TABLA_EQUIPOS = "EQUIPOS";
    public static final String COLUMN_ID_EQUIPO = "ID_EQUIPO";
    public static final String COLUMN_NOMBRE_EQUIPO = "NOMBRE_EQUIPO";
    public static final String COLUMN_PATROCINADOR = "PATROCINADOR";
    public static final String COLUMN_PRESIDENTE = "PRESIDENTE";
    public static final String COLUMN_LOCALIDAD = "LOCALIDAD";
    public static final String COLUMN_EQUIPO_ACTIVO = "ACTIVO";

    //Tabla Jugadores
    public static final String TABLA_JUGADORES = "JUGADORES";
    public static final String COLUMN_ID_JUGADOR = "ID_JUGADOR";
    public static final String COLUMN_NOMBRE_JUGADOR = "NOMBRE_JUGADOR";
    public static final String COLUMN_EDAD = "EDAD";
    public static final String COLUMN_PICTURE = "PICTURE";
    public static final String COLUMN_LESIONADO = "LESIONADO";

    public static final String queryEquipos = "CREATE TABLE " + TABLA_EQUIPOS + " (" + COLUMN_ID_EQUIPO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOMBRE_EQUIPO + " TEXT, " + COLUMN_PATROCINADOR + " TEXT, " + COLUMN_PRESIDENTE + " TEXT, " + COLUMN_LOCALIDAD + " TEXT, " + COLUMN_EQUIPO_ACTIVO + " BOOL)";
    public static final String queryJugadores = "CREATE TABLE " + TABLA_JUGADORES + " (" + COLUMN_ID_JUGADOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOMBRE_JUGADOR + " TEXT, " + COLUMN_EDAD + " INTEGER, " + COLUMN_PICTURE + " TEXT," + COLUMN_LESIONADO + " BOOL, "+COLUMN_ID_EQUIPO+" INTEGER, FOREIGN KEY("+COLUMN_ID_EQUIPO+") REFERENCES EQUIPOS(ID_EQUIPOS))";
}
