package com.sk27.tenantship

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sk27.tenantship.model.api.Tenant
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantDao {

    @Query("SELECT * FROM tenants ORDER BY name")
    fun getTenants(): LiveData<List<Tenant>>

    @Query("SELECT * FROM TENANTS ORDER BY name")
    fun getTenantsFlow(): Flow<List<Tenant>>

    @Query("SELECT * FROM tenants WHERE propertyType = :propertyType ORDER BY name")
    fun getTenantsByPropertyTypeFlow(propertyType: String): Flow<List<Tenant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tenants: List<Tenant>)
}