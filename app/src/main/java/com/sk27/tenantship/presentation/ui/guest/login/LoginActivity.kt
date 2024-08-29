package com.sk27.tenantship.presentation.ui.guest.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.myapplication2.databinding.LoginActBinding
import com.sk27.tenantship.presentation.ui.authorised.dashboard.DashboardActivity

class LoginActivity : ComponentActivity(){

    private lateinit var binding: LoginActBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val model: LoginVm by viewModels()
        model.getUser()
        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }
}