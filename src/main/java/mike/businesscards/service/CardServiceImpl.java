package mike.businesscards.service;

import mike.businesscards.dao.CardDao;
import mike.businesscards.model.Card;
import mike.businesscards.model.CardUtil;
import mike.businesscards.model.ElementCard;
import mike.businesscards.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao cardDao;

    @Autowired
    private CardUtil cardUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private ElementCardService elementCardService;

    public CardServiceImpl() {}

    @Override
    @Transactional
    public Card getCardById(Integer id) {
        return cardDao.getCardById(id);
    }

    @Override
    @Transactional
    public Integer create(String json) {
        User user = userService.getUserByEmail(userSessionService.getUsername());
        Card card = cardUtil.setPropertyCard(user, json);
        cardDao.create(card);
        elementCardService.addElementsCard(card, json);
        return card.getId();
    }

    @Override
    @Transactional
    public Integer update(Integer idCard, String json) {
        User user = userService.getUserByEmail(userSessionService.getUsername());
        Card card = cardDao.getCardById(idCard);
        elementCardService.removeAll(card.getElementsCard());
        card = cardUtil.updateProperty(card, user, json);
        cardDao.create(card);
        elementCardService.addElementsCard(card, json);
        return idCard;
    }

    @Override
    @Transactional
    public String saveCardImage(Integer idCard, String image) {
        String url = cardUtil.sendImage(idCard, image);
        Card card = cardDao.getCardById(idCard);
        card.setUrl(url);
        return url;
    }

    @Override
    @Transactional
    public String updateCardImage(Integer idCard, String image) {
        Card card = getCardById(idCard);
        String url = card.getUrl();
        cardUtil.removeImage(url);
        return saveCardImage(idCard, image);
    }

    @Override
    @Transactional
    public String getCardByIdJson(Integer id) {
        Card card = getCardById(id);
        return cardUtil.getCardJson(card, elementCardService.getElementsCardJson(card.getElementsCard()));
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
        Card card = getCardById(id);
        String url = card.getUrl();
        elementCardService.removeAll(card.getElementsCard());
        cardDao.removeCard(id);
        cardUtil.removeImage(url);
    }
}
