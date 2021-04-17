package com.sk27.tenantship.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sk27.tenantship.R
import com.sk27.tenantship.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var mHandler: Handler;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_act)
        mHandler = Handler(Looper.getMainLooper())
        mHandler.postDelayed(Runnable {
            var goToLogin = Intent(this,LoginActivity::class.java)
            startActivity(goToLogin)
        }, 1000)
    }

}