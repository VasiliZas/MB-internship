package io.vasilizas.myservice;

import io.vasilizas.myannotation.MyPrintAnnotation;
import io.vasilizas.repositories.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final ProductRepository productRepository;

    private final StringService stringService;

    private final CardService cardService;

    private final PrintService printService;

    private final ProductService productService;

    @MyPrintAnnotation
    public void getBill(String string, String card) {
        BigDecimal bill = BigDecimal.valueOf(0);
        List<Integer> list = stringService.getNumbersListFromString(string);
        List<Integer> idList = stringService.getIdListFromNumberList(list);
        List<Integer> countList = stringService.getCountListFromNumberList(list);
        for (int i = 0; i < idList.size(); i++) {
            var product = productRepository.findById(idList.get(i));
            if (productService.checkProductAndCount(product, countList, i)) {
                printService.noProductOrNotEnough();
            } else {
                var total = printService.printBill(product.orElseThrow(), countList, i);
                bill = bill.add(total);
            }
        }
        printService.printTotal(bill, cardService.getDiscount(card));
    }
}
