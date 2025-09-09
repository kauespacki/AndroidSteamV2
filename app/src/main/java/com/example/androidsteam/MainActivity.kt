package com.example.androidsteam

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color.parseColor

import android.graphics.Paint.Style
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androidsteam.ui.theme.AndroidSteamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidSteamTheme {
                AppNavigation()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaLogin(navController: NavHostController){
    var usuario by remember {
        mutableStateOf("")
    }
    var senha by remember {
        mutableStateOf("")
    }
    Scaffold {
        Surface(modifier = Modifier.padding(it)) {

            Column(
                modifier = Modifier
                    .background(color = Color(0xFF292c33))
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 20.dp)
                ) {
                    Text("INICIAR SESSÃO", color = Color.White, style = MaterialTheme.typography.titleLarge)
                }
                Row(modifier = Modifier.height(30.dp)) {
                    Text("NOME DE USUÁRIO STEAM", color = Color.LightGray)
                }
                Row(modifier = Modifier.height(80.dp)) {
                    TextField(
                        value = usuario,
                        onValueChange = {usuario = it},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF202126),
                            unfocusedContainerColor = Color(0xFF202126),
                            disabledContainerColor = Color(0xFF202126)
                        )
                    )
                }
                Row(modifier = Modifier.height(30.dp)) {
                    Text("SENHA", color = Color.LightGray)
                }
                Row(modifier = Modifier.height(80.dp)) {
                    TextField(
                        value = senha,
                        onValueChange = {senha = it},
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF202126),
                            unfocusedContainerColor = Color(0xFF202126),
                            disabledContainerColor = Color(0xFF202126)
                        )
                    )
                }
                Row(modifier = Modifier.height(70.dp)) {
                    Button(
                        onClick = {
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = ButtonColors(
                            containerColor = Color(0xFF216cad),
                            contentColor = Color.White,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Color.White
                        ),

                        ) {
                        Text("Iniciar sessão")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().height(30.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Preciso de ajuda para iniciar a sessão", color = Color.LightGray)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Não tem uma conta Steam?", color = Color.White)
                }
            }
        }
    }
}