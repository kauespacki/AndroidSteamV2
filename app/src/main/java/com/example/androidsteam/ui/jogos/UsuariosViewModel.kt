package com.example.androidsteam.ui.jogos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidsteam.data.local.Usuarios
import com.example.androidsteam.data.repository.UsuariosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class UsuariosUiState(
    val listaDeUsuarios: List<Usuarios> = emptyList(),
    val nome: String = "",
    val senha: String = "",
    val usuariosEmEdicao: Usuarios? = null
){
    val textoBotao: String
        get() = if(usuariosEmEdicao == null) "adicionar usuario" else "atualizar usuario"
}

class UsuariosViewModel(private val repository: UsuariosRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(UsuariosUiState())
    val uiState: StateFlow<UsuariosUiState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch{
            repository.buscarTodos().collect{
                    usuarios ->
                _uiState.update{
                        currentState ->
                    currentState.copy(listaDeUsuarios = usuarios)
                }
            }
        }
    }

    fun onNameChange(novoNome: String){
        _uiState.update { it.copy(nome = novoNome) }

    }

    fun onPasswordChange(novaSenha: String){
        _uiState.update { it.copy(senha = novaSenha) }

    }
    fun onEditar(usuario: Usuarios){
        _uiState.update{
            it.copy(
                usuariosEmEdicao = usuario,
                nome = usuario.nome,
                senha = usuario.senha
            )
        }

    }

    fun onDeletar(usuarios: Usuarios){
        viewModelScope.launch {
            repository.deletar(usuarios)
        }

    }

    fun onSalvar(){
        val state = _uiState.value

        if (state.nome.isBlank() && state.senha.isBlank()) return

        val usuariosParaSalvar = state.usuariosEmEdicao?.
        copy(
            nome = state.nome,
            senha = state.senha
        )?: Usuarios(
            nome = state.nome,
            senha = state.senha
        )

        viewModelScope.launch {
            if(state.usuariosEmEdicao == null){
                repository.inserir(usuariosParaSalvar)
            } else {
                repository.atualizar(usuariosParaSalvar)

            }
            LimparCampos()
        }
    }

    private fun LimparCampos(){
        _uiState.update{
            it.copy(
                nome = "",
                senha = "",
                usuariosEmEdicao = null
            )
        }
    }

    fun validarLogin(
        nome: String,
        senha: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            val usuario = repository.buscarUsuario(nome, senha)
            if (usuario != null) {
                onSuccess()
            } else {
                onError()
            }
        }
    }

}


class UsuariosViewModelFactory(private val repository: UsuariosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class <T>): T{
        if(modelClass.isAssignableFrom(UsuariosViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return UsuariosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}