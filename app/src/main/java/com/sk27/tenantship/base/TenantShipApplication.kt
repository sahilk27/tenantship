package com.sk27.tenantship.base
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit


@HiltAndroidApp
class TenantShipApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

       // val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)

    }
}