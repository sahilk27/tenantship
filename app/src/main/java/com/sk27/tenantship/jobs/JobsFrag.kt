package com.sk27.tenantship.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sk27.tenantship.databinding.JobFrgBinding

class JobsFrag : Fragment() {

    lateinit var binding: JobFrgBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = JobFrgBinding.inflate(inflater, container, false)
        return binding.root
    }


}