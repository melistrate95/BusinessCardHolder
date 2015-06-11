package mike.businesscards.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Mike on 01/06/2015.
 */

@Entity
@Table(name = "jobs")
public class Jobs implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Size(min=3, max=16)
    @Column(name = "COMPANY", nullable = false)
    private String company;

    @NotNull
    @Size(min=3, max=16)
    @Column(name = "POST", nullable = false)
    private String post;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Jobs(User user, String company, String post) {
        this.user = user;
        this.company = company;
        this.post = post;
    }

    public Jobs() {
    }
}
