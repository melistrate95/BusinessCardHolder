package mike.businesscards.service;

import mike.businesscards.model.Card;

import java.util.List;

public interface CardService {

    public void addCard(Card card, Integer userId);

    public Card getCardByName(String name);

    public List<Card> listUserCard(Integer userId);

    public List<Card> listAllCard();

    public void removeCard(Integer id);
}
