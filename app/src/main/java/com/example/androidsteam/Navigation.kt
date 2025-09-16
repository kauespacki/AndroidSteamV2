package com.example.androidsteam

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "telaLogin"){
        composable("telaLogin") { Tela1(
            onClickInicio = {
                navController.navigate("telaInicial")
            },
            onClickCadastro = {
                navController.navigate("telaCadastro")
            }
        ) }
        composable("telaInicial") { Tela3{navController.navigate("telaPerfil")} }
        composable("telaPerfil") { Tela2(
            onClickInicio = {
                navController.navigate("telaInicial")
            },
            onClickDetalhes = {
                navController.navigate("telaDetalhesPerfil")
            }
        ) }
        composable("telaDetalhesPerfil") { Tela4() }
        composable("telaCadastro") { Tela5(onClickInicio = {navController.navigate("telaInicial")}) }
    }
}