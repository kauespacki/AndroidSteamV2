package com.example.androidsteam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface JogosDAO{

    @Insert
    suspend fun inserir(jogo: Jogos)

    @Query("SELECT * FROM usuarios")
    suspend fun buscarTodos() : List<Jogos>

    @Delete
    suspend fun deletar(jogos: Jogos)

    @Update
    suspend fun atualizar(jogos: Jogos)

}
