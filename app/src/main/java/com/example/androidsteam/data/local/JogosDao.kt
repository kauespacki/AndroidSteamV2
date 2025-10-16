package com.example.androidsteam.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JogosDAO{

    @Insert
    suspend fun inserir(jogo: Jogos)

    @Query("SELECT * FROM jogos")
    fun buscarTodos() : Flow<List<Jogos>>

    @Delete
    suspend fun deletar(jogos: Jogos)

    @Update
    suspend fun atualizar(jogos: Jogos)

}
