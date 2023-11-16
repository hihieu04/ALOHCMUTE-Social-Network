package Dao;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.User;
import JpaConfig.JPAConfig;

public class UserDAOImpl implements IUserDAO{
	@Override
	public User findUser(int userID) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.userID = :userID", User.class);
        query.setParameter("userID", userID);
        
        return query.getSingleResult();
	}

	@Override
	public List<User> SortUserByName(List<User> list) {
		Collections.sort(list, Comparator.comparing(User::getFirstName));
		return list;
	}

	@Override
	public List<User> SortUserByWorkplace(List<User> list) {
		Collections.sort(list, Comparator.comparing(User::getWorkPlace));
		return list;
	}
}
