package com.example.Disaster.Management.Controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @PostMapping("/send")
    public String sendSms(@RequestParam String to, @RequestParam String body) {
        Twilio.init(accountSid, authToken); // Initialize Twilio here

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                body
        ).create();

        return "Message sent with SID: " + message.getSid();
    }
}
