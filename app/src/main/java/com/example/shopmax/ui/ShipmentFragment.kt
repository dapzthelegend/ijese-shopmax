@file:Suppress("DEPRECATION")

package com.example.shopmax.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.shopmax.R
import com.example.shopmax.databinding.FragmentShipmentBinding
import com.example.shopmax.utils.DatePickerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shipment.*


@AndroidEntryPoint
class ShipmentFragment : Fragment() {
    lateinit var binding:FragmentShipmentBinding
    private val viewModel: ShipmentViewModel  by viewModels()
    private lateinit var progressBar:ProgressDialog



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity),
            R.layout.fragment_shipment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initializeFields()
        observeViewModel()

    }

    private fun observeViewModel() {
       viewModel.done.observe(viewLifecycleOwner){

           if(it) view?.findNavController()?.popBackStack()
       }

        viewModel.error.observe(viewLifecycleOwner){
            (activity as ShopMaxActivity).showError(it)
        }

        viewModel.loading.observe(viewLifecycleOwner){
           if(it) progressBar.show() else progressBar.dismiss()
        }

        viewModel.enable.observe(viewLifecycleOwner){

        }
    }

    private fun initializeFields() {
        shipments_close_button.setOnClickListener {
         it.findNavController().popBackStack()
        }

        shipment_date_edittext.setOnClickListener {
            showDatePicker()
        }

        shipment_make_shipment_button.setOnClickListener {

            if(viewModel.enable.value!!){
                viewModel.makeShipments()
            }else{
                (activity as ShopMaxActivity).showError("Fill all fields.")
            }

        }

        progressBar = ProgressDialog.show(requireContext(), "", "message")
        progressBar.window?.setGravity(Gravity.CENTER)
        progressBar.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressBar.setCanceledOnTouchOutside(false)
        progressBar.setContentView(R.layout.loader)




    }






    private fun showDatePicker(){
        DatePickerFragment{
            setDate(it)
        }.show(requireFragmentManager(), "datePicker")

    }

    private fun setDate(date:String){
        viewModel.date.value = date

    }


}