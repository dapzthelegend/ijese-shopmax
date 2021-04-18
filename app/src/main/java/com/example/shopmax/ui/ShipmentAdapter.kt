package com.example.shopmax.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopmax.R
import com.example.shopmax.data.ShipmentResponseShipments
import com.example.shopmax.databinding.ShipmentListItemBinding

class ShipmentAdapter(private val onItemClicked: (ShipmentResponseShipments) -> Unit): RecyclerView.Adapter<ShipmentViewHolder> (){

    private var shipments: List<ShipmentResponseShipments> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentViewHolder {
        val binding: ShipmentListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.shipment_list_item, parent, false  )
        return ShipmentViewHolder(binding){
            onItemClicked(shipments[it])

        }
    }

    override fun onBindViewHolder(holder: ShipmentViewHolder, position: Int) {
        val model = shipments[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int =
        shipments.size

    fun setList(shipments: List<ShipmentResponseShipments>){
        this.shipments = shipments
        notifyDataSetChanged()
    }
}