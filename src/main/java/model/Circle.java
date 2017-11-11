package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shape {

	private int radius;

	public Circle() {

	}

	public Circle(Color color, Point start, Point end, User user) {
		super(start, end, color, user);
		this.type = 2;
		radius = ((int) Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2)));
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawOval(start.x - radius, start.y - radius, radius * 2, radius * 2);
	}

	@Override
	public boolean contains(Point point) {
		int cx = (int) Math.pow((point.x - start.x), 2);
		int cy = (int) Math.pow((point.y - start.y), 2);
		int d = (int) Math.sqrt(cx + cy);
		return (d == radius || d == radius - 2 || d == radius - 1);
	}
}