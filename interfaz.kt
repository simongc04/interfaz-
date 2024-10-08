package com.example.interfaz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.interfaz.ui.theme.InterfazTheme
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textFromFile1 = Login("archivo1.txt")
        val textFromFile2 = Login("archivo2.txt")

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



    fun Login(fileName: String): String {
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
                    text = text1, // Mostrar el contenido del archivo 1
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
                    text = text2, // Mostrar el contenido del archivo 2
                    fontSize = 30.sp,
                )
            }
        }
    }
}
