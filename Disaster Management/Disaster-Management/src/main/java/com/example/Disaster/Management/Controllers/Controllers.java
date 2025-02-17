package com.example.Disaster.Management.Controllers;

import com.example.Disaster.Management.Repositories.EmailRepository;
import com.example.Disaster.Management.Repositories.IncidentRepository;
import com.example.Disaster.Management.Services.EmailService;
import com.example.Disaster.Management.Tables.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Controllers {

    @Autowired
    EmailService emailService;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    IncidentRepository incidentRepository;

    @GetMapping("/")
    public String login(){
        return "Login";
    }

    @PostMapping("/alert")

    public ResponseEntity<String> alert(@RequestParam("name") String name,
                                        @RequestParam("desc") String desc,
                                        @RequestParam("latitude") BigDecimal latitude,
                                        @RequestParam("longitude") BigDecimal longitude) {
        // Fetch all email addresses from the repository
        List<Emails> list1 = emailRepository.findAll().stream().toList();
        List<String> list = new ArrayList<>();
        for (Emails emails : list1) {
            list.add(emails.getMail());
        }

        // Call the email service to send the alert
        emailService.sendAlertEmail(list, desc, name, latitude, longitude);

        // Redirect to the /users/ page
        String htmlResponse = "<script>window.location.href='/users/';</script>";
        return ResponseEntity.ok(htmlResponse);
    }

//    public ResponseEntity<String> alert(@RequestParam("name") String name , @RequestParam("desc") String desc){
//        List<Emails> list1 = emailRepository.findAll().stream().toList();
//        List<String> list = new ArrayList<>();
//        for(Emails emails:list1){
//            list.add(emails.getMail());
//        }
//        emailService.sendAlertEmail(list,desc,"1",);
//        String htmlResponse = "<script>window.location.href='/users/';</script>";
//        return  ResponseEntity.ok(htmlResponse);
//    }

}
