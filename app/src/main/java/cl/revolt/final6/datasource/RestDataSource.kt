package cl.revolt.final6.datasource


import cl.revolt.final6.modelo.DetalleProducto
import cl.revolt.final6.modelo.Productos
import cl.revolt.final6.util.Constantes.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {

    @GET(ENDPOINT)
    //retorna la lista de equipos, con la info basica
    suspend fun getProductos(): List<Productos>

    @GET("$ENDPOINT/{id}")
    suspend fun getProductoById(@Path(value = "id") id: Int): Response<DetalleProducto>
}