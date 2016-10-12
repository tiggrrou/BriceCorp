//package com.wha.springmvc.dao;
//
//import java.util.List;
//
//import javax.persistence.NoResultException;
//
//import org.springframework.stereotype.Repository;
//
//import com.wha.springmvc.model.User;
//
//@Repository("userDoa")
//public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
//
//
//
//	@Override
//	public User findById(int id) {
//		User user = getByKey(id);
//		return user;
//	}
//
//	@Override
//	public User findByName(String name) {
//		System.out.println("name : " + name);
//
//		try {
//			User user = (User) getEntityManager().createQuery("SELECT u FROM User u WHERE u.username Like :name")
//					.setParameter("name", name).getSingleResult();
//			return user;
//		} catch (NoResultException ex) {
//			return null;
//		}
//
//	}
//
//	@Override
//	public void save(User user) {
//		persist(user);
//
//	}
//
//	@Override
//	public void deleteUserById(int id) {
//		User user = getByKey(id);
//		delete(user);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<User> findAllUsers() {
//		List<User> users = getEntityManager().createQuery("SELECT u FROM User u ORDER BY u.username ASC")
//				.getResultList();
//		return users;
//	}
//
//	@Override
//	public void deleteAllUsers() {
//		getEntityManager().createQuery("DELETE FROM USER").executeUpdate();
//	}
//
//}
