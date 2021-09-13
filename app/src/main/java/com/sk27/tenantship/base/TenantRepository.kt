/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sk27.tenantship.base

import androidx.annotation.AnyThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.sk27.tenantship.TenantDao
import com.sk27.tenantship.model.api.PropertyType
import com.sk27.tenantship.model.api.Tenant
import com.sk27.tenantship.model.api.UnknownType
import com.sk27.tenantship.utils.ComparablePair
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Repository module for handling data operations.
 *
 * The @ExperimentalCoroutinesApi and @FlowPreview indicate that experimental APIs are being used.
 */
@ExperimentalCoroutinesApi
@FlowPreview
class TenantRepository private constructor(
    private val tenantDao: TenantDao,
    private val plantService: NetworkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    /**
     * Fetch a list of [Plant]s from the database and apply a custom sort order to the list.
     * Returns a LiveData-wrapped List of Plants.
     */
    val tenants: LiveData<List<Tenant>> = liveData<List<Tenant>> {
        // Observe plants from the database (just like a normal LiveData + Room return)
        val plantsLiveData = tenantDao.getTenants()

        /* // Fetch our custom sort from the network in a main-safe suspending call (cached)
        val customSortOrder = plantsListSortOrderCache.getOrAwait()

        // Map the LiveData, applying the sort criteria
        emitSource(plantsLiveData.map { plantList -> plantList.applySort(customSortOrder) })*/
    }

    /**
     * Fetch a list of [Plant]s from the database that matches a given [GrowZone] and apply a
     * custom sort order to the list. Returns a LiveData-wrapped List of Plants.
     *
     * This this similar to [tenants], but uses *main-safe* transforms to avoid blocking the main
     * thread.
     */
  /*  fun getPlantsWithGrowZone(propertyType: PropertyType) =
        tenantDao.getTenantsByPropertyType(propertyType.propertyType)

            // Apply switchMap, which "switches" to a new liveData every time a new value is
            // received
            .switchMap { tenantsList ->

                // Use the liveData builder to construct a new coroutine-backed LiveData
                *//* liveData {
                    val customSortOrder = plantsListSortOrderCache.getOrAwait()

                    // Emit the sorted list to the LiveData builder, which will be the new value
                    // sent to getPlantsWithGrowZoneNumber
                    emit(tenantsList.applyMainSafeSort(customSortOrder))
                }*//*
            }*/

    /**
     * Create a flow that calls a single function
     */
   // private val customSortFlow = plantsListSortOrderCache::getOrAwait.asFlow()

    /**
     * This is a version of [tenants] (from above), and represent our observable database using
     * [flow], which has a similar interface to sequences in Kotlin. This allows us to do async or
     * suspending transforms of the data.
     */
   /* val tenantFlow: Flow<List<Tenant>>
        get() = tenantDao.getTenantsFlow()

            // When the result of customSortFlow is available, this will combine it with the latest
            // value from the flow above.  Thus, as long as both `plants` and `sortOrder`
            // have an initial value (their flow has emitted at least one value), any change
            // to either `plants` or `sortOrder` will call `plants.applySort(sortOrder)`.
            .combine(customSortFlow) { tenant, sortOrder ->
                tenant.applySort(sortOrder)
            }

            // Flow allows you to switch the dispatcher the previous transforms run on.
            // Doing so introduces a buffer that the lines above this can write to, which we don't
            // need for this UI use-case that only cares about the latest value.
            //
            // This flowOn is needed to make the [background-thread only] applySort call above
            // run on a background thread.
            .flowOn(defaultDispatcher)

            // We can tell flow to make the buffer "conflated". It removes the buffer from flowOn
            // and only shares the last value, as our UI discards any intermediate values in the
            // buffer.
            .conflate()*/

    /**
     * This is a version of [getPlantsWithGrowZoneNumber] (from above), but using [Flow].
     * It differs from [tenantFlow] in that it only calls *main-safe* suspend functions in the
     * [map] operator, so it does not need to use [flowOn].
     */
   /* fun getPlantsWithGrowZoneFlow(propertyType: PropertyType): Flow<List<Tenant>> {
        // A Flow from Room will return each value, just like a LiveData.
        return tenantDao.getPlantsWithGrowZoneNumberFlow(growZone.number)
            // When a new value is sent from the database, we can transform it using a
            // suspending map function. This allows us to call async code like here
            // where it potentially loads the sort order from network (if not cached)
            //
            // Since all calls in this map are main-safe, flowOn is not needed here.
            // Both Room and Retrofit will run the query on a different thread even though this
            // flow is using the main thread.
            .map { plantList ->

                // We can make a request for the cached sort order directly here, because map
                // takes a suspend lambda
                //
                // This may trigger a network request if it's not yet cached, but since the network
                // call is main safe, we won't block the main thread (even though this flow executes
                // on Dispatchers.Main).
                val sortOrderFromNetwork = plantsListSortOrderCache.getOrAwait()

                // The result will be the sorted list with custom sort order applied. Note that this
                // call is also main-safe due to using applyMainSafeSort.
                val nextValue = plantList.applyMainSafeSort(sortOrderFromNetwork)
                nextValue
            }
    }*/

    /**
     * A function that sorts the list of Plants in a given custom order.
     */
    private fun List<Tenant>.applySort(customSortOrder: List<String>): List<Tenant> {
        // Our product manager requested that these plants always be sorted first in this
        // order whenever they are present in the array
        return sortedBy { tenant ->
            val positionForItem = customSortOrder.indexOf(tenant.tenantId).let { order ->
                if (order > -1) order else Int.MAX_VALUE
            }
            ComparablePair(positionForItem, tenant.name)
        }
    }

    /**
     * The same sorting function as [applySort], but as a suspend function that can run on any thread
     * (main-safe)
     */
    @AnyThread
    private suspend fun List<Tenant>.applyMainSafeSort(customSortOrder: List<String>) =
        withContext(defaultDispatcher) {
            this@applyMainSafeSort.applySort(customSortOrder)
        }

    /**
     * Returns true if we should make a network request.
     */
    private suspend fun shouldUpdatePlantsCache(propertyType: PropertyType): Boolean {
        // suspending function, so you can e.g. check the status of the database here
        return true
    }

    /**
     * Update the plants cache.
     *
     * This function may decide to avoid making a network requests on every call based on a
     * cache-invalidation policy.
     */
    suspend fun tryUpdateRecentPlantsCache() {
        if (shouldUpdatePlantsCache(UnknownType)) fetchRecentTenants()
    }


    /**
     * Fetch a new list of Tenants from the network, and append them to [tenantDao]
     */
    private suspend fun fetchRecentTenants() {
        val plants = plantService.allTenants()
        tenantDao.insertAll(plants)
    }

   /* *//**
     * Fetch a list of plants for a grow zone from the network, and append them to [tenantDao]
     *//*
    private suspend fun fetchPlantsForGrowZone(growZoneNumber: GrowZone): List<Plant> {
        val plants = plantService.plantsByGrowZone(growZoneNumber)
        tenantDao.insertAll(plants)
        return plants
    }
*/
    companion object {
        // For Singleton instantiation
        @Volatile private var instance: TenantRepository? = null

        fun getInstance(tenantDao: TenantDao, tenantService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: TenantRepository(tenantDao, tenantService).also { instance = it }
            }
    }
}
