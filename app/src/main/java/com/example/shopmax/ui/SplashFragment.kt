package com.example.shopmax.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.shopmax.R
import com.example.shopmax.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_splash.*



@AndroidEntryPoint
class SplashFragment : Fragment() {


   private lateinit var binding:FragmentSplashBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(LayoutInflater.from(activity),
            R.layout.fragment_splash, container, false)

        binding.lifecycleOwner = this

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeFields()
    }
    private fun initializeFields() {
        splash_get_started_extended_fab.shrink()
        splash_get_started_extended_fab.setOnClickListener {
            if(splash_get_started_extended_fab.isExtended){
                val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                splash_get_started_extended_fab.findNavController().navigate(action)

            }else{
                splash_get_started_extended_fab.extend()
            }
        }
    }

}