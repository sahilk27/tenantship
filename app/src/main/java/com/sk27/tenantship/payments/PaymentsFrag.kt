package com.sk27.tenantship.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sk27.tenantship.databinding.PaymentsFrgBinding

class PaymentsFrag : Fragment() {

    lateinit var binding: PaymentsFrgBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PaymentsFrgBinding.inflate(inflater, container, false)
        return binding.root
    }
}