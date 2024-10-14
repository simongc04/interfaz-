package com.example.interfaz

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.OutputStreamWriter
import com.example.interfaz.ui.theme.InterfazTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //guardarArchivo("archivo1.txt","aaa")
        //guardarArchivo("archivo2.txt","aaasdsdsds")
        val usuario = leerArchivo("archivo1.txt")
        val contraseña = leerArchivo("archivo2.txt")
        setContent {
            InterfazTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        message = "usuario:",
                        from = "contraseña:",
                        text1 = usuario,
                        text2 = contraseña,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    fun guardarArchivo(nombreArchivo : String, texto : String){
        val estadoAlmacenamiento = Environment.getExternalStorageState()




        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)
            try {
                val flujoSalida = FileOutputStream(archivo, true)
                val writer = OutputStreamWriter(flujoSalida)
                writer.append(texto)
                writer.close()
                Log.i("DAM2", "Texto añadido en $directorio $nombreArchivo")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("DAM2", "Error al guardar el archivo")
            }
        } else {
            Log.i("DAM2", "No se pudo acceder al almacenamiento externo")
        }
    }
    fun leerArchivo(nombreArchivo : String) : String {
        val estadoAlmacenamiento = Environment.getExternalStorageState()
        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)
            try {
                val reader = archivo.readText()
                Log.i("DAM2", "El archivo contiene $reader")
                return reader
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("DAM2", "Error al leer el archivo")
            }
        }else {
            Log.i("DAM2", "No se pudo acceder al almacenamiento externo")
        }
        return "Error"
    }
    fun borrarContenido(archivoString: String) {
        val directorio = getFilesDir()
        val archivo = File(directorio, archivoString)
        if (!archivo.exists()) {
            Log.i("DAM2","El archivo data no existe.")
        } else {
            val fw: FileWriter
            try {
                fw = FileWriter(archivo)
                fw.flush()
                fw.close()




                Log.i("DAM2","El archivo data fue eliminado.")
            } catch (ex: IOException) {
                Log.i("DAM2","Error al borrar contenido del fichero")
            }
        }
    }




    @Composable
    fun Greeting(
        message: String,
        from: String,
        text1: String,
        text2: String,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier) {
            Row {
                Text(
                    text = message,
                    fontSize = 30.sp,
                    lineHeight = 146.sp,
                )
                Text(
                    text = text1,
                    fontSize = 30.sp,
                    lineHeight = 146.sp,
                )
            }
            Row {
                Text(
                    text = from,
                    fontSize = 30.sp
                )
                Text(
                    text = text2,
                    fontSize = 30.sp,
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
            ) {
                Button(
                    onClick = {},
                ) {
                    Text("editar")
                }
                Spacer(modifier = Modifier.width(16.dp))


                Button(
                    onClick = {
                        borrarContenido("archivo1.txt")
                        borrarContenido("archivo2.txt")
                    },
                ) {
                    Text("Borrar")
                }
            }

        }
    }
}
