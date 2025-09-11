package com.example.androidsteam

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "telaLogin"){
        composable("telaLogin") { Tela1{navController.navigate("telaInicial")} }
        composable("telaInicial") { Tela3{navController.navigate("telaPerfil")} }
        composable("telaPerfil") { Tela2{navController.navigate("telaInicial")} }
        composable("telaDetalhesPerfil") { Tela4() }
    }
}