package io.vasilizas.controller;


import io.vasilizas.bean.db.MyClient;
import io.vasilizas.myservice.BillService;
import io.vasilizas.repositories.jpa.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Slf4j
@Controller

public class SpringController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BillService billService;

    @GetMapping("/getclientinfo")
    public String getClientInfo(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient user) {

        var clientRegistration = user.getClientRegistration();
        if (clientRepository.findById(clientRegistration.getClientId()).isEmpty()) {
            MyClient client = new MyClient();
            client.setId(clientRegistration.getClientId());
            client.setName(clientRegistration.getClientName());
            client.setRedirectUri(clientRegistration.getRedirectUri());
            client.setClientSecret(clientRegistration.getClientSecret());
            client.setLastVisit(LocalDateTime.now());
            clientRepository.save(client);
        }
        return "redirect:/home";
    }

    @GetMapping("/")
    public String startPage() {
        return "start";
    }

    @GetMapping("/home")
    public String homePage() {
        return "index";
    }

    @PostMapping("/getbill")
    public String getBill(@RequestParam(value = "product", required = false) String product,
                          @RequestParam(value = "card", required = false) String card) {
        billService.getBill(product, card);
        return "redirect:/home";
    }
}
