package com.example.shopmax.ui

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmax.data.*
import com.example.shopmax.utils.setMediatorSources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class ShipmentViewModel @Inject constructor(private val shipmentService: ShipmentService): ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    val weight: MutableLiveData<String> = MutableLiveData()
    val height: MutableLiveData<String> = MutableLiveData()
    val length: MutableLiveData<String> = MutableLiveData()
    val charge: MutableLiveData<String> = MutableLiveData()
    val width: MutableLiveData<String> = MutableLiveData()
    val receiverName: MutableLiveData<String> = MutableLiveData()
    val receiverAddress: MutableLiveData<String> = MutableLiveData()
    val receiverCity: MutableLiveData<String> = MutableLiveData()
    val receiverPhoneNo: MutableLiveData<String> = MutableLiveData()
    val pickupName: MutableLiveData<String> = MutableLiveData()
    val pickupAddress: MutableLiveData<String> = MutableLiveData()
    val pickupCity: MutableLiveData<String> = MutableLiveData()
    val pickupNumber: MutableLiveData<String> = MutableLiveData()
    val date: MutableLiveData<String> = MutableLiveData()
    val enable: MediatorLiveData<Boolean> = MediatorLiveData()
    val done: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: MutableLiveData<String> = MutableLiveData()





    init {
        enable.value = false
        setMediatorSources(mediator = enable, listOf(name,weight,height,charge,length,width,receiverName,receiverAddress, receiverCity, receiverPhoneNo, pickupName
            , pickupAddress, pickupCity, pickupNumber,date)){
            validateInput()
        }


    }

    private fun validateInput() {

        enable.value =
            !TextUtils.isEmpty(name.value) &&  !TextUtils.isEmpty(weight.value)&&  !TextUtils.isEmpty(height.value)&&  !TextUtils.isEmpty(length.value)&&  !TextUtils.isEmpty(width.value)&&
                    !TextUtils.isEmpty(receiverCity.value)&&  !TextUtils.isEmpty(receiverName.value)&&  !TextUtils.isEmpty(receiverAddress.value)&&  !TextUtils.isEmpty(receiverPhoneNo.value)&&
                    !TextUtils.isEmpty(pickupName.value)&&  !TextUtils.isEmpty(pickupCity.value)&&  !TextUtils.isEmpty(pickupNumber.value)&&  !TextUtils.isEmpty(pickupNumber.value)&&
                    !TextUtils.isEmpty(date.value)  &&  !TextUtils.isEmpty(charge.value)

    }



    fun makeShipments(){

        viewModelScope.launch {
            try{
                loading.value = true
                val dimension = Dimension(length = length.value?.toDouble()!!,
                    width = width.value?.toDouble()!!,
                    height = height.value?.toDouble()!!
                )
                val pack = ShipmentPackage(weight = weight.value?.toDouble()!!,
                    name = name.value!!,
                    dimensions = dimension
                )
                val packages = ArrayList<ShipmentPackage>()
                packages.add(pack)

                val pickupDetails = PickupDetails(fullName = pickupName.value!!,
                    cityName = pickupCity.value!!,
                    phone = pickupNumber.value!!,
                    address = pickupAddress.value!!)

                val receiverDetails = ReceiverDetails(fullName = receiverName.value!!,
                    cityName = receiverCity.value!!,
                    phone = receiverPhoneNo.value!!,
                    address = receiverAddress.value!!)
                val shipmentRequest = ShipmentRequest(
                    plannedShippingDateAndTime = formatDate(date = formatDate(date.value!! ),
                    ),
                    charges = charge.value?.toDouble()!!,
                    packages = packages,
                    receiverDetails = receiverDetails,
                    pickupDetails = pickupDetails
                )

                val shipmentResponse = shipmentService.makeShipment(shipmentRequest)

                loading.value = false
                if(shipmentResponse.status){
                    done.value = true
                }
                else{
                    error.value = shipmentResponse.message
                }
            }catch (e:Exception){
                loading.value = false
                error.value  = e.message

            }
        }

    }


    @SuppressLint("SimpleDateFormat")
    fun formatDate(date:String ): String {

        try {

            val formatIn = SimpleDateFormat("yyyy-MM-dd")
            val formatOut= SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val dateCreated = formatIn.parse(date)
            dateCreated?.time = Date().time
            val d = formatOut.format(dateCreated!!)
            Log.e("Date", d)
            return "2021-04-21T09:14:50.000Z"
        } catch (e: ParseException) {
            e.printStackTrace()


        }

        return ""
    }
}