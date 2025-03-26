package com.kotlin_crud.config

import com.twilio.Twilio
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class TwilioConfiguration {

    @Value("\${twilio.account-sid}")
    private lateinit var accountSID:String

    @Value("\${twilio.auth-token}")
    private lateinit var authToken:String

    @Value("\${twilio.phone-number}")
    lateinit var phoneNumber:String

    @PostConstruct
    fun twilioInit(){
        Twilio.init(
            accountSID,
            authToken
        )
    }
}