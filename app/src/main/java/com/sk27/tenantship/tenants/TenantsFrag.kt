package com.sk27.tenantship.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sk27.tenantship.databinding.TenantsFrgBinding
import com.sk27.tenantship.utils.Laptop
import com.sk27.tenantship.utils.LearnInterface

class TenantsFrag : Fragment(), LearnInterface{

    private lateinit var binding: TenantsFrgBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TenantsFrgBinding.inflate(inflater, container, false)
        Laptop.Builder("i7")
            .setRam("12GB")
            .setHDD("512GB")
            .setScreenSize("16 inch")
            .create()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun getTopics(subject: String) {
        TODO("Not yet implemented")
    }

    override fun chooseSubject(subjects: List<String>) {
        TODO("Not yet implemented")
    }

}