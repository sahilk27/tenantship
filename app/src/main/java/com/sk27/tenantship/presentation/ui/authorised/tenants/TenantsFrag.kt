package com.sk27.tenantship.presentation.ui.authorised.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sk27.tenantship.databinding.TenantsFrgBinding

class TenantsFrag : Fragment() {

    private lateinit var binding: TenantsFrgBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TenantsFrgBinding.inflate(inflater, container, false)
        return binding.root
    }

}