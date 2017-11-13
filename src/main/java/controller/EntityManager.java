package controller;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Circle;
import model.Line;
import model.Rectangle;
import model.RoundRectangle;
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

	public static ArrayList<Shape> loadShape(User user) {
		ArrayList<Shape> shapes = new ArrayList<>();
		addRectangle(shapes, user);
		addCircle(shapes, user);
		addLine(shapes, user);
		addRoundRectangle(shapes, user);
		return shapes;
	}

	private static void addRectangle(ArrayList<Shape> shapes, User user) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Rectangle> rectangles = session.createCriteria(Rectangle.class).list();
			if (rectangles != null) {
				for (int i = 0; i < rectangles.size(); i++) {
					if (rectangles.get(i).getUser().getUsername().equals(user.getUsername())) {
						int startX = rectangles.get(i).getStartX();
						int startY = rectangles.get(i).getStartY();
						int endX = rectangles.get(i).getEndX();
						int endY = rectangles.get(i).getEndY();
						Color color = ColorString.stringToColor(rectangles.get(i).getStrColor());
						shapes.add(new Rectangle(new Point(startX, startY), new Point(endX, endY), color, user));
					}
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void addCircle(ArrayList<Shape> shapes, User user) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Circle> circles = session.createCriteria(Circle.class).list();
			if (circles != null) {
				for (int i = 0; i < circles.size(); i++) {
					if (circles.get(i).getUser().getUsername().equals(user.getUsername())) {
						int startX = circles.get(i).getStartX();
						int startY = circles.get(i).getStartY();
						int endX = circles.get(i).getEndX();
						int endY = circles.get(i).getEndY();
						Color color = ColorString.stringToColor(circles.get(i).getStrColor());
						shapes.add(new Circle(new Point(startX, startY), new Point(endX, endY), color, user));
					}
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void addLine(ArrayList<Shape> shapes, User user) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Line> lines = session.createCriteria(Line.class).list();
			if (lines != null) {
				for (int i = 0; i < lines.size(); i++) {
					if (lines.get(i).getUser().getUsername().equals(user.getUsername())) {
						int startX = lines.get(i).getStartX();
						int startY = lines.get(i).getStartY();
						int endX = lines.get(i).getEndX();
						int endY = lines.get(i).getEndY();
						Color color = ColorString.stringToColor(lines.get(i).getStrColor());
						shapes.add(new Line(new Point(startX, startY), new Point(endX, endY), color, user));
					}
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void addRoundRectangle(ArrayList<Shape> shapes, User user) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<RoundRectangle> roundRectangles = session.createCriteria(RoundRectangle.class).list();
			if (roundRectangles != null) {
				for (int i = 0; i < roundRectangles.size(); i++) {
					if (roundRectangles.get(i).getUser().getUsername().equals(user.getUsername())) {
						int startX = roundRectangles.get(i).getStartX();
						int startY = roundRectangles.get(i).getStartY();
						int endX = roundRectangles.get(i).getEndX();
						int endY = roundRectangles.get(i).getEndY();
						Color color = ColorString.stringToColor(roundRectangles.get(i).getStrColor());
						shapes.add(new RoundRectangle(new Point(startX, startY), new Point(endX, endY), color, user));
					}
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}