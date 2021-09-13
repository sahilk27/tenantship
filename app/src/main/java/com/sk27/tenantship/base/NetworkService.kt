package com.sk27.tenantship.base

import com.sk27.tenantship.model.api.Tenant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val tenantShipService = retrofit.create(TenantShipService::class.java)

    suspend fun allTenants(): List<Tenant> = withContext(Dispatchers.Default) {
        delay(1500)
        val result = tenantShipService.getAllTenants()
        result.shuffled()
    }

   /* suspend fun plantsByGrowZone(growZone: GrowZone) = withContext(Dispatchers.Default) {
        delay(1500)
        val result = sunflowerService.getAllPlants()
        result.filter { it.growZoneNumber == growZone.number }.shuffled()
    }*/

    suspend fun customPlantSortOrder(): List<String> = withContext(Dispatchers.Default) {
        val result = tenantShipService.getCustomPropertySortOrder()
        result.map { tenant -> tenant.tenantId }
    }
}

interface TenantShipService {
    @GET("sahilk27/tenantship/main/app/src/main/assets/tenants.json")
    suspend fun getAllTenants() : List<Tenant>
//https://raw.githubusercontent.com/sahilk27/tenantship/main/app/src/main/assets/tenants.json
    @GET("sahilk27/tenantship/main/app/src/main/assets/property_order.json")
    suspend fun getCustomPropertySortOrder() : List<Tenant>
}