package com.croby.gcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.croby.gcp.GcpPubSubProviderApplication.PubsubOutboundGateway;

@RestController
public class PubSubController {

    @Autowired
    private PubsubOutboundGateway messasingGateway;

    @PostMapping("/publishMessage")
    public ResponseEntity<?> publishMesagge(@RequestParam("mesagge") String mesagge) {
        messasingGateway.sendToPubsub(mesagge);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
