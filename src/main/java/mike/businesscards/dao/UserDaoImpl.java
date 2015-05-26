package mike.businesscards.dao;

import mike.businesscards.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mike on 10/05/2015.
 */
@Repository
@Transactional
public class UserDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    public List<User> listAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public void removeUser(Integer id) {
        User user = (User) this.sessionFactory.getCurrentSession().load(User.class, id);
        if (null != user) {
            this.sessionFactory.getCurrentSession().delete(user);
        }
    }

    public boolean findUserById(Integer id) {
        User user = (User) (this.sessionFactory.getCurrentSession().load(User.class, id));
        if (null != user) {
            return true;
        }
        return false;
    }

    public User getUserById(Integer id) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM User where id=:id");
        q.setInteger("id", id);
        User user = (User) q.uniqueResult();
        return user;
    }

    public boolean findUserByEmailAndPassword(String email, String password) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM User where mail=:mail and password=:password");
        q.setString("mail", email);
        q.setString("password", password);
        User user = (User) q.uniqueResult();
        if (null != user) {
            return true;
        }
        return false;
    }

    public int getIdByEmailAndPassword(String email, String password) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM User where mail=:mail and password=:password");
        q.setString("mail", email);
        q.setString("password", password);
        User user = (User) q.uniqueResult();
        return user.getId();
    }

    public boolean findUserByEmail(String email) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM User where mail=:mail");
        q.setString("mail", email);
        User user = (User) q.uniqueResult();
        if (null != user) {
            return true;
        }
        return false;
    }

    public User getUserByEmail(String email) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM User where mail=:mail");
        q.setString("mail", email);
        User user = (User) q.uniqueResult();
        return user;
    }
}
