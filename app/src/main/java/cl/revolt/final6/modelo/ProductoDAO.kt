package cl.revolt.final6.modelo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDAO {
    //si encuentra id repetida reemplaza los datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(producto: Producto)

    @Query("SELECT * FROM producto ORDER BY id")
    fun getAll(): Flow<List<Producto>>
}