package mike.businesscards.service;

import mike.businesscards.dao.CardDao;
import mike.businesscards.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mike on 02/06/2015.
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao cardDao;

    @Transactional
    public void addCard(Card card, Integer userId) {
        cardDao.addCard(card, userId);
    }

    @Transactional
    public boolean findCardByName(String name) {
        return cardDao.findCardByName(name);
    }

    @Transactional
    public Card getCardByName(String name) {
        return cardDao.getCardByName(name);
    }

    @Transactional
    public List<Card> listUserCard(Integer userId) {
        return cardDao.listUserCard(userId);
    }

    @Transactional
    public List<Card> listAllCard() {
        return cardDao.listAllCard();
    }

    @Transactional
    public void removeCard(Integer id) {
        cardDao.removeCard(id);
    }
}
