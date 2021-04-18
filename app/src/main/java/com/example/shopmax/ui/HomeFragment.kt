package com.example.shopmax.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopmax.R
import com.example.shopmax.data.ShipmentResponseShipments
import com.example.shopmax.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private val viewModel:HomeViewModel by viewModels()
    private lateinit var shipmentAdapter:ShipmentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.fragment_home,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeFields()
        viewModel.shipments.observe(viewLifecycleOwner, {
            shipmentAdapter.setList(it.data.shipments.asReversed())
        })

    }

    private fun initializeFields() {
        shipmentAdapter = ShipmentAdapter { model ->
            onItemClicked(model)
        }

        home_shipments_recyclerview.layoutManager = LinearLayoutManager(activity)
        home_shipments_recyclerview.adapter = shipmentAdapter
        home_shipments_recyclerview.setHasFixedSize(true)
        home_shipments_recyclerview.addItemDecoration(ShipmentItemDecoration(30))
        home_make_shipment_fab.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToQuoteFragment()
            it.findNavController().navigate(action)
        }

    }

    private fun onItemClicked(model: ShipmentResponseShipments) {
        (activity as ShopMaxActivity).showError("Tracking ${model.packages[0].name}")



    }


    override fun onResume() {
        super.onResume()
        viewModel.getShipments()
    }


}