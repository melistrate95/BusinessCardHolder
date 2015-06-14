package mike.businesscards.service;

import mike.businesscards.model.Card;
import mike.businesscards.model.ElementCard;
import org.json.JSONObject;

import java.util.List;

public interface ElementCardService {

    public void create(ElementCard elementCard);

    public void addElementsCard(Card card, String json);

    public JSONObject getElementsCardJson(List<ElementCard> elements);

    public void remove(ElementCard elementCard);

    public void removeAll(List<ElementCard> elementsCard);
}
