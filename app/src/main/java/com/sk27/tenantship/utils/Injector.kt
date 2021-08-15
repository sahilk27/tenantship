package com.sk27.tenantship.utils

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.sk27.tenantship.base.AppDatabase
import com.sk27.tenantship.base.TenantRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

interface ViewModelFactoryProvider {
    fun providePlantListViewModelFactory(context: Context): TenantListViewModelFactory
}

@FlowPreview
val Injector: ViewModelFactoryProvider
    get() = currentInjector

@FlowPreview
@ExperimentalCoroutinesApi
private object DefaultViewModelProvider: ViewModelFactoryProvider {
    private fun getTenantRepository(context: Context): TenantRepository {
        return TenantRepository.getInstance(
            plantDao(context),
            plantService()
        )
    }

    private fun plantService() = NetworkService()

    private fun plantDao(context: Context) =
        AppDatabase.getInstance(context.applicationContext).tenantDao()

    override fun providePlantListViewModelFactory(context: Context): TenantListViewModelFactory {
        val repository = getTenantRepository(context)
        return TenantListViewModelFactory(repository)
    }
}

private object Lock

@FlowPreview
@Volatile private var currentInjector: ViewModelFactoryProvider =
    DefaultViewModelProvider


@FlowPreview
@VisibleForTesting
private fun setInjectorForTesting(injector: ViewModelFactoryProvider?) {
    synchronized(Lock) {
        currentInjector = injector ?: DefaultViewModelProvider
    }
}

@FlowPreview
@VisibleForTesting
private fun resetInjector() =
    setInjectorForTesting(null)