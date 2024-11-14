package cl.revolt.final6.repositorio

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.revolt.final6.datasource.RestDataSource
import cl.revolt.final6.modelo.DetalleProducto
import cl.revolt.final6.modelo.Producto
import cl.revolt.final6.modelo.ProductoDAO
import cl.revolt.final6.modelo.Productos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ProductoRepository {
    suspend fun getProductoById(id:Int): DetalleProducto
    suspend fun getAllProductosAPI(): ArrayList<Productos>
    //Flow hace referencia a la tabla de modelo
    fun getAllProductosRoom(): Flow<List<Producto>>
}

class ProductoRepositoryIMP @Inject constructor(private val dataSource: RestDataSource, private val productoDAO: ProductoDAO): ProductoRepository {
    override suspend fun getProductoById(id: Int): DetalleProducto {
        val data = dataSource.getProductoById(id).body()!!
        val producto = DetalleProducto(
            id = data.id,
            name = data.name,
            price = data.price,
            image = data.image
        )
        return producto
    }

    override suspend fun getAllProductosAPI(): ArrayList<Productos> {
        val data = dataSource.getProductos()
        data.forEach{
            val producto = Producto(
                it.id,
                it.name,
                it.price,
                it.image
            )
            productoDAO.insert(producto)
        }
        return ArrayList(data)
    }

    override fun getAllProductosRoom(): Flow<List<Producto>> = productoDAO.getAll()

}