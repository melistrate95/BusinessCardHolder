package mike.businesscards.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ElementCardUtil {

    public static final String ELEMENTS_CARD = "contactCards";

    public static final String TEXT_ELEMENT = "text";

    public static final String FONT_ELEMENT = "font";

    public static final String COLOR_ELEMENT = "color";

    public static final String BGCOLOR_ELEMENT = "bgcolor";

    public static final String WIDTH_ELEMENT = "width";

    public static final String HEIGHT_ELEMENT = "height";

    public static final String POSITION_X_ELEMENT = "xposition";

    public static final String POSITION_Y_ELEMENT = "yposition";

    public ElementCardUtil() {}

    public List<ElementCard> getElementCard(String json) {
        List<ElementCard> elements = Collections.emptyList();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = (JSONArray) object.get(ELEMENTS_CARD);
            elements = new ArrayList<ElementCard>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject element = (JSONObject) array.get(i);
                ElementCard elementCard = new ElementCard();
                elementCard.setText(element.getString(TEXT_ELEMENT));
                elementCard.setFont(element.getString(FONT_ELEMENT));
                elementCard.setColor(element.getString(COLOR_ELEMENT));
                elementCard.setBgColor(element.getString(BGCOLOR_ELEMENT));
                elementCard.setWidth(element.getInt(WIDTH_ELEMENT));
                elementCard.setHeight(element.getInt(HEIGHT_ELEMENT));
                elementCard.setPositionX(element.getInt(POSITION_X_ELEMENT));
                elementCard.setPositionY(element.getInt(POSITION_Y_ELEMENT));
                elements.add(elementCard);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  elements;
    }

    public JSONObject getElementsCardJson(List<ElementCard> elements) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            for (ElementCard elementCard : elements) {
                JSONObject element = new JSONObject();
                element.put(TEXT_ELEMENT, elementCard.getText());
                element.put(FONT_ELEMENT, elementCard.getFont());
                element.put(COLOR_ELEMENT, elementCard.getColor());
                element.put(BGCOLOR_ELEMENT, elementCard.getBgColor());
                element.put(WIDTH_ELEMENT, elementCard.getWidth());
                element.put(HEIGHT_ELEMENT, elementCard.getHeight());
                element.put(POSITION_X_ELEMENT, elementCard.getPositionX());
                element.put(POSITION_Y_ELEMENT, elementCard.getPositionY());
                array.put(element);
            }
            object.put(ELEMENTS_CARD, array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
