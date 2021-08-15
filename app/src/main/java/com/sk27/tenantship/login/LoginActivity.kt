package com.sk27.tenantship.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sk27.tenantship.databinding.LoginActBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val model: LoginVm by viewModels()
        model.getUser()
    }
}