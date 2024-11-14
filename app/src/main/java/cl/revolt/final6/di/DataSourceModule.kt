package cl.revolt.final6.di

import android.content.Context
import androidx.room.Room
import cl.revolt.final6.datasource.DbDataSource
import cl.revolt.final6.datasource.RestDataSource
import cl.revolt.final6.modelo.ProductoDAO
import cl.revolt.final6.util.Constantes.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//injectar datos modules y single
@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    //convertimos datos de JSON a GSON API
    fun providesRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    //convertimos datos de JSON a GSON API
    fun restDataSource(retrofit: Retrofit): RestDataSource =
        retrofit.create(RestDataSource::class.java)

    @Singleton
    @Provides
    //convertimos datos de JSON a GSON BASEDATOS
    fun dbDataSource(@ApplicationContext context: Context): DbDataSource {
        return Room.databaseBuilder(
            context,
            DbDataSource::class.java,
            "productos_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    //integrar DAO con basedatos creada
    fun ProductoDAO(db: DbDataSource): ProductoDAO = db.productoDAO()
}