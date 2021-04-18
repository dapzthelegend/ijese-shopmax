package com.example.shopmax.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ShipmentService{


    companion object{

        const val GET_SHIPMENTS = "shipments"
        const val MAKE_SHIPMENT = "shipments"

    }


    @GET(GET_SHIPMENTS)
    suspend fun getShipments(): ShipmentResponse

    @POST(MAKE_SHIPMENT)
    suspend fun makeShipment(
        @Body shipmentRequest: ShipmentRequest
    ): ShipmentResponse


}