package mike.businesscards.service;

import mike.businesscards.model.Contact;

import java.util.List;

/**
 * Created by Mike on 02/06/2015.
 */
public interface ContactService {

    public void addContact(Contact contact, Integer userId);

    public Contact getContact(Integer userId, Integer contactId);

    public List<Contact> listUserContact(Integer userId);

    public List<Contact> listAllContact();

    public void removeContact(Integer id);
}
