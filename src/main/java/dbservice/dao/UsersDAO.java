package dbservice.dao;

import dbservice.datasets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


/**
 * Created by mk-orzu on 09.01.2017.
 */
public class UsersDAO {
    private Session session;
    public UsersDAO(Session session) {
        this.session = session;
    }



    public UsersDataSet get(long id) throws HibernateException{
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public Long getUserId(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet userDS = (UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        if (userDS != null){
            return userDS.getId();
        }else {
            throw new HibernateException("The row with '"+login+"' isn't found in the table");
        }
    }

    public long insertUser(String login, String password)throws HibernateException{
        return (Long) session.save(new UsersDataSet(login, password));
    }

}
