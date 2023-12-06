package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Entity.HiringPost;
import Entity.User;
import JpaConfig.JPAConfig;

public class UserDAOImpl implements IUserDAO {
	@Override
	public User findUser(int userID) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.userID = :userID", User.class);
		query.setParameter("userID", userID);

		return query.getSingleResult();
	}

	@Override
	public void insert(User user) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public User findUser(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		User entity = enma.find(User.class, id);
		return entity;
	}

	@Override
	public List<User> findAllChat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUserChat(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteChat(User user) {
		// TODO Auto-generated method stub

	}
}
