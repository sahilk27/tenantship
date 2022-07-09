package com.sk27.tenantship.utils

class Laptop(builder: Builder) {

    private val ram = builder.ram
    private val HDD = builder.HDD
    private val screenSize = builder.screenSize


    class Builder(processor: String) {

        var ram = "32 GB"
        var HDD = "512 GB"
        var screenSize = "16 Inch"

        fun setRam(ram: String) = apply {
            this.ram = ram
        }

        fun setHDD(HDD: String) = apply {
            this.HDD = HDD
        }

        fun setScreenSize(screenSize: String) = apply {
            this.screenSize = screenSize
        }

        fun create(): Laptop {
            return Laptop(this)
        }

    }

}

