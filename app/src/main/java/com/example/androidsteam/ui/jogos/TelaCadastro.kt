package com.example.androidsteam.ui.jogos

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidsteam.data.local.AppDatabase
import com.example.androidsteam.data.local.Usuarios
import com.example.androidsteam.data.local.UsuariosDAO
import com.example.androidsteam.data.repository.JogosRepository
import com.example.androidsteam.data.repository.UsuariosRepository
import com.example.androidsteam.ui.theme.AndroidSteamTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TelaCadastro : ComponentActivity() {
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

@Composable
@Preview
fun PreviewTela5(){
    Tela5(onClickLogin = {})
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Tela5(onClickLogin: () -> Unit){
    Scaffold {
        Surface(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFF292c33))
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Cabecalho2()
                Formulario2()
                InformacoesAdicionais2(onClickLogin)
            }
        }
    }
}

@Composable
private fun Cabecalho2() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)
    ) {
        Text("CADASTRAR", color = Color.White, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
private fun Formulario2(
    viewModel: UsuariosViewModel = viewModel(
        factory = UsuariosViewModelFactory(
            UsuariosRepository(
                AppDatabase.getDatabase(
                    LocalContext.current
                ).usuariosDAO()
            )
        )
    )
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Row(modifier = Modifier.height(30.dp)) {
        Text("NOME DE USUÁRIO STEAM", color = Color.LightGray)
    }
    Row(modifier = Modifier.height(80.dp)) {
        TextField(
            value = uiState.nome,
            onValueChange = { viewModel.onNameChange(it) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF202126),
                unfocusedContainerColor = Color(0xFF202126),
                disabledContainerColor = Color(0xFF202126),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
    }
    Row(modifier = Modifier.height(30.dp)) {
        Text("SENHA", color = Color.LightGray)
    }
    Row(modifier = Modifier.height(80.dp)) {
        TextField(
            value = uiState.senha,
            onValueChange = { viewModel.onPasswordChange(it) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF202126),
                unfocusedContainerColor = Color(0xFF202126),
                disabledContainerColor = Color(0xFF202126),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation()
        )
    }
    Row(modifier = Modifier.height(30.dp)) {
        Text("SENHA", color = Color.LightGray)
    }
    Row(modifier = Modifier.height(80.dp)) {
        TextField(
            value = uiState.senha,
            onValueChange = { viewModel.onPasswordChange(it) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF202126),
                unfocusedContainerColor = Color(0xFF202126),
                disabledContainerColor = Color(0xFF202126),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation()
        )
    }
    Row(modifier = Modifier.height(70.dp)) {
        Button(
            onClick = {
                viewModel.onSalvar()
                Toast.makeText(context, "Cadastro efetuado.", Toast.LENGTH_SHORT).show()
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
            Text("Fazer cadastro")
        }
    }
}

@Composable
private fun InformacoesAdicionais2(onClickLogin: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Preciso de ajuda para fazer o cadastro", color = Color.LightGray)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Já possui uma conta Steam?",
            modifier = Modifier.clickable {
                onClickLogin()
            },
            color = Color.White)
    }
}
