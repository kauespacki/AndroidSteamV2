package com.example.androidsteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidsteam.ui.theme.AndroidSteamTheme

class TelaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidSteamTheme {
            }
        }
    }
}

@Composable
@Preview
fun PreviewTela2(){
    Tela2({}, {})
}

@Composable
fun Tela2(onClickInicio: () -> Unit, onClickDetalhes: () -> Unit){
    Scaffold {
        Surface(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.background(
                    Color(0xFF292c33)
                ).fillMaxSize()
            ) {
                Cabecalho()
                Nome()
                EditarPerfil(onClickDetalhes)
                InformacoesAdicionais()
                Spacer(modifier = Modifier.weight(1f))
                Footer(onClickInicio)
            }
        }
    }
}

@Composable
private fun Cabecalho() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF202126))
            .height(50.dp), horizontalArrangement = Arrangement.Center,
        Alignment.CenterVertically
    ) {
        Text("COMUNIDADE", color = Color.White, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
private fun Nome() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "",
            modifier = Modifier.size(120.dp)
        )

        Column {
            Text("fulano", style = MaterialTheme.typography.headlineLarge, color = Color.White)
            Text("Paraná, Brasil \uD83C\uDDE7\uD83C\uDDF7", color = Color.White)
        }
    }
}

@Composable
private fun EditarPerfil(onClickDetalhes: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 5.dp)
            .background(Color(0xFF2b3445))
            .height(30.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text("Editar Perfil", color = Color.White)
    }
    Button(
        onClick = {
            onClickDetalhes()
        },
        colors = ButtonDefaults.buttonColors(Color(0xFF2b3445)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 5.dp)
            .background(Color(0xFF2b3445))
            .height(35.dp)
    ) {
        Text("Ver detalhes da conta", color = Color.White)
    }
}

@Composable
private fun InformacoesAdicionais() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 20.dp)
            .height(70.dp)
            .background(Color(0xFF161b1e)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Nível 26", style = MaterialTheme.typography.headlineLarge, color = Color.White)
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Column {
            Text("5 anos de serviço", color = Color.White)
        }

    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 5.dp)
            .height(60.dp)
            .background(Color(0xFF161b1e)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("On-line", color = Color.White, style = MaterialTheme.typography.headlineSmall)
    }

    Row {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp)
                    .height(50.dp)
                    .background(Color(0xFF161b1e)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Grupos: 2", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp)
                    .height(50.dp)
                    .background(Color(0xFF161b1e)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Jogos: 104", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp)
                    .height(50.dp)
                    .background(Color(0xFF161b1e)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Capturas de Tela: 9", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 5.dp)
                    .height(50.dp)
                    .background(Color(0xFF161b1e)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Itens da Oficina", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.padding())

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 5.dp, 15.dp)
                    .height(50.dp)
                    .background(Color(0xFF161b1e)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Amigos: 88", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 5.dp, 15.dp)
                    .height(50.dp)
                    .background(Color(0xFF161b1e)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Inventário", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 5.dp, 15.dp)
                    .height(50.dp)
                    .background(Color(0xFF161b1e)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Análise", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 5.dp, 15.dp)
                    .height(50.dp)
                    .background(Color(0xFF161b1e)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Artes", color = Color.White)
            }

        }
    }
}

@Composable
private fun Footer(onClickInicio: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFF202126)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "",
            modifier = Modifier
                .size(35.dp)
                .clickable {
                    onClickInicio()
                },
            tint = Color.White

        )

        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color.White

        )


        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color.White

        )
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color.White

        )

        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color.White

        )

    }
}