package board;

import java.awt.Color;

import processing.core.PApplet;

public class Line {

	private int x, y, x2, y2, color;
	
	public Line(int x, int y, int x2, int y2)
	{
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;

		color = 0;
	}
	
	public void draw(PApplet drawer)
	{
		drawer.stroke(color);
		drawer.strokeWeight(16);
		drawer.line(x, y, x2, y2);
		// drawer.text(getX(), x, y2 - 20); // displays x-pos of line for debugging/testing purposes
	}
	
	// GETTERS & SETTERS

	public void setX(int x) 
	{
		this.x = x;
		x2 = x; // setting both x's to same value since we want to move the line horizontally along the x-axis while keeping it as a vertical line
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y2;
	}
	
	public void setY(int y2)
	{
		this.y2 = y2;
	}

	public void setColor(Color color)
	{
		this.color = color.getRGB();
	}

	public static void swapLines(Line l1, Line l2)
	{
		int temp = l1.getY();
		l1.setY(l2.getY());
		l2.setY(temp);
	}
}
