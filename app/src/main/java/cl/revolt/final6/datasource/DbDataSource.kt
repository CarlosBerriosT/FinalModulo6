package cl.revolt.final6.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.revolt.final6.modelo.Producto
import cl.revolt.final6.modelo.ProductoDAO

@Database(entities = [Producto::class], version = 1)
abstract class DbDataSource: RoomDatabase() {
    //implementamos el DAO para que haga las consultas
    abstract fun productoDAO(): ProductoDAO
}