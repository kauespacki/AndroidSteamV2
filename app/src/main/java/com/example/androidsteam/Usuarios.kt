package com.example.androidsteam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuarios(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val senha: String
)
