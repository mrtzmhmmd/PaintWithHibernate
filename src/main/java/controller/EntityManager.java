package controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Shape;
import model.User;

public class EntityManager {
	private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

	public static User getUser(String username, String password) {
		Session session = factory.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = session.load(User.class, username);
			tx.commit();
			if (!(user.equals(null))) {
				if (user.getPassword().equals(password))
					return user;
			}
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public static boolean addUser(User user) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			return true;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public static void saveShape(Shape shape) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(shape);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
}