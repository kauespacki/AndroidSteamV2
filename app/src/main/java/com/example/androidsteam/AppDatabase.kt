package com.example.androidsteam

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Usuarios::class, Jogos::class], // <- Adiciona a entidade Jogos
    version = 2, // <- Incrementa a versão do banco
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuariosDAO(): UsuariosDAO
    abstract fun jogosDAO(): JogosDAO // <- Adiciona o DAO de Jogos

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("app_database.db") // usa o banco pronto
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
