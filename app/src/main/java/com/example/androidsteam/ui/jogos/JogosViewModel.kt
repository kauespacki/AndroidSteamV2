package com.example.androidsteam.ui.jogos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidsteam.data.local.Jogos
import com.example.androidsteam.data.repository.JogosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class JogosUiState(
    val listaDeJogos: List<Jogos> = emptyList(),
    val nome: String = "",
    val imagem: String = "",
    val preco: String = "",
    val jogosEmEdicao: Jogos? = null
){
    val textoBotao: String
        get() = if (jogosEmEdicao == null) "Adicionar Jogo" else "Atualizar Jogo"
}

class JogosViewModel(private val repository: JogosRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(JogosUiState())
    val uiState: StateFlow<JogosUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.buscarTodos().collect{
                    jogos ->
                _uiState.update {
                        currentState ->
                    currentState.copy( listaDeJogos = jogos )
                }
            }
        }
    }

    fun onNameChange(novoNome: String) {
        _uiState.update { it.copy(nome = novoNome) }
    }

    fun onImagemChange(novaImagem: String) {
        _uiState.update { it.copy(imagem = novaImagem) }
    }

    fun onPrecoChange(novoPreco: String) {
        _uiState.update { it.copy(preco = novoPreco) }
    }

    fun onEditar(jogo: Jogos) {
        _uiState.update {
            it.copy(
                jogosEmEdicao = jogo,
                nome = jogo.nome,
                imagem = jogo.imagem,
                preco = jogo.preco
            )
        }
    }

    fun onDeletar(jogo: Jogos) {
        viewModelScope.launch {
            repository.deletar(jogo)
        }
    }

    fun onSalvar() {
        val state = _uiState.value
        if (state.nome.isBlank() || state.imagem.isBlank() || state.preco.isBlank()) return

        val jogosParaSalvar = state.jogosEmEdicao?.
        copy(
            nome = state.nome,
            imagem = state.imagem,
            preco = state.preco
        ) ?: Jogos( // Se não... cria um novo...
            nome = state.nome,
            imagem = state.imagem,
            preco = state.preco
        )
        viewModelScope.launch {
            if(state.jogosEmEdicao == null){ // Verifica se está editando ou adicionando
                repository.inserir(jogosParaSalvar) // Adiciona um novo filme
            } else {
                repository.atualizar(jogosParaSalvar) // Atualiza um filme existente
            }
        }
        LimparCampos()
    }

    private fun LimparCampos() {
        _uiState.update {
            it.copy(
                nome = "",
                imagem = "",
                preco = "",
                jogosEmEdicao = null
            )
        }
    }
}

class JogosViewModelFactory(private val repository: JogosRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(JogosViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return JogosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}