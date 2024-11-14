package cl.revolt.final6.modelo

//crear Array para almacenar los equipos
data class ApiResponse(
    val result: ArrayList<Productos>
)

//se crea dataclass de los productos para recibir la lista
data class Productos(
    val id: Int,
    val name: String,
    val price: String,
    val image: String
)

//se crea dataclass del detalle de cada producto

data class DetalleProducto(
    val id: Int,
    val name: String,
    val price: String,
    val image: String
)

