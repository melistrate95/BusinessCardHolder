package mike.businesscards.dao;

import mike.businesscards.model.Card;
import mike.businesscards.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public class CardDaoImpl implements  CardDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void create(Card card) {
        sessionFactory.getCurrentSession().saveOrUpdate(card);
    }

    public Card getCardById(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Card WHERE id=:id");
        query.setInteger("id", id);
        Card card = (Card) query.uniqueResult();
        return card;
    }

    public Card getCardByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Card WHERE name=:name");
        query.setString("name", name);
        Card card = (Card) query.uniqueResult();
        return card;
    }

    public List<Card> listUserCard(Integer userId) {
        Query q = sessionFactory.getCurrentSession().createQuery("FROM User where id=:id");
        q.setInteger("id", userId);
        User user = (User) q.uniqueResult();
        Set<Card> set= user.getCards();
        List<Card> list = new ArrayList<Card>(set);
        return list;
    }

    public List<Card> listAllCard() {
        Query q = sessionFactory.getCurrentSession().createQuery("FROM Card");
        return q.list();
    }

    public void removeCard(Integer id) {
        Card card = (Card) sessionFactory.getCurrentSession().load(Card.class, id);
        if (null != card) {
            sessionFactory.getCurrentSession().delete(card);
        }
    }

    @Override
    public List<Card> search(String text) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("searchCards");
        query.setParameter("nameCard", "%" + text + "%");
        query.setParameter("textElement", "%" + text + "%");
        return query.list();
    }
}
