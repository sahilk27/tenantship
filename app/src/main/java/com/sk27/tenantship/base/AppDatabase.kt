package com.sk27.tenantship.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sk27.tenantship.presentation.ui.authorised.tenants.TenantDao
import com.sk27.tenantship.data.entity.Tenant

/*
* The Room database for this app
* */
@Database(entities = [Tenant::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tenantDao(): TenantDao

    companion object {

        private const val DATABASE_NAME = "tenantship-db"

        //for singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }


        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java,DATABASE_NAME).build()
        }
    }


}