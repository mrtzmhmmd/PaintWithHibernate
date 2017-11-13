package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends Shape {

	public Line() {

	}

	public Line(Point start, Point end, Color color, User user) {
		super(start, end, color, user);
	}
	
	public Line(Point start, Point end, Color color, User user, String type) {
		super(start, end, color, user, type);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawLine(start.x, start.y, end.x, end.y);
	}

	@Override
	public boolean contains(Point point) {
		return new Line2D.Double(start, end).ptLineDist(point) <= 0.7;
	}
}