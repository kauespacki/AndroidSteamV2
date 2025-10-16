package com.example.androidsteam.data.repository

import com.example.androidsteam.data.local.Jogos
import com.example.androidsteam.data.local.JogosDAO
import kotlinx.coroutines.flow.Flow

class JogosRepository (private val jogosDAO: JogosDAO) {
    suspend fun buscarTodos(): Flow<List<Jogos>> {
        return jogosDAO.buscarTodos()
    }

    suspend fun inserir(jogo: Jogos){
        jogosDAO.inserir(jogo)
    }

    suspend fun atualizar(jogo: Jogos){
        jogosDAO.atualizar(jogo)
    }

    suspend fun deletar(jogo: Jogos){
        jogosDAO.deletar(jogo)
    }
}