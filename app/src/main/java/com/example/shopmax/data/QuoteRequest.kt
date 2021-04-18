package com.example.shopmax.data

data class ShipmentRequest(val pickupRequested: Boolean = true,
                           val charges:Double = 0.0,
                           val plannedShippingDateAndTime: String,
                           val productCode:String = "N",
                           val packages: List<ShipmentPackage>,
                           val receiverDetails: ReceiverDetails,
                           val pickupDetails: PickupDetails

)


data class ReceiverDetails( val cityName:String,
                            val address:String,
                            val phone: String,
                            val fullName:String
 )

data class PickupDetails( val cityName:String,
                          val address:String,
                          val phone: String,
                          val fullName:String)