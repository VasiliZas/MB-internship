package io.vasilizas.bean.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "usr")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    private LocalDateTime lastVisit;

    @Override
    public String toString() {
        return "User {" +
                "id = '" + id + '\'' +
                ", name = '" + name + '\'' +
                ", userpic = '" + userpic + '\'' +
                ", email = '" + email + '\'' +
                ", gender = '" + gender + '\'' +
                ", locale = '" + locale + '\'' +
                ", lastVisit = " + lastVisit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(userpic, user.userpic) && Objects.equals(email, user.email) && Objects.equals(gender, user.gender) && Objects.equals(locale, user.locale) && Objects.equals(lastVisit, user.lastVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userpic, email, gender, locale, lastVisit);
    }
}
