package com.example.androidsteam.ui.jogos

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidsteam.data.local.AppDatabase
import com.example.androidsteam.data.local.Jogos
import com.example.androidsteam.data.repository.JogosRepository

@Composable
fun TelaAdminPanel(
    viewModelJogos: JogosViewModel = viewModel(
        factory = JogosViewModelFactory(
            JogosRepository(
                AppDatabase.getDatabase(
                    LocalContext.current
                ).jogosDAO()
            )
        )
    ),
    viewModelUsuarios: JogosViewModel = viewModel(
        factory = JogosViewModelFactory(
            JogosRepository(
                AppDatabase.getDatabase(
                    LocalContext.current
                ).jogosDAO()
            )
        )
    )
) {
    val uiState by viewModelJogos.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var showJogoDialog by remember { mutableStateOf(false) }
    var jogoParaEditar by remember { mutableStateOf<Jogos?>(null) }

    // Layout principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1c293a))
            .padding(16.dp)
    ) {
        // Cabeçalho
        Text("🛠️ Painel do Administrador", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))

        // Seção de Gerenciamento de Jogos
        Text("🎮 Gerenciar Jogos", color = Color(0xFF6fbdec))
        Spacer(modifier = Modifier.height(8.dp))

        // Botão para adicionar jogo
        Button(
            onClick = {
                jogoParaEditar = null
                showJogoDialog = true
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF216cad))
        ) {
            Text("➕ Adicionar Jogo", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Lista de Jogos
        LazyColumn {
            items(uiState.listaDeJogos) { jogo ->
                AdminCard(
                    title = jogo.nome,
                    descricao = "R$ ${jogo.preco} - ID: ${jogo.id}",
                    onEdit = {
                        jogoParaEditar = jogo
                        showJogoDialog = true
                    },
                    onDelete = {
//                        viewModel.onDeletar(jogo)
                        Toast.makeText(context, "Jogo removido.", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }

    // Exibir o diálogo para adicionar ou editar jogo
    if (showJogoDialog) {
        JogoDialog(
            jogo = jogoParaEditar,
            onDismiss = { showJogoDialog = false },
            onSave = { jogo ->
                viewModelJogos.onSalvar()
                Toast.makeText(context, if (jogoParaEditar == null) "Jogo adicionado!" else "Jogo atualizado!", Toast.LENGTH_SHORT).show()
                showJogoDialog = false
            }
        )
    }
}

@Composable
fun AdminCard(
    title: String,
    descricao: String,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF26303c))
            .padding(12.dp)
    ) {
        Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium)
        Text(descricao, color = Color.Gray, style = MaterialTheme.typography.bodySmall)

        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Text("✏️ Editar", color = Color(0xFF6fbdec), modifier = Modifier
                .clickable { onEdit() }
                .padding(8.dp))
            Text("🗑️ Deletar", color = Color.Red, modifier = Modifier
                .clickable { onDelete() }
                .padding(8.dp))
        }
    }
}

@Composable
fun JogoDialog(jogo: Jogos?, onDismiss: () -> Unit, onSave: (Jogos) -> Unit) {
    var nome by remember { mutableStateOf(jogo?.nome ?: "") }
    var preco by remember { mutableStateOf(jogo?.preco ?: "") }
    var imagem by remember { mutableStateOf(jogo?.imagem ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                if (nome.isNotBlank() && preco.isNotBlank() && imagem.isNotBlank()) {
                    onSave(Jogos(id = jogo?.id ?: 0, nome = nome, preco = preco, imagem = imagem))
                }
            }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        },
        title = { Text(if (jogo == null) "Adicionar Jogo" else "Editar Jogo") },
        text = {
            Column {
                Text("Nome", color = Color.Gray)
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    singleLine = true,
                    colors = fieldColors()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Preço (R$)", color = Color.Gray)
                TextField(
                    value = preco,
                    onValueChange = { preco = it },
                    singleLine = true,
                    colors = fieldColors()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("URL da Imagem", color = Color.Gray)
                TextField(
                    value = imagem,
                    onValueChange = { imagem = it },
                    singleLine = true,
                    colors = fieldColors()
                )
            }
        },
        containerColor = Color(0xFF1c293a),
        titleContentColor = Color.White,
        textContentColor = Color.White
    )
}

@Composable
fun fieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color(0xFF202126),
    unfocusedContainerColor = Color(0xFF202126),
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White
)
