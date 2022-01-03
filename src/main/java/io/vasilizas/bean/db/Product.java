package io.vasilizas.bean.db;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "product")
public class Product extends MyAbstractProduct {


    public Product withId(int id) {
        setId(id);
        return this;
    }

    public Product withCount(Integer count) {
        setCount(count);
        return this;
    }

    public Product withName(String name) {
        setName(name);
        return this;
    }

    public Product withPrice(BigDecimal price) {
        setPrice(price);
        return this;
    }

    @Override
    public String toString() {
        return "Product {} " + super.toString();
    }
}