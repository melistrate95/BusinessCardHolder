package mike.businesscards.service;

import mike.businesscards.dao.ElementCardDao;
import mike.businesscards.model.Card;
import mike.businesscards.model.ElementCard;
import mike.businesscards.model.ElementCardUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ElementCardServiceImpl implements ElementCardService {

    @Autowired
    private ElementCardDao elementCardDao;

    @Autowired
    private ElementCardUtil elementCardUtil;

    @Override
    @Transactional
    public void create(ElementCard elementCard) {
        elementCardDao.create(elementCard);
    }

    @Override
    @Transactional
    public void addElementsCard(Card card, String json) {
        List<ElementCard> elements = elementCardUtil.getElementCard(json);
        for (ElementCard elementCard : elements) {
            elementCard.setCard(card);
            create(elementCard);
        }
    }

    @Override
    public JSONObject getElementsCardJson(List<ElementCard> elements) {
        return  elementCardUtil.getElementsCardJson(elements);
    }

    @Override
    @Transactional
    public void remove(ElementCard elementCard) {
        elementCardDao.remove(elementCard);
    }

    @Override
    @Transactional
    public void removeAll(List<ElementCard> elementsCard) {
        for (ElementCard elementCard: elementsCard) {
            remove(elementCard);
        }
    }


}
