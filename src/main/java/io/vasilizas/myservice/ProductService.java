package io.vasilizas.myservice;

import io.vasilizas.bean.db.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public boolean checkProductAndCount(Optional<Product> product, List<Integer> countList, int i) {
        return product.isEmpty() || product.get().getCount() < countList.get(i);
    }
}
