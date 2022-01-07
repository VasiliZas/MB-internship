package io.vasilizas.controller;


import io.vasilizas.bean.db.MyClient;
import io.vasilizas.bean.db.User;
import io.vasilizas.myservice.BillService;
import io.vasilizas.repositories.jpa.ClientRepository;
import io.vasilizas.repositories.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SpringController {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final BillService billService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/getclientinfo")
    public String getClientInfo(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient user) {
        var clientRegistration = user.getClientRegistration();
        var myClient = clientRepository.findById(clientRegistration.getClientId()).orElseGet(() -> {
            MyClient client = new MyClient();
            client.setId(clientRegistration.getClientId());
            client.setName(clientRegistration.getClientName());
            client.setRedirectUri(clientRegistration.getRedirectUri());
            client.setClientSecret(clientRegistration.getClientSecret());
            client.setRegistrationId((clientRegistration.getRegistrationId()));
            return client;
        });
        myClient.setLastVisit(LocalDateTime.now());
        clientRepository.save(myClient);
        return "redirect:/home";
    }

    @GetMapping("/loginSuccess")
    public String getLoginInfo(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());
        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();

        if (!isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                    .getTokenValue());
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map userAttributes = response.getBody();
            String id = (String) userAttributes.get("sub");
            User user = userRepository.findById(id).orElseGet(() -> {
                User newUser = new User();
                newUser.setName((String) userAttributes.get("name"));
                newUser.setId(id);
                newUser.setEmail((String) userAttributes.get("email"));
                newUser.setUserpic((String) userAttributes.get("picture"));
                newUser.setLocale((String) userAttributes.get("locale"));
                return newUser;
            });
            user.setLastVisit(LocalDateTime.now());
            userRepository.save(user);
        }
        return "redirect:/getclientinfo";
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
