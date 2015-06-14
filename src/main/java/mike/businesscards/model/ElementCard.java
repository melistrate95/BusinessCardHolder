package mike.businesscards.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

//    @NotNull
//    @Size(min=1, max=50)
    @Column(name = "TEXT")
    private String text;

//    @NotNull
//    @Size(min=1, max=50)
    @Column(name = "FONT", nullable = false)
    private String font;

//    @NotNull
//    @Size(min=1, max=50)
    @Column(name = "COLOR", nullable = false)
    private String color;

//    @NotNull
//    @Size(min=1, max=50)
    @Column(name = "BGCOLOR", nullable = false)
    private String bgColor;

//    @NotNull
//    @Min(0)
//    @Max(500)
    @Column(name = "WIDTH", nullable = false)
    private Integer width;

//    @NotNull
//    @Min(0)
//    @Max(500)
    @Column(name = "HEIGHT", nullable = false)
    private Integer height;

//    @NotNull
//    @Min(0)
//    @Max(500)
    @Column(name = "POSITION_X", nullable = false)
    private Integer positionX;

//    @NotNull
//    @Min(0)
//    @Max(500)
    @Column(name = "POSITION_Y", nullable = false)
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
