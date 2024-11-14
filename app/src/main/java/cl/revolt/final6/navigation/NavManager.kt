package cl.revolt.final6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.revolt.final6.ViewModel.ProductoViewModel
import cl.revolt.sprintfinal6.view.DetailView
import cl.revolt.sprintfinal6.view.HomeView

@Composable
fun NavManager(viewModel: ProductoViewModel){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            HomeView(viewModel, navController)
        }
        composable("DetailView/{id}", arguments = listOf(
            navArgument("id"){type = NavType.IntType}
        )) {
            //Recibe el id al presionar y en caso que sea null entrega un 0 para evitar errores
            val id = it.arguments?.getInt("id") ?: 0
            DetailView(viewModel, navController, id)
        }
    }
}