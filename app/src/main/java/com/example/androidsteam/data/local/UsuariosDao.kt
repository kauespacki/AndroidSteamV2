package com.example.androidsteam.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuariosDAO{

    @Insert
    suspend fun inserir(usuario: Usuarios)

    @Query("SELECT * FROM usuarios")
    fun buscarTodos() : Flow<List<Usuarios>>

    @Delete
    suspend fun deletar(usuarios: Usuarios)

    @Update
    suspend fun atualizar(usuarios: Usuarios)

}
