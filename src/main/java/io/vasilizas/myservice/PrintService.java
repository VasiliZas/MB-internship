package io.vasilizas.myservice;

import io.vasilizas.bean.db.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PrintService {

    private static final Logger log = LoggerFactory.getLogger(PrintService.class);

    public void printStartAndFinishBill() {
        log.info("********************************************************************************");
    }
    public void noProductOrNotEnough() {
        log.info("--------------------------------------------------------------------------------");
        log.info("There is no such product or its quantity is insufficient.");
    }

    public BigDecimal printBill(Product product, List<Integer> countList, int i) {
        var price = product.getPrice();
        var countForBill = BigDecimal.valueOf(countList.get(i));
        var name = product.getName();
        var count = countList.get(i);
        var total = price.multiply(countForBill);
        log.info("--------------------------------------------------------------------------------");
        log.info("Your product: {} , price product: {} EUR, count product: {} , total: {} EUR", name, price, count, total);
        return total;
    }

    public void printTotal(BigDecimal bill, double discount){
        log.info("Your discount : {} % , {} EUR ", discount, bill.multiply(BigDecimal.valueOf(discount / 100)));
        log.info("Your total bill: {} EUR", bill.multiply(BigDecimal.valueOf(1 - discount / 100)));
    }
}
