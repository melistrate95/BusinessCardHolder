package mike.businesscards.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cards")
@NamedQuery(name = "searchCards",
    query = "SELECT DISTINCT c FROM Card c JOIN c.elementsCard el WHERE c.name LIKE :nameCard OR el.text LIKE :textElement ")
public class Card implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URL")
    private String url;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ElementCard> elementsCard;

    public Card() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ElementCard> getElementsCard() {
        return elementsCard;
    }

    public void setElementsCard(List<ElementCard> elementsCard) {
        this.elementsCard = elementsCard;
    }

}
