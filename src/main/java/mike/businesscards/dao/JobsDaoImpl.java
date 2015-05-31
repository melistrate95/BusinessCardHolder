package mike.businesscards.dao;

import mike.businesscards.model.Jobs;
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
 * Created by Mike on 01/06/2015.
 */

@Repository
@Transactional
public class JobsDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public void addJob(Jobs contact, Integer userId) {
        User user = (User) this.sessionFactory.getCurrentSession().load(User.class, userId);
        contact.setUser(user);
        this.sessionFactory.getCurrentSession().saveOrUpdate(contact);
    }

    public List<Jobs> listUserJobs(Integer userId) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM User where id=:id");
        q.setInteger("id", userId);
        User user = (User) q.uniqueResult();
        Set<Jobs> set= user.getJobs();
        List<Jobs> list = new ArrayList<Jobs>(set);
        return list;
    }

    public List<Jobs> listAllJobs() {
        Query q = this.sessionFactory.getCurrentSession().createQuery("FROM Jobs");
        return q.list();
    }

    public void removeContact(Integer id) {
        Jobs jobs = (Jobs) this.sessionFactory.getCurrentSession().load(Jobs.class, id);
        if (null != jobs) {
            this.sessionFactory.getCurrentSession().delete(jobs);
        }
    }
}
