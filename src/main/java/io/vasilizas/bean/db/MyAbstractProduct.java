package io.vasilizas.bean.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class MyAbstractProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;


    public MyAbstractProduct withId(Integer id) {
        setId(id);
        return this;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name ='" + name + '\'' +
                ", count =" + count +
                ", price =" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyAbstractProduct that = (MyAbstractProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(count, that.count)
                && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, count, price);
    }
}
