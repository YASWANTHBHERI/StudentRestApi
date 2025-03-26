package com.kotlin_crud.services.twilioservice

import com.kotlin_crud.config.TwilioConfiguration
import com.kotlin_crud.services.twilioservice.impl.TwilioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class SMSService:TwilioService {

    private var logger: Logger = LoggerFactory.getLogger(SMSService::class.java)

    @Autowired
    private lateinit var twilioConfig:TwilioConfiguration
    override fun sendSingleSMS(toPhoneNumber: String, message: String) {
       try {
           val from = PhoneNumber(twilioConfig.phoneNumber)
           val to = PhoneNumber(toPhoneNumber)
           val messageCreator = Message.creator(
               to,
               from,
               message
           ).create()
           logger.info("sms sent... {}",messageCreator)
           println(messageCreator)

       } catch (e:Exception){
           logger.info("SMS sent failed due to: {}",e.message)
       }





    }

}
