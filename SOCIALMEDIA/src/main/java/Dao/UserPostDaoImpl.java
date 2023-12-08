package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Entity.LikeUserPost;
import Entity.User;
import Entity.UserPost;
import JpaConfig.JPAConfig;
import Model.UserPostModel;

public class UserPostDaoImpl implements IUserPostDao {

	public static void main(String[] args) {
//		System.out.println(new UserPostDaoImpl().paginationPage(12, 6));
		List<UserPost> list = new UserPostDaoImpl().paginationPostUser(1, 4, "mxFasgmO8bSvQtpiHqNUG9WrEai1");
		System.out.println(list.size());
		for (UserPost p : list) {
			System.out.println(p);
		}

	}

	@Override
	public List<UserPost> paginationPage(int index, int numberOfPage) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		TypedQuery<UserPost> query = entityManager.createNamedQuery("UserPost.findAll", UserPost.class);
		query.setFirstResult(index*numberOfPage); //them *numberOfPage
		query.setMaxResults(numberOfPage);
		return query.getResultList();
	}
	//hieu-begin
	@Override
	public void update(UserPost userPost) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(userPost);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}
	@Override
	public void delete(int userPostID){
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			UserPost userPost = enma.find(UserPost.class, userPostID);
			if(userPost != null) {
				enma.remove(userPost);
			}
			else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}finally {
			enma.close();
		}
	}
	@Override
	public Long countAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Long> count = enma.createQuery("select count(p) from UserPost p", Long.class);
		return count.getSingleResult();
	}
	//hieu-end
	
	@Override
	public List<UserPost> paginationPostUser(int index, int numberOfPage, String uid) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		String jpqlQuery = "SELECT up FROM User u " + "JOIN u.followingUsers fu " + "JOIN fu.userPosts up "
				+ "WHERE u.userID = :userId " + "ORDER BY up.UserPostCreateTime DESC";
//		String jpqlQuery = "SELECT DISTINCT up FROM User u " + "LEFT JOIN u.followingUsers fu "
//				+ "LEFT JOIN u.userPosts up " + "LEFT JOIN fu.userPosts followedPosts "
//				+ "WHERE u.userID = :userId OR fu.userID = :userId "
//				+ "ORDER BY COALESCE(up.UserPostCreateTime, followedPosts.UserPostCreateTime) DESC";
		List<UserPost> userFollowedPosts = entityManager.createQuery(jpqlQuery, UserPost.class)
				.setParameter("userId", uid).setFirstResult(index).setMaxResults(numberOfPage) 
				.getResultList();

		return userFollowedPosts;
	}
//	String jpqlQuery = "SELECT DISTINCT up FROM User u " +
//            "LEFT JOIN u.followingUsers fu " +
//            "LEFT JOIN u.userPosts up " +
//            "LEFT JOIN fu.userPosts followedPosts " +
//            "WHERE u.userID = :userId OR fu.userID = :userId " +
//            "ORDER BY COALESCE(up.UserPostCreateTime, followedPosts.UserPostCreateTime) DESC";
//
//List<UserPost> userFollowedPosts = entityManager.createQuery(jpqlQuery, UserPost.class)
// .setParameter("userId", userId)
// .getResultList();

	@Override
	public LikeUserPost findLikeUserPost(int userPostID) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		TypedQuery<LikeUserPost> query = entityManager.createQuery("SELECT u FROM LikeUserPost u WHERE u.userPostID = :userPostID", LikeUserPost.class);
		query.setParameter("userPostID", userPostID);
		System.out.println(userPostID);
		return query.getSingleResult();
	}

	@Override
	public Long countLike(int userPostLike) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Long> count = enma.createQuery("select count(l) from LikeUserPost l WHERE l.userPostID = :userPostID", Long.class);
		return count.getSingleResult();
	}
	@Override
	public void insertUserLikePost(LikeUserPost likePost) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(likePost);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			entityManager.close();
		}
	}
	//hieu end

}
