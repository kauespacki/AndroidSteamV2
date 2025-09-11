package com.example.androidsteam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidsteam.ui.theme.AndroidSteamTheme

class TelaInicial : ComponentActivity() {
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
fun PreviewTela3(){
    Tela3 {}
}

@Composable
fun Tela3(onClickPerfil: () -> Unit){
    Scaffold {
        LazyColumn (
            modifier = Modifier
                .padding(it)
                .background(Color(0xFF1d405c)
            )
        ) {
            item {Cabecalho()}
            item {Bloco1()}
            item {Bloco2()}
            item {Bloco3()}
            item{Footer(onClickPerfil)}
        }
    }
}

@Composable
private fun Cabecalho() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF202126))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                TextField(
                    value = "Busca",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            tint = Color.LightGray,
                            contentDescription = ""
                        )},
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = Color.LightGray,
                            contentDescription = ""
                        )},
                    label = {
                        Text("STEAM", color = Color.LightGray)
                    },
                    modifier = Modifier.height(20.dp).fillMaxWidth(0.85f),
                    onValueChange = {},
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color(0xFF292c33))
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    tint = Color.LightGray,
                    contentDescription = ""
                )
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    tint = Color.LightGray,
                    contentDescription = ""
                )
            }

            Row (
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize().padding(5.dp)
            ) {
                Text("MENU", color = Color.LightGray)
                Text("LISTA DESEJOS", color = Color.LightGray)
                Text("CARTEIRA (R$ 0,00)", color = Color.LightGray)
            }
        }
    }
}

@Composable
private fun Bloco1() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(322.dp)
    ) {
        Column {

            Text("DESTAQUES E RECOMENDADOS", color = Color.White, modifier = Modifier.padding(0.dp,30.dp,0.dp,0.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0A1821),
                ),
                shape = RectangleShape,
                modifier = Modifier.padding(4.dp)

            ) {
                Box(modifier = Modifier.height(190.dp).fillMaxWidth()
                    .background(Color.Red)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.banner1),
                        contentDescription = "imagem local",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("The Last of Us Part I", style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text("-50% R$124,95", style = MaterialTheme.typography.titleSmall, color = Color(0XFFbce549))
            }

        }
    }
}

@Composable
private fun Bloco2() {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .background(Color.Cyan)
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner2),
            contentDescription = "imagem local",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun Bloco3() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(Color(0xFF1d405c))
    ) {
        Column {

            Text("DESCONTOS E EVENTOS", color = Color.White , modifier = Modifier.padding(0.dp,30.dp,0.dp,0.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0A1821),
                ),
                shape = RectangleShape,
                modifier = Modifier.padding(4.dp)

            ){

                Box(modifier = Modifier.height(160.dp).fillMaxWidth()
                    .background(Color.Red)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.banner3),
                        contentDescription = "imagem local",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("OFERTA DO MEIO DA SEMANA", style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text("Oferta válida até 3 de set. às 14:00", color = Color.LightGray)
                Text("-25% R$31,49", style = MaterialTheme.typography.titleLarge, color = Color(0XFFbce549))
            }


        }
    }
}

@Composable
private fun Footer(onClickPerfil: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFF202126)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Icon(imageVector = Icons.Default.Home,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color.White

        )

        Icon(imageVector = Icons.Default.DateRange,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color.White

        )


        Icon(imageVector = Icons.Default.Lock,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color.White

        )
        Icon(imageVector = Icons.Default.Notifications,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color.White

        )
        Icon(imageVector = Icons.Default.Menu,
            contentDescription = "",
            modifier = Modifier.size(35.dp)
                .clickable {
                    onClickPerfil()

                },
            tint = Color.White

        )

    }
}