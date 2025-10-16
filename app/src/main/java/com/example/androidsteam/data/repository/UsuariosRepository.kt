package com.example.androidsteam.data.repository

import com.example.androidsteam.data.local.Jogos
import com.example.androidsteam.data.local.JogosDAO
import com.example.androidsteam.data.local.Usuarios
import com.example.androidsteam.data.local.UsuariosDAO
import kotlinx.coroutines.flow.Flow

class UsuariosRepository (private val usuariosDAO: UsuariosDAO) {
    suspend fun buscarTodos(): Flow<List<Usuarios>> {
        return usuariosDAO.buscarTodos()
    }

    suspend fun inserir(usuario: Usuarios){
        usuariosDAO.inserir(usuario)
    }

    suspend fun atualizar(usuario: Usuarios){
        usuariosDAO.atualizar(usuario)
    }

    suspend fun deletar(usuario: Usuarios){
        usuariosDAO.deletar(usuario)
    }
}