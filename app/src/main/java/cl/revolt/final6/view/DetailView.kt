package cl.revolt.sprintfinal6.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.revolt.final6.ViewModel.ProductoViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(viewModel: ProductoViewModel, navController: NavController, id: Int){

    LaunchedEffect(Unit) {
        viewModel.getProductoByID(id)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clean()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Vista Detalle", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFA77840)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() } ) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            "",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text( text = viewModel.state.name )
            //codigo para presentar el precio en la vista individual
            HorizontalDivider()
            //Text( text = viewModel.state.price )
            Text(text = "$${viewModel.state.price}")
            //codigo para presentar imagen en la vista individual
            HorizontalDivider()
            val image = rememberAsyncImagePainter(model = viewModel.state.image)
            Image(
                painter = image,
                contentDescription = "ImagenProducto",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp)

            )
        }
    }

}