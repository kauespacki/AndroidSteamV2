package com.example.androidsteam

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidsteam.ui.theme.AndroidSteamTheme
import kotlinx.coroutines.launch

@Composable
fun TelaAdminPanel() {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val usuariosDao = db.usuariosDAO()
    val jogosDao = db.jogosDAO()
    val scope = rememberCoroutineScope()

    var usuarios by remember { mutableStateOf(emptyList<Usuarios>()) }
    var jogos by remember { mutableStateOf(emptyList<Jogos>()) }

    var showUsuarioDialog by remember { mutableStateOf(false) }
    var usuarioParaEditar by remember { mutableStateOf<Usuarios?>(null) }

    var showJogoDialog by remember { mutableStateOf(false) }
    var jogoParaEditar by remember { mutableStateOf<Jogos?>(null) }

    LaunchedEffect(Unit) {
        usuarios = usuariosDao.buscarTodos()
        jogos = jogosDao.buscarTodos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1c293a))
            .padding(16.dp)
    ) {
        Text("🛠️ Painel do Administrador", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))

        // ==== USUÁRIOS ====
        Text("👤 Gerenciar Usuários", color = Color(0xFF6fbdec))
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                usuarioParaEditar = null
                showUsuarioDialog = true
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF216cad))
        ) {
            Text("➕ Adicionar Usuário", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(usuarios.size) { index ->
                val usuario = usuarios[index]
                AdminCard(
                    title = usuario.nome,
                    descricao = "ID: ${usuario.id}",
                    onEdit = {
                        usuarioParaEditar = usuario
                        showUsuarioDialog = true
                    },
                    onDelete = {
                        scope.launch {
                            usuariosDao.deletar(usuario)
                            usuarios = usuariosDao.buscarTodos()
                            Toast.makeText(context, "Usuário removido.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ==== JOGOS ====
        Text("🎮 Gerenciar Jogos", color = Color(0xFF6fbdec))
        Spacer(modifier = Modifier.height(8.dp))

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

        LazyColumn {
            items(jogos.size) { index ->
                val jogo = jogos[index]
                AdminCard(
                    title = jogo.nome,
                    descricao = "R$ ${jogo.preco} - ID: ${jogo.id}",
                    onEdit = {
                        jogoParaEditar = jogo
                        showJogoDialog = true
                    },
                    onDelete = {
                        scope.launch {
                            jogosDao.deletar(jogo)
                            jogos = jogosDao.buscarTodos()
                            Toast.makeText(context, "Jogo removido.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }

    // ==== DIALOGOS ====
    if (showUsuarioDialog) {
        UsuarioDialog(
            usuario = usuarioParaEditar,
            onDismiss = { showUsuarioDialog = false },
            onSave = { usuario ->
                scope.launch {
                    if (usuarioParaEditar == null) {
                        usuariosDao.inserir(usuario)
                        Toast.makeText(context, "Usuário adicionado!", Toast.LENGTH_SHORT).show()
                    } else {
                        usuariosDao.atualizar(usuario)
                        Toast.makeText(context, "Usuário atualizado!", Toast.LENGTH_SHORT).show()
                    }
                    usuarios = usuariosDao.buscarTodos()
                    showUsuarioDialog = false
                }
            }
        )
    }

    if (showJogoDialog) {
        JogoDialog(
            jogo = jogoParaEditar,
            onDismiss = { showJogoDialog = false },
            onSave = { jogo ->
                scope.launch {
                    if (jogoParaEditar == null) {
                        jogosDao.inserir(jogo)
                        Toast.makeText(context, "Jogo adicionado!", Toast.LENGTH_SHORT).show()
                    } else {
                        jogosDao.atualizar(jogo)
                        Toast.makeText(context, "Jogo atualizado!", Toast.LENGTH_SHORT).show()
                    }
                    jogos = jogosDao.buscarTodos()
                    showJogoDialog = false
                }
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
fun UsuarioDialog(usuario: Usuarios?, onDismiss: () -> Unit, onSave: (Usuarios) -> Unit) {
    var nome by remember { mutableStateOf(usuario?.nome ?: "") }
    var senha by remember { mutableStateOf(usuario?.senha ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                if (nome.isNotBlank() && senha.isNotBlank()) {
                    onSave(Usuarios(id = usuario?.id ?: 0, nome = nome, senha = senha))
                }
            }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        },
        title = { Text(if (usuario == null) "Adicionar Usuário" else "Editar Usuário") },
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
                Text("Senha", color = Color.Gray)
                TextField(
                    value = senha,
                    onValueChange = { senha = it },
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

@Preview(showBackground = true)
@Composable
fun TelaAdminPanelPreview() {
    AndroidSteamTheme {
        TelaAdminPanel()
    }
}
