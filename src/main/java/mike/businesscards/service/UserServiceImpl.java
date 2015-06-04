package mike.businesscards.service;

import mike.businesscards.dao.UserDao;
import mike.businesscards.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mike on 02/06/2015.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    public List<User> listAll() {
        return userDao.listAll();
    }

    @Transactional
    public void removeUser(Integer id) {
        userDao.removeUser(id);
    }

    @Transactional
    public boolean findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Transactional
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public boolean findUserByEmailAndPassword(String email, String password) {
        return userDao.findUserByEmailAndPassword(email, password);
    }

    @Transactional
    public int getIdByEmailAndPassword(String email, String password) {
        return userDao.getIdByEmailAndPassword(email, password);
    }

    @Transactional
    public boolean findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
