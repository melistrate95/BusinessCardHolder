package mike.businesscards.service;

import mike.businesscards.model.Card;

import java.util.List;

/**
 * Created by Mike on 02/06/2015.
 */
public interface CardService {

    public void addCard(Card card, Integer userId);

    public boolean findCardByName(String name);

    public Card getCardByName(String name);

    public List<Card> listUserCard(Integer userId);

    public List<Card> listAllCard();

    public void removeCard(Integer id);
}
