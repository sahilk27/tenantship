package com.sk27.tenantship.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sk27.tenantship.presentation.ui.authorised.tenants.TenantListViewModel
import com.sk27.tenantship.base.TenantRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

class TenantListViewModelFactory(private val repository: TenantRepository) :
    ViewModelProvider.NewInstanceFactory() {

//    @ExperimentalCoroutinesApi
//    override fun <T : ViewModel?> create(modelClass: Class<T>) =
//        TenantListViewModel(repository) as T
}
