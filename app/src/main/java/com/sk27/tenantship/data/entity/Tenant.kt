package com.sk27.tenantship.data.entity

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
    var android: Int,
    val propertyType: String,
) {
    override fun toString() = name
}

@JvmInline
value class PropertyType(val propertyType: String)

val UnknownType = PropertyType("Unknown")

fun drop(random: (String, Int) -> Unit) {

}
