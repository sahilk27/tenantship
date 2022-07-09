package com.sk27.tenantship.utils

object MySingleton {

    init {
        print("Singleton class invoked")
    }

    fun getData(): String {
        return "data"
    }

}