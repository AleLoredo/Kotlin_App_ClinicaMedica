package com.example.clubdeportivo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,
  //  DATABASE_NAME, null, DATABASE_VERSION) {

//}

class BDatos(context: Context) : SQLiteOpenHelper(context,
    BD, null, DATABASE_VERSION) {

    //inicio tabla usuarios
    companion object {
        private const val BD = "BaseDatos"
        private const val DATABASE_VERSION = 7

        // Nombre de la tabla y columnas
        private const val TABLE_USUARIOS = "Usuarios"
        private const val COLUMN_ID = "IDusuario"
        private const val COLUMN_NOMBRE = "nombreUsuario"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_TIPO = "tipo"

        //agregado pra registro socios y no socios
        private const val TABLE_SOCIOS = "Socios"
        private const val TABLE_NO_SOCIOS = "NoSocios"
        private const val COLUMN_DOCUMENTO = "documento"
        private const val COLUMN_CNOMBRE = "nombre"
        private const val COLUMN_APELLIDO = "apellido"
        private const val COLUMN_FECHANACIMIENTO = "fechanacimiento"
        private const val COLUMN_DOMICILIO = "domicilio"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_APTOFISICO = "aptoFisico"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_USUARIOS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NOMBRE TEXT, "
                + "$COLUMN_PASSWORD TEXT, "
                + "$COLUMN_TIPO TEXT)")
        //val createSociosTable = "CREATE TABLE $TABLE_SOCIOS ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_AGE INTEGER)"

        db?.execSQL(createTable)

        val createSociosTable = """
        CREATE TABLE $TABLE_SOCIOS (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
            $COLUMN_DOCUMENTO TEXT, 
            $COLUMN_CNOMBRE TEXT, 
            $COLUMN_APELLIDO TEXT, 
            $COLUMN_FECHANACIMIENTO TEXT, 
            $COLUMN_DOMICILIO TEXT, 
            $COLUMN_EMAIL TEXT, 
            $COLUMN_APTOFISICO TEXT
        )
        """.trimIndent()

        db?.execSQL(createSociosTable)

        //val createNoSociosTable = "CREATE TABLE $TABLE_NO_SOCIOS ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_AGE INTEGER)"

        val createNoSociosTable = """
        CREATE TABLE $TABLE_NO_SOCIOS (
            $COLUMN_ID INTEGER PRIMARY KEY, 
            $COLUMN_DOCUMENTO TEXT, 
            $COLUMN_CNOMBRE TEXT, 
            $COLUMN_APELLIDO TEXT, 
            $COLUMN_FECHANACIMIENTO TEXT, 
            $COLUMN_DOMICILIO TEXT, 
            $COLUMN_EMAIL TEXT 
        )
        """.trimIndent()

        db?.execSQL(createNoSociosTable)


        /*
        val adminValues = ContentValues().apply {
            put(COLUMN_NOMBRE, "admin")
            put(COLUMN_PASSWORD, "admin")
            put(COLUMN_TIPO, "1")
        }
        if (db != null) {
            db.insert(TABLE_USUARIOS, null, adminValues)
        }
*/
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_SOCIOS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NO_SOCIOS")
        onCreate(db)
    }

    //se agrega para registro de socios y no socios
    fun insertSocio(
        documento: String,
        nombre: String,
        apellido: String,
        fechanacimiento: String,
        domicilio: String,
        email: String,
        aptoFisico: String,
    ) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_DOCUMENTO, documento)
            put(COLUMN_CNOMBRE, nombre)
            put(COLUMN_APELLIDO, apellido)
            put(COLUMN_FECHANACIMIENTO, fechanacimiento)
            put(COLUMN_DOMICILIO, domicilio)
            put(COLUMN_EMAIL, email)
            put(COLUMN_APTOFISICO, aptoFisico)
        }
        db.insert(TABLE_SOCIOS, null, contentValues)
    }


    //fun insertNoSocio(name: String, age: Int) {
      //  val db = writableDatabase
        //val contentValues = ContentValues().apply {
          //  put(COLUMN_NAME, name)
           // put(COLUMN_AGE, age)
       // }
   // }
    fun getSocioByDocumento(documento: String): Socio? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_SOCIOS, // Tabla
            null, // Columnas (null para todas)
            "$COLUMN_DOCUMENTO = ?", // Selección
            arrayOf(documento), // Argumentos de selección
            null, // Agrupamiento
            null, // Filtro de grupo
            null // Orden
        )

        var socio: Socio? = null
        cursor.use {
            if (it.moveToFirst()) {
                socio = Socio(

                    documento = it.getString(it.getColumnIndexOrThrow(COLUMN_DOCUMENTO)),
                    cnombre = it.getString(it.getColumnIndexOrThrow(COLUMN_CNOMBRE)),
                    apellido = it.getString(it.getColumnIndexOrThrow(COLUMN_APELLIDO)),
                    domicilio = it.getString(it.getColumnIndexOrThrow(COLUMN_DOMICILIO)),
                    aptofisico = it.getString(it.getColumnIndexOrThrow(COLUMN_APTOFISICO)),
                    fechanacimiento = it.getString(it.getColumnIndexOrThrow(COLUMN_FECHANACIMIENTO)),
                    email = it.getString(it.getColumnIndexOrThrow(COLUMN_EMAIL)),

                    // Agrega aquí otros atributos del socio si es necesario
                )
            }
        }

        return socio
    }

    fun insertNoSocio(
        documento: String,
        nombre: String,
        apellido: String,
        fechanacimiento: String,
        domicilio: String,
        email: String,
    ) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_DOCUMENTO, documento)
            put(COLUMN_CNOMBRE, nombre)
            put(COLUMN_APELLIDO, apellido)
            put(COLUMN_FECHANACIMIENTO, fechanacimiento)
            put(COLUMN_DOMICILIO, domicilio)
            put(COLUMN_EMAIL, email)
        }
        db.insert(TABLE_NO_SOCIOS, null, contentValues)
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

    fun agregarUsuariosEjemplo(): Boolean {
        return try {
            val db = writableDatabase

            val usuarios = listOf(
                Usuario(1, "admin", "admin", "1"),
                Usuario(2, "11223344", "1234", "2"),
                Usuario(3, "12345678", "5678", "3")
             )

            for (usuario in usuarios) {
                val values = ContentValues().apply {
                    put(COLUMN_NOMBRE, usuario.nombreUsuario)
                    put(COLUMN_PASSWORD, usuario.password)
                    put(COLUMN_TIPO, usuario.tipo)
                }
                db.insert(TABLE_USUARIOS, null, values)
            }

            db.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    //data class Usuario(val idUsuario: Int, val nombreUsuario: String, val nombreUsuario: String, val password: String, val tipo: String)

   data class Socio(val documento: String, val cnombre: String, val apellido: String, val fechanacimiento: String, val domicilio: String, val email: String, val aptofisico: String)

    fun agregarSociosEjemplo(): Boolean {
        return try {
            val db = writableDatabase

            val socios = listOf(
                Socio("12345", "Pepe1", "argento", "123", "sarasa 123", "sss@sss.com", "1"),
                Socio("123456", "Pepe2", "argento", "1234", "sarasa 1234", "sss@sss.com", "1"),
                Socio("1234567", "Pepe3", "argento", "12345", "sarasa 12345", "sss@sss.com", "1"),
                )

            for (socio in socios) {
                val values = ContentValues().apply {
                    put(COLUMN_DOCUMENTO, socio.documento)
                    put(COLUMN_CNOMBRE, socio.cnombre)
                    put(COLUMN_APELLIDO, socio.apellido)
                    put(COLUMN_FECHANACIMIENTO, socio.fechanacimiento)
                    put(COLUMN_DOMICILIO, socio.domicilio)
                    put(COLUMN_EMAIL, socio.email)
                    put(COLUMN_APTOFISICO, socio.aptofisico)
                }
                db.insert(TABLE_SOCIOS, null, values)
            }

            db.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}
