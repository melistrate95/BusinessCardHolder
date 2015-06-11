package mike.businesscards.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "elementsCard")
public class ElementCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_CARD")
    private Card card;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "FONT", nullable = false)
    private String font;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @Column(name = "BGCOLOR", nullable = false)
    private String bgColor;

    @Column(name = "WIDTH", nullable = false)
//    @Size(min = 0, max = 500)
    private Integer width;

    @Column(name = "HEIGHT", nullable = false)
//    @Size(min = 0, max = 300)
    private Integer height;

    @Column(name = "POSITION_X", nullable = false)
//    @Size(min = 0, max = 500)
    private Integer positionX;

    @Column(name = "POSITION_Y", nullable = false)
//    @Size(min = 0, max = 300)
    private Integer positionY;

    public ElementCard() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}
