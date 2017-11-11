package controller;

import java.awt.Color;

public class ColorString {

	public static Color stringToColor(String stringColor) {
		switch (stringColor) {
		case "Black":
			return Color.BLACK;
		case "Blue":
			return Color.BLUE;
		case "Green":
			return Color.GREEN;
		case "Red":
			return Color.RED;
		case "Yellow":
			return Color.YELLOW;
		default:
			return Color.CYAN;
		}
	}

	public static String colorToString(Color color) {
		if (color.equals(Color.BLACK))
			return "Black";
		else if (color.equals(Color.BLUE))
			return "Blue";
		else if (color.equals(Color.GREEN))
			return "Green";
		else if (color.equals(Color.RED))
			return "Red";
		else if (color.equals(Color.YELLOW))
			return "Yellow";
		return "Cyan";
	}
}