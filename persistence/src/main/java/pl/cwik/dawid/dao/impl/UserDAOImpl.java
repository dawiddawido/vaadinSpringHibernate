package pl.cwik.dawid.dao.impl;

import pl.cwik.dawid.dao.UserDAO;
import pl.cwik.dawid.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Dawid on 03.11.2017.
 */
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void save(User user) {
        System.out.println(user);
        //TODO
    }

    public List<User> findAll() {
        CriteriaQuery<User> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
