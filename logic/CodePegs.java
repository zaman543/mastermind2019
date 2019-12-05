package logic;

import java.awt.Color;

public class CodePegs {
	private String color;
	private static String[] colorArr = {"white", "black", "red", "blue", "green", "orange", "yellow", "cyan", "gray", "pink"};

	
	public CodePegs(int i) {
		color = colorArr[i];
	}
	
	public String getColor() {
		return color;
	}
	
	public Color getColorObj() {
		Color colorObj = null;
		
		switch(color) {
		case "white": colorObj = Color.white; break;
		case "black": colorObj = Color.black; break;
		case "red": colorObj = Color.red; break;
		case "blue": colorObj = Color.blue; break;
		case "green": colorObj = Color.green; break;
		case "orange": colorObj = Color.orange; break;
		case "yellow": colorObj = Color.yellow; break;
		case "cyan": colorObj = Color.cyan; break;
		case "gray": colorObj = Color.gray; break;
		case "pink": colorObj = Color.pink; break;
		default: colorObj = Color.white; break;
		}
		
		return colorObj;
	}

	public void setColor(int i) {
		color = colorArr[i];
	}
}
