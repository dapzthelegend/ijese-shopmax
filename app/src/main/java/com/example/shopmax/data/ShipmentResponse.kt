package com.example.shopmax.data

import com.example.shopmax.utils.convertToTitleCaseIteratingChars

data class ShipmentResponse( val status:Boolean,
                             val message:String,
                             val data: ShipmentResponseData,
                             val errors: List<ShipmentResponseError>
                            )


data class ShipmentResponseData(val shipments: List<ShipmentResponseShipments>,
                                )

data class ShipmentPackage(val weight:Double,
                           val name:String,
                           val dimensions: Dimension
                                    ){
    val packageName
    get() = name.convertToTitleCaseIteratingChars()
}


data class ShipmentResponseShipments(val id:String,
                                     val charges:Double,
                                     val packages: List<ShipmentPackage>,
                                     val tracking_id:String,
                                     val status:String,
                                    )
data class ShipmentResponseError(val message: String)

data class Dimension(   val length: Double,
                        val height:Double,
                        val width:Double)






