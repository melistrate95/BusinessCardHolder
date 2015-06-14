package mike.businesscards.service;

import mike.businesscards.model.Card;

import java.util.List;

public interface CardService {

    public Card getCardById(Integer id);

    public Integer create(String json);

    public String saveCardImage(Integer idCard, String image);

    public String getCardByIdJson(Integer id);

    public List<Card> listUserCard(Integer userId);

    public List<Card> listAllCard();

    public void removeCard(Integer id);

    public Integer update(Integer idCard, String json);

    public String updateCardImage(Integer idCard, String image);
}
