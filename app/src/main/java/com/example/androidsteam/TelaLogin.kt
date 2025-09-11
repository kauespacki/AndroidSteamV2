package com.example.androidsteam

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androidsteam.ui.theme.AndroidSteamTheme

class TelaLogin : ComponentActivity() {
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
fun Tela1(navController: NavHostController){
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
                            navController.navigate("telaInicial")
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