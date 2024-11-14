package cl.revolt.sprintfinal6.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.revolt.final6.ViewModel.ProductoViewModel
import cl.revolt.final6.componentes.CardProducto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: ProductoViewModel, navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "App Productos") }
            )
        }
    ) { innerPadding ->
        val producto by viewModel.productos.collectAsState(listOf())

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            items(producto) { item ->
                CardProducto(
                    item.name,
                    item.image
                ) {
                    navController.navigate("DetailView/${item.id}")
                }
            }
        }
    }
}