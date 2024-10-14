
package com.example.interfaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import com.example.interfaz.ui.theme.InterfazTheme
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textFromFile1 = login("archivo1.txt")
        val textFromFile2 = login("archivo2.txt")

        setContent {
            InterfazTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        message = "usuario:",
                        from = "contraseña:",
                        text1 = textFromFile1,
                        text2 = textFromFile2,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    fun borrar() {

    }

    fun editar(){

    }

    fun login(fileName: String): String {
        return assets.open(fileName).bufferedReader().use { it.readText() }
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
                    onClick = {},
                ) {
                    Text("Borrar")
                }
            }


        }
    }
}
