package com.example.androidsteam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuariosDAO{

    @Insert
    suspend fun inserir(usuario: Usuarios)

    @Query("SELECT * FROM usuarios")
    suspend fun buscarTodos() : List<Usuarios>

    @Delete
    suspend fun deletar(usuarios: Usuarios)

    @Update
    suspend fun atualizar(usuarios: Usuarios)

}
