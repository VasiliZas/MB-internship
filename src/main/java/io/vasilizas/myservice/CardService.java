package io.vasilizas.myservice;

import io.vasilizas.bean.db.Card;
import io.vasilizas.repositories.jpa.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final Card defaultCard = new Card(0, 0);

    private final CardRepository cardRepository;

    public Integer getDiscount(String cardNumber) {
        if (cardNumber.isEmpty()) {
            return defaultCard.getDiscount();
        }
        var card = cardRepository.findById(Integer.parseInt(cardNumber)).orElse(defaultCard);
        return card.getDiscount();
    }
}
