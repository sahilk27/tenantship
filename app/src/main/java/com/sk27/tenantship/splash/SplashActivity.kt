package com.sk27.tenantship.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sk27.tenantship.R
import com.sk27.tenantship.login.LoginActivity

@Suppress("PrivatePropertyName")

class SplashActivity : AppCompatActivity() {

    private val DELAY_MILLIS: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_act)

        Handler(Looper.getMainLooper())
            .postDelayed(Runnable {
                startActivity(Intent(this, LoginActivity::class.java))
            }, DELAY_MILLIS)
    }
}