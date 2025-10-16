package com.example.androidsteam.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jogos")
data class Jogos(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val imagem: String,
    val preco: String
)
