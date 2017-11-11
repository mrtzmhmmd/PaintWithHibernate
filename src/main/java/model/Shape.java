package model;

import java.awt.Color;
import java.awt.Point;

import controller.ColorString;

public class Shape {

	protected long id;
	protected Point start;
	protected Point end;
	protected Color color;
	protected User user;
	protected int type;
	protected int startX;
	protected int startY;
	protected int endX;
	protected int endY;
	protected String strColor;

	public Shape() {

	}

	public Shape(long id, Point start, Point end, Color color, User user) {
		setId(id);
		setStart(start);
		setEnd(end);
		setColor(color);
		setUser(user);
		setStartX(start.x);
		setStartY(start.y);
		setEndX(end.x);
		setEndY(end.y);
		setStrColor(ColorString.colorToString(color));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public String getStrColor() {
		return strColor;
	}

	public void setStrColor(String strColor) {
		this.strColor = strColor;
	}
}