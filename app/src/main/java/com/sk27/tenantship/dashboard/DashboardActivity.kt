package com.sk27.tenantship.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sk27.tenantship.R
import com.sk27.tenantship.TenantListViewModel
import com.sk27.tenantship.login.LoginVm

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dash_act)

    }
}