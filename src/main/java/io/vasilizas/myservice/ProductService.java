package io.vasilizas.myservice;

import io.vasilizas.bean.db.Product;
import io.vasilizas.repositories.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("productService")
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final StringService stringService;

    private final CardService cardService;

    private final PrintService printService;

    public void getBill(String string, String card) {
        BigDecimal bill = BigDecimal.valueOf(0);
        List<Integer> list = stringService.getNumbersListFromString(string);
        List<Integer> idList = stringService.getIdListFromNumberList(list);
        List<Integer> countList = stringService.getCountListFromNumberList(list);
        printService.printStartAndFinishBill();
        for (int i = 0; i < idList.size(); i++) {
            var product = productRepository.findById(idList.get(i));
            if (check(product, countList, i)) {
                printService.noProductOrNotEnough();
            } else {
                var total = printService.printBill(product.orElseThrow(), countList, i);
                bill = bill.add(total);
            }
        }
        printService.printStartAndFinishBill();
        printService.printTotal(bill, cardService.getDiscount(card));
    }

    private boolean check(Optional<Product> product, List<Integer> countList, int i) {
        return product.isEmpty() || product.get().getCount() < countList.get(i);
    }
}
