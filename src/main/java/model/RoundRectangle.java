package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class RoundRectangle extends Shape {

	public RoundRectangle() {

	}

	public RoundRectangle(Point start, Point end, Color color, User user) {
		super(start, end, color, user);
		setType();
	}

	public void setType() {
		type = 4;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawRoundRect(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.abs(start.x - end.x),
				Math.abs(start.y - end.y), 20, 20);

	}

	@Override
	public boolean contains(Point point) {
		int x1, y1, x2, y2;
		if (start.y >= end.y) {
			y1 = start.y;
			y2 = end.y;
		} else {
			y1 = end.y;
			y2 = start.y;
		}
		if (start.x >= end.x) {
			x1 = start.x;
			x2 = end.x;
		} else {
			x1 = end.x;
			x2 = start.x;
		}
		return (((point.x == end.x || point.x == start.x || point.x == end.x - 1 || point.x == start.x - 1
				|| point.x == end.x + 1 || point.x == start.x + 1) && point.y > y2 && point.y < y1)
				|| ((point.y == end.y || point.y == start.y || point.y == end.y - 1 || point.y == start.y - 1
						|| point.y == end.y + 1 || point.y == start.y + 1) && point.x > x2 && point.x < x1));
	}
}