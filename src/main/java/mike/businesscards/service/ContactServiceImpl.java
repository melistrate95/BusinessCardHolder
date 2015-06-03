package mike.businesscards.service;

import mike.businesscards.dao.ContactDao;
import mike.businesscards.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mike on 02/06/2015.
 */
@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDao contactDao;

    @Transactional
    public void addContact(Contact contact, Integer userId) {
        contactDao.addContact(contact, userId);
    }

    @Transactional
    public List<Contact> listUserContact(Integer userId) {
        return contactDao.listUserContact(userId);
    }

    @Transactional
    public List<Contact> listAllContact() {
        return contactDao.listAllContact();
    }

    @Transactional
    public void removeContact(Integer id) {
        contactDao.removeContact(id);
    }
}
