package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.EntityManager;
import model.Circle;
import model.Line;
import model.Rectangle;
import model.RoundRectangle;
import model.Shape;
import model.User;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Shape> shapes = new ArrayList<Shape>();
	private static ArrayList<Shape> shapes_s = new ArrayList<Shape>();
	private Point start, end;
	private Shape shape = null;
	private double scale = 0.2;
	protected static double zoomFactor = 1;

	Panel(final User user) throws SQLException {
		setBounds(0, 0, 500, 500);
		setBackground(Color.WHITE);
		// entityManager = new ShapeEntityManager(user);
		shapes = EntityManager.loadShape(user);

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				repaint();
				if (Paint.color != null && Paint.flag != 0) {
					end = new Point(e.getPoint());
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Paint.flag == 5) {
					Color color = Paint.color;
					Point p = new Point(e.getPoint());
					for (Shape s : shapes) {
						if (s.contains(p)) {
							repaint();
							s.setColor(color);
							/*
							 * try { // entityManager.updateShape(s, color); } catch (SQLException e1) {
							 * e1.printStackTrace(); }
							 */
						}
					}
				}

				else if (Paint.color != null && Paint.flag != 0) {
					start = new Point(e.getPoint());
					end = start;
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (Paint.color != null && Paint.flag != 0 && Paint.flag != 5) {
					if (Paint.flag == 1) {
						shape = new Rectangle(start, end, Paint.color, user);
						shapes_s.add(shape);
						shapes.add(shape);
					} else if (Paint.flag == 2) {
						shape = new Circle(start, end, Paint.color, user);
						shapes_s.add(shape);
						shapes.add(shape);
					} else if (Paint.flag == 3) {
						shape = new Line(start, end, Paint.color, user);
						shapes_s.add(shape);
						shapes.add(shape);
					} else if (Paint.flag == 4) {
						shape = new RoundRectangle(start, end, Paint.color, user);
						shapes_s.add(shape);
						shapes.add(shape);
					}
					start = null;
					end = null;
					repaint();
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.scale(zoomFactor, zoomFactor);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		for (Shape shape : shapes)
			shape.draw(graphics);

		if (start != null && end != null) {
			g2d.setPaint(Paint.color);
			if (Paint.flag == 1) {
				g2d.draw(drawRectangle());
			} else if (Paint.flag == 2) {
				g2d.draw(drawCircle());
			} else if (Paint.flag == 3) {
				g2d.draw(drawLine());
			} else if (Paint.flag == 4) {
				g2d.draw(drawRoundRectangle());
			}
		}
	}

	private Rectangle2D.Double drawRectangle() {
		int x = Math.min(start.x, end.x);
		int y = Math.min(start.y, end.y);
		int width = Math.abs(start.x - end.x);
		int height = Math.abs(start.y - end.y);
		return (new Rectangle2D.Double(x, y, width, height));
	}

	private Ellipse2D.Double drawCircle() {
		int cX = (int) Math.pow((end.x - start.x), 2);
		int cY = (int) Math.pow((end.y - start.y), 2);
		int radius = (int) Math.sqrt(cX + cY);
		cX = start.x - radius;
		cY = start.y - radius;
		return (new Ellipse2D.Double(cX, cY, 2 * radius, 2 * radius));
	}

	private Line2D.Double drawLine() {
		return (new Line2D.Double(start.x, start.y, end.x, end.y));
	}

	private RoundRectangle2D.Double drawRoundRectangle() {
		int startX = Math.min(start.x, end.x);
		int startY = Math.min(start.y, end.y);
		int width = Math.abs(start.x - end.x);
		int height = Math.abs(start.y - end.y);
		return (new RoundRectangle2D.Double(startX, startY, width, height, 5, 5));
	}

	protected static void saveShapeInDatabase() throws SQLException {
		for (Shape shape : shapes_s) {
			EntityManager.saveShape(shape);
		}
		shapes.clear();
		shapes_s.clear();
	}

	public Dimension getPreferredSize() {
		return new Dimension((int) (500 * zoomFactor), (int) (500 * zoomFactor));
	}

	public void zoomIn() {
		Panel.zoomFactor = Math.min(2, scale + zoomFactor);
		revalidate();
		repaint();
	}

	public void zoomOut() {
		Panel.zoomFactor = Math.max(1, zoomFactor - scale);
		revalidate();
		repaint();
	}
}