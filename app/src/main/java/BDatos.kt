package com.example.clubdeportivo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDatos(context: Context) : SQLiteOpenHelper(context,
    BD, null, DATABASE_VERSION) {

    companion object {
        private const val BD = "BaseDatos"
        private const val DATABASE_VERSION = 2

        // Nombre de la tabla y columnas
        private const val TABLE_USUARIOS = "Usuarios"
        private const val COLUMN_ID = "IDusuario"
        private const val COLUMN_NOMBRE = "nombreUsuario"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_TIPO = "tipo"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_USUARIOS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NOMBRE TEXT, "
                + "$COLUMN_PASSWORD TEXT, "
                + "$COLUMN_TIPO TEXT)")
        db?.execSQL(createTable)

        val adminValues = ContentValues().apply {
            put(COLUMN_NOMBRE, "admin")
            put(COLUMN_PASSWORD, "admin")
            put(COLUMN_TIPO, "1")
        }
        if (db != null) {
            db.insert(TABLE_USUARIOS, null, adminValues)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    // Métodos CRUD
    fun addUsuario(usuario: Usuario): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NOMBRE, usuario.nombreUsuario)
            put(COLUMN_PASSWORD, usuario.password)
            put(COLUMN_TIPO, usuario.tipo)
        }
        return db.insert(TABLE_USUARIOS, null, contentValues)
    }

    fun getAllUsuarios(): List<Usuario> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USUARIOS", null)
        val usuarios = mutableListOf<Usuario>()

        if (cursor.moveToFirst()) {
            do {
                val usuario = Usuario(
                    idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    nombreUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                    password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                    tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
                )
                usuarios.add(usuario)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return usuarios
    }

    fun updateUsuario(usuario: Usuario): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NOMBRE, usuario.nombreUsuario)
            put(COLUMN_PASSWORD, usuario.password)
            put(COLUMN_TIPO, usuario.tipo)
        }
        return db.update(TABLE_USUARIOS, contentValues, "$COLUMN_ID = ?", arrayOf(usuario.idUsuario.toString()))
    }

    fun deleteUsuario(idUsuario: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_USUARIOS, "$COLUMN_ID = ?", arrayOf(idUsuario.toString()))
    }
    fun agregarUsuariosEjemplo() {
        val usuario1 = Usuario(idUsuario = 1, nombreUsuario = "admin", password = "admin", tipo = "1")
        addUsuario(usuario1)

        // Dos registros con tipo = 2
        val usuario2 = Usuario(idUsuario = 2, nombreUsuario = "11223344", password = "1234", tipo = "2")
        val usuario3 = Usuario(idUsuario = 3, nombreUsuario = "12345678", password = "5678", tipo = "2")
        addUsuario(usuario2)
        addUsuario(usuario3)

        // Dos registros con tipo = 3
        val usuario4 = Usuario(idUsuario = 4, nombreUsuario = "87654321", password = "8778", tipo = "3")
        val usuario5 = Usuario(idUsuario = 5, nombreUsuario = "44332211", password = "9876", tipo = "3")
        addUsuario(usuario4)
        addUsuario(usuario5)
    }
}
