package com.sk27.tenantship.model.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenants")
data class Tenant(
    @PrimaryKey @ColumnInfo(name = "id") val tenantId: String,
    val name: String,
    val propertyAddress: String,
    val circleRate: Int,
    val rentAmount: Int,
    val propertyType: String,
    val imageUrl: String = ""
) {
    override fun toString() = name
}

inline class PropertyType(val propertyType: String)
val UnknownType = PropertyType("Unknown")
