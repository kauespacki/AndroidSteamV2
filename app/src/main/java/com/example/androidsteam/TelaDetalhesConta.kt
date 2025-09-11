package com.example.androidsteam

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidsteam.ui.theme.AndroidSteamTheme

class TelaDetalhesConta : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidSteamTheme {
                Tela4()
            }
        }
    }
}


@Preview (showBackground = true)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Tela4(){
    Scaffold {
        LazyColumn (
            modifier = Modifier
                .padding(it)
                .background(Color(0xFF1c293a)
                )
        ) {
            item {Cabecalho()}
            item {bloco1()}
            item {bloco2()}
            item {Footer()}

        }
    }
}

@Composable
fun bloco1(){
    Column(modifier = Modifier
        .background(Color(0xFF243f5c))
        .fillMaxWidth()
        .padding(5.dp, 20.dp))   {
        Text("Página principal > Conta", color = Color.Gray)
        Text("Conta de fulano", style = MaterialTheme.typography.headlineLarge, color = Color.White)
        Text("ID Steam: 00000000000000000", color = Color.Gray)
    }
}

@Composable
fun bloco2(){
    LazyRow {
        item{
            Row(modifier = Modifier.padding(20.dp)
                .background(color = Color(0xFF1f3954))
            ){
                Text("Detalhes da Conta", modifier = Modifier.padding(10.dp), color = Color(0xFF6fbdec) )
            }



        }

        item{
            Row(modifier = Modifier.padding(20.dp)
                .background(color = Color(0xFF1f3954))
            ){
                Text("Preferências da loja", modifier = Modifier.padding(10.dp), color = Color.White )
            }
        }

        item{
            Row(modifier = Modifier.padding(20.dp)
                .background(Color(0xFF1f3954))
            ){
                Text("Gerenciamento da Família", modifier = Modifier.padding(10.dp), color = Color.White )
            }
        }

        item{
            Row(modifier = Modifier.padding(20.dp)
                .background(Color(0xFF1f3954))
            ){
                Text("Segurança e dispositivos", modifier = Modifier.padding(10.dp), color = Color.White )
            }
        }
        item{
            Row(modifier = Modifier.padding(20.dp)
                .background(Color(0xFF1f3954))
            ){
                Text("Preferências de idioma", modifier = Modifier.padding(10.dp), color = Color.White )
            }
        }
        item{
            Row(modifier = Modifier.padding(20.dp)
                .background(Color(0xFF1f3954))
            ){
                Text("Cookies e navegação", modifier = Modifier.padding(10.dp), color = Color.White )
            }
        }

        item{
            Row(modifier = Modifier.padding(20.dp)
                .background(Color(0xFF1f3954))
            ){
                Text("Configurações de notificação", modifier = Modifier.padding(10.dp), color = Color.White )
            }
        }

        item{
            Row(modifier = Modifier.padding(20.dp)
                .background(Color(0xFF1f3954))
            ){
                Text("Jogos com acesso limitado", modifier = Modifier.padding(10.dp), color = Color.White )
            }
        }


    }

}

@Composable
fun bloco3(){
    LazyColumn {
        item {
            Column(modifier = Modifier.padding(9.dp).background(Color(0xFF16202c))) {

                Text("🛒 Histórico na loja e compras",
                    modifier = Modifier
                        .fillMaxWidth()
                        . background(color = Color(0xFF223d5a))
                        .padding(4.dp)
                    , color = Color.White)
                Column(modifier = Modifier.padding(10.dp).fillMaxWidth() ) {
                    Text("+ adicionar fundos à sua Carteira Steam", color = Color.White, modifier = Modifier.background(Color(0xFF26303c)).padding(4.dp))

                    Spacer(modifier = Modifier.padding(6.dp))

                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth() ) {
                        Text("Saldo da Carteira", color = Color(0xFF7bc1e7))
                        Text("R$ 10,54", style = MaterialTheme.typography.headlineLarge, color = Color(0xFF7bc1e7))
                    }

                    Spacer(modifier = Modifier.padding(7.dp))

                    Box(/*   LINHA   */
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color.White)
                    )

                    Spacer(modifier = Modifier.padding(7.dp))

                    Text("Não há formas de pagamentos associados à conta.", color = Color.White)

                    Spacer(modifier = Modifier.padding(3.dp))

                    Text("Adicionar forma de pagamento à conta", color = Color.White, modifier = Modifier.background(Color(0xFF26303c)).padding(4.dp))

                    Spacer(modifier = Modifier.padding(20.dp))

                    Text("Ver histórico de compras", color = Color.White, modifier = Modifier.background(Color(0xFF26303c)).padding(4.dp))

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text("ver licenças e ativações de códigos de produtos", color = Color.White, modifier = Modifier.background(Color(0xFF26303c)).padding(4.dp))

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text("Atualizar preferências da loja", color = Color.White, modifier = Modifier.background(Color(0xFF26303c)).padding(4.dp))

                    Spacer(modifier = Modifier.padding(5.dp))

                    Row {
                        Text("País: ", color = Color.Gray, style = TextStyle(fontSize = 12.sp))
                        Text("Brasil", color = Color.White, style = TextStyle(fontSize = 12.sp))
                    }

                    Text("Caso tenha se mudado de país, há opção de atualizar a Carteira", color = Color.Gray, style = TextStyle(fontSize = 12.sp))

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text("Atualizar país da loja", color = Color.White, modifier = Modifier.background(Color(0xFF26303c)).padding(4.dp))

                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
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
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = Color.LightGray,
                            contentDescription = ""
                        )
                    },
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
                modifier = Modifier.fillMaxSize()
            ) {
                Text("MENU", color = Color.LightGray)
                Text("LISTA DESEJOS", color = Color.LightGray)
                Text("CARTEIRA (R$ 0,00)", color = Color.LightGray)
            }
        }
    }
}

@Composable
private fun Footer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
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

                },
            tint = Color.White

        )

    }
}