package mike.businesscards.dao;

import mike.businesscards.model.Card;
import mike.businesscards.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Mike on 27/05/2015.
 */

@Repository
@Transactional
public class CardDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCard(Card card, Integer userId) {
        User user = (User) this.sessionFactory.getCurrentSession().load(User.class, userId);
        card.setUser(user);
        this.sessionFactory.getCurrentSession().saveOrUpdate(card);
    }

    public boolean findCardByName(String name) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM Card where name=:name");
        q.setString("name", name);
        Card card = (Card) q.uniqueResult();
        if (null != card) {
            return true;
        }
        return false;
    }

    public Card getCardByName(String name) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Card WHERE name=:name");
        query.setString("name", name);
        Card card = (Card) query.uniqueResult();
        return card;
    }

    public List<Card> listUserCard(Integer userId) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM User where id=:id");
        q.setInteger("id", userId);
        User user = (User) q.uniqueResult();
        Set<Card> set= user.getCards();
        List<Card> list = new ArrayList<Card>(set);
        return list;
    }

    public List<Card> listAllCard() {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM Card");
        return q.list();
    }

    public void removeCard(Integer id) {
        Card card = (Card) this.sessionFactory.getCurrentSession().load(Card.class, id);
        if (null != card) {
            this.sessionFactory.getCurrentSession().delete(card);
        }
    }
}
