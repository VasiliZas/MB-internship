package io.vasilizas.bean.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "client")
@Data
public class MyClient {
    @Id
    private String id;
    private String name;
    private String redirectUri;
    private String clientSecret;
    private String registrationId;
    private LocalDateTime lastVisit;

    @Override
    public String toString() {
        return "MyClient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", lastVisit=" + lastVisit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClient myClient = (MyClient) o;
        return Objects.equals(id, myClient.id) && Objects.equals(name, myClient.name) && Objects.equals(redirectUri, myClient.redirectUri) && Objects.equals(clientSecret, myClient.clientSecret) && Objects.equals(registrationId, myClient.registrationId) && Objects.equals(lastVisit, myClient.lastVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, redirectUri, clientSecret, registrationId, lastVisit);
    }
}
