package io.vasilizas.myservice;

import io.vasilizas.bean.db.Product;
import io.vasilizas.repositories.jpa.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public boolean checkProductAndCount(Optional<Product> product, List<Integer> countList, int i) {
        return product.isEmpty() || product.get().getCount() == 0 || product.get().getCount() < countList.get(i) ;
    }

    public Product setCountProduct(Product product, List<Integer> countList, int i, ProductRepository productRepository) {
        int j = product.getCount() - countList.get(i);
        product.setCount(j);
        productRepository.save(product);
        return product;
    }
}
