package io.vasilizas.controller;


import io.vasilizas.myservice.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller

public class SpringController {

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String homePage() {
        return "index";
    }

    @PostMapping("/getbill")
    public String getBill(@RequestParam(value = "product", required = false) String product,
                          @RequestParam(value = "card", required = false) String card) {
        productService.getBill(product, card);
        return "redirect:/home";
    }
}
