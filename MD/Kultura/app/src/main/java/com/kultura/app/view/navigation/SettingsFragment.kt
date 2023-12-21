package com.kultura.app.view.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kultura.app.databinding.FragmentSettingsBinding
import com.kultura.app.view.guide.GuideActivity

class SettingsFragment : Fragment() {

    private lateinit var binding : FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvPanduan.setOnClickListener {
            val intentPanduan = Intent(requireContext(), GuideActivity::class.java)
            startActivity(intentPanduan)
        }

    }



}