package com.example.androidsteam

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "telaInicial"){

        composable("telaLogin") { TelaLogin(navController) }
        composable("telaInicial") { Tela3() }
        composable("telaPerfil") {  }
        composable("telaDetalhesPerfil") {  }

    }
}