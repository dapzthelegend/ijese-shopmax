package com.example.shopmax.ui

import androidx.lifecycle.*
import com.example.shopmax.data.ShipmentResponse
import com.example.shopmax.data.ShipmentService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val shipmentService: ShipmentService) : ViewModel () {
    private var _shipments:MutableLiveData<ShipmentResponse> = MutableLiveData()
    val shipments:LiveData<ShipmentResponse> = _shipments




    fun getShipments(){
        try {
            viewModelScope.launch {
                _shipments.value = shipmentService.getShipments()
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }



}