package com.example.shopmax.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.shopmax.data.ShipmentResponseShipments
import com.example.shopmax.databinding.ShipmentListItemBinding
import kotlinx.android.synthetic.main.shipment_list_item.view.*

class ShipmentViewHolder(val binding: ShipmentListItemBinding,
    onItemClicked: (Int) -> Unit): RecyclerView.ViewHolder(binding.root){


    init {
        binding.root.shipment_list_item_track_textview.setOnClickListener {
            onItemClicked(adapterPosition)
        }
    }


    fun bind(model: ShipmentResponseShipments){
        binding.model = model
    }


}