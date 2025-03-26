package com.kotlin_crud.services.twilioservice.impl


interface TwilioService {

    fun sendSingleSMS(toPhoneNumber: String,message: String)
}