package mike.businesscards.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mike on 27/05/2015.
 */

@Entity
@Table(name = "contacts")
public class Contact  implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    public Contact(User user, String type, String content) {
        this.user = user;
        this.type = type;
        this.content = content;
    }


    public Contact() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
