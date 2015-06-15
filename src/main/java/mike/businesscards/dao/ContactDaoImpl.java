package mike.businesscards.dao;

import mike.businesscards.model.Contact;
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
public class ContactDaoImpl implements  ContactDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addContact(Contact contact, Integer userId) {
        User user = (User) this.sessionFactory.getCurrentSession().load(User.class, userId);
        contact.setUser(user);
        this.sessionFactory.getCurrentSession().saveOrUpdate(contact);
    }

    public Contact getContact(Integer userId, Integer contactId) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM Contact where id=:id and user=:user");
        q.setInteger("id", contactId);
        q.setInteger("user", userId);
        return (Contact) q.uniqueResult();
    }

    public List<Contact> listUserContact(Integer userId) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM User where id=:id");
        q.setInteger("id", userId);
        User user = (User) q.uniqueResult();
        Set<Contact> set= user.getContacts();
        List<Contact> list = new ArrayList<Contact>(set);
        return list;
    }

    public List<Contact> listAllContact() {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM Contact");
        return q.list();
    }

    public void removeContact(Integer id) {
        Contact contact = (Contact) this.sessionFactory.getCurrentSession().load(Contact.class, id);
        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }
}
