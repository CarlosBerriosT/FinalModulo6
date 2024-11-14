package cl.revolt.final6.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.revolt.final6.modelo.Producto
import cl.revolt.final6.repositorio.ProductoRepository
import cl.revolt.final6.state.ProductoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductoViewModel @Inject constructor (
    private val repo: ProductoRepository
): ViewModel() {
    var state by mutableStateOf(ProductoState())
        private set

    val productos: Flow<List<Producto>> by lazy {
        repo.getAllProductosRoom()
    }

    init {
        getAllAPI()
    }
    //llamada a la API
    private fun getAllAPI(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllProductosAPI()
        }
    }

    fun getProductoByID(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getProductoById(id)
            state = state.copy(
                name = result.name,
                price = result.price,
                image = result.image)
        }
    }
    //inicializa el estado y borra el anterior para evitar mostrar datos antiguos
    fun clean(){
        state = state.copy(
            name = "",
            price = "",
            image = "")
    }
}