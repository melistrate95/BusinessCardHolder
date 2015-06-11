package mike.businesscards.dao;

import mike.businesscards.model.ElementCard;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElementCardDaoImpl implements ElementCardDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(ElementCard elementCard) {
        sessionFactory.getCurrentSession().saveOrUpdate(elementCard);
    }
}
