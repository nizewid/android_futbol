package com.example.futbolproyecto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.futbolproyecto.db.DatabaseOpenHelper;
import com.example.futbolproyecto.db.DbConstants;
import com.example.futbolproyecto.interfaces.EquiposDAO;
import com.example.futbolproyecto.model.EquipoModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DAOEquipoImpl extends DatabaseOpenHelper implements EquiposDAO {

    public DAOEquipoImpl(@Nullable Context context) {
        super(context);
    }

    @Override
    public boolean registrarEquipo(@NotNull EquipoModel equipo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbConstants.COLUMN_NOMBRE_EQUIPO,equipo.getNombre());
        cv.put(DbConstants.COLUMN_PATROCINADOR,equipo.getPatrocinador());
        cv.put(DbConstants.COLUMN_PRESIDENTE,equipo.getPresidente());
        cv.put(DbConstants.COLUMN_LOCALIDAD,equipo.getLocalidad());
        cv.put(DbConstants.COLUMN_EQUIPO_ACTIVO,equipo.isActivo());

        long insert = db.insert(DbConstants.TABLA_EQUIPOS, "", cv);
        if (insert==-1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean eliminarEquipo(EquipoModel equipo){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+DbConstants.TABLA_EQUIPOS+" where "+ DbConstants.COLUMN_ID_EQUIPO+" ="+ equipo.getId_equipo();
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void actualizarEquipo(EquipoModel equipo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valoresNuevos = new ContentValues();
        valoresNuevos.put("nombre",equipo.getNombre());
        valoresNuevos.put("patrocinador",equipo.getPatrocinador());
        valoresNuevos.put("presidente",equipo.getPresidente());
        valoresNuevos.put("localidad",equipo.getLocalidad());
        valoresNuevos.put("activo",equipo.isActivo());

        String[] argumentosParaActualizar = {String.valueOf(equipo.getId_equipo())};

        db.update(DbConstants.TABLA_EQUIPOS, valoresNuevos, DbConstants.COLUMN_ID_EQUIPO+" = ?",argumentosParaActualizar);


    }

    @Override
    public ArrayList<EquipoModel> listarEquipos(){
        ArrayList<EquipoModel> list= new ArrayList<>();
        String query = "SELECT * FROM "+DbConstants.TABLA_EQUIPOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                int id_equipo = cursor.getInt(0);
                String nombreEquipo = cursor.getString(1);
                String patrocinador = cursor.getString(2);
                String presidente = cursor.getString(3);
                String localidad = cursor.getString(4);
                // turnary Operator LEER MAS
                boolean activo = cursor.getInt(5)== 1? true:false;

                list.add(new EquipoModel(id_equipo,nombreEquipo,patrocinador,presidente,localidad,activo));

            }while(cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();
        return list;
    }
}
