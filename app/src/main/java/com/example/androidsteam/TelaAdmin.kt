package com.example.androidsteam

import android.content.Context
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
    val scope = rememberCoroutineScope()

    var usuarios by remember { mutableStateOf(listOf<Usuarios>()) }
    var showUsuarioDialog by remember { mutableStateOf(false) }
    var usuarioParaEditar by remember { mutableStateOf<Usuarios?>(null) }

    // Buscar dados do banco ao abrir a tela
    LaunchedEffect(Unit) {
        usuarios = usuariosDao.buscarTodos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1c293a))
            .padding(16.dp)
    ) {
        Text("Painel do Administrador", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

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
    }

    // Diálogo de cadastro/edição
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
