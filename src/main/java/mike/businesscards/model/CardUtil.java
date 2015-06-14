package mike.businesscards.model;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
@PropertySource("classpath:app.properties")
public class CardUtil {

    public static final String ID_CARD = "id";

    public static final String NAME_CARD = "name";

    public static final String CLOUD_NAME = "cloud_name";

    public static final String CLOUD_API_KEY = "api_key";

    public static final String CLOUD_API_SECRET = "api_secret";

    @Resource
    private Environment environment;

    public CardUtil() {}

    public Card setPropertyCard(User user, String json) {
        Card card = parseJson(new Card(), user, json);
        return card;
    }

    public Card updateProperty(Card card, User user, String json) {
        card.getElementsCard().clear();
        return parseJson(card, user, json);
    }

    public String sendImage(Integer idCard, String image) {
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(image.replaceAll("data:image/.+;base64,", ""));
        BufferedImage bufferedImage = null;
        String url = "/resources/cardLogo.jpg";
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(decodedBytes));
            File imageFile = File.createTempFile("BusinessCardHolder" + idCard, ".png");
            ImageIO.write(bufferedImage , "png", imageFile);
            bufferedImage.flush();
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    CLOUD_NAME, environment.getRequiredProperty("cloudinary.cloud_name"),
                    CLOUD_API_KEY, environment.getRequiredProperty("cloudinary.api_key"),
                    CLOUD_API_SECRET, environment.getRequiredProperty("cloudinary.api_secret")));
            Map uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap());
            url = (String) uploadResult.get("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    public void removeImage(String url) {
        try {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                CLOUD_NAME, environment.getRequiredProperty("cloudinary.cloud_name"),
                CLOUD_API_KEY, environment.getRequiredProperty("cloudinary.api_key"),
                CLOUD_API_SECRET, environment.getRequiredProperty("cloudinary.api_secret")));
            cloudinary.uploader().destroy(getPublicId(url), ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCardJson(Card card, JSONObject object) {
        try {
            object.put(NAME_CARD, card.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public String getPublicId(String url) {
        String[] tokens = url.split("/");
        String file = tokens[tokens.length-1];
        String name = file.substring(0, file.length()-4);
        return name;
    }

    private Card parseJson(Card card, User user, String json) {
        try {
            JSONObject object = new JSONObject(json);
            card.setName(object.getString(NAME_CARD));
            card.setUser(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return card;
    }
}


