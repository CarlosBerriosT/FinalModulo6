package cl.revolt.final6.di

import cl.revolt.final6.repositorio.ProductoRepository
import cl.revolt.final6.repositorio.ProductoRepositoryIMP
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun productoRepository(productoRepositoryIMP: ProductoRepositoryIMP): ProductoRepository
}