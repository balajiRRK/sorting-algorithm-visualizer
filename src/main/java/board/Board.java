package board;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class Board {

	private Line[] lines; // list to be visually displayed with incremental sorting steps
	private Line[] linesCopy; // list to be sorted by algorithm
	private ArrayList<Integer> swaps;
	private Algorithms algo;
	private int algorithmIndex;
	private String currentAlgorithm;
	private int linesCount;

	public Board(int width, int height)
	{
		currentAlgorithm = "Bubble Sort";
		algo = new Algorithms();
		linesCount = 50; 
		lines = new Line[linesCount];
		linesCopy = new Line[linesCount];

		// Top left of the PApplet window is (0, 0)
		int x = 50; // starting x position of left-most line
		int y = (int)(height * 8.9/9);
		int y2 = y - 10; // y - 10 since it creates a small length distance between y1 and y2 so creates a small line

		for (int i = 0; i < lines.length; i++)
		{
			lines[i] = new Line(x, y, x, y2);
			linesCopy[i] = new Line(x, y, x, y2);
			x += (width-40)/lines.length; // incrementally move each next line to the right after the first one, increase their x-position to the right evenly so that the width of the window is distributed by the amount of lines
			y2 -= ((double)height) / (lines.length * 1.8); // incrementally make each next line taller after the first one (decreasing value means height goes up) 
		}
	}

	public void randomize()
	{
		int random;
		Random r = new Random();

		for (int i = lines.length - 1; i > 0; i--)
		{
			random = r.nextInt(i + 1); // random index from 0 to i

			// switch the y-values of lines to simulate swapping the lines position
			int tempY = lines[random].getY();
			lines[random].setY(lines[i].getY());
			lines[i].setY(tempY);

			// copy linesCopy from lines instead of copying swapping coded since the two arrays may start out differently so swapping both would result in different randomizes
			for (int j = 0; j < lines.length; j++)
			{
				linesCopy[j].setY(lines[j].getY());
			}
		} 

		if (swaps != null) {
			swaps.clear(); // reset swaps since there is now a new unsorted lines array			
		}
	}

	public void sort() 
	{
		if (swaps != null) {
			swaps.clear(); // ensure swaps is empty to prevent any weird issues
		}

		String[] algosList = algo.getAlgorithmsList();

		if (algosList[algorithmIndex] == "Bubble Sort")
		{
			swaps = algo.bubbleSort(linesCopy);
		} else if (algosList[algorithmIndex] == "Insertion Sort")
		{
			swaps = algo.insertionSort(linesCopy);
		} else if (algosList[algorithmIndex] == "Merge Sort")
		{
			swaps = algo.mergeSort(linesCopy, 0, linesCopy.length - 1);
		} else if (algosList[algorithmIndex] == "Quick Sort")
		{
			swaps = algo.quickSort(linesCopy, 0, linesCopy.length - 1);
		} else if (algosList[algorithmIndex] == "Selection Sort")
		{
			swaps = algo.selectionSort(linesCopy);
		}
	}

	public boolean swap()
	{
		if (swaps != null && swaps.size() > 1)
		{
			Line.swapLines(lines[swaps.remove(0)], lines[swaps.remove(0)]);
			return true; // possibly not finished sorting
		} else
		{
			return false; // sorted
		}
	}
	
	public void changeAlgorithm(String s)
	{
		String[] algosList = algo.getAlgorithmsList();

		if (s == "LEFT")
		{
			algorithmIndex = (algorithmIndex - 1 + algosList.length) % algosList.length; // `% algorithms.length` to create the wrap around functionality
			currentAlgorithm = algosList[algorithmIndex]; // sorting algorithm name to display
		} else if (s == "RIGHT")
		{
			algorithmIndex = (algorithmIndex + 1) % algosList.length; // `% algorithms.length` to create the wrap around functionality
			currentAlgorithm = algosList[algorithmIndex]; // sorting algorithm name to display
		}
	}
	
	public void draw(PApplet drawer)
	{
		drawer.fill(0);
		drawer.textSize(26);
		drawer.text(currentAlgorithm, drawer.width/30, drawer.height/10);
		drawer.textSize(20);
		String randomizeInstructions = "Press 'R' to randomize";
		String sortingInstructions = "Press spacebar to sort";
		String algorithmCycleInstructions = "Use the left and right arrow keys to cycle between different algorithms";
		String fpsInstructions = "Use the up and down arrow keys to increase or decrease the fps by 5 frames from a range of 5-90";
		drawer.text(randomizeInstructions + "\n" + sortingInstructions + "\n" + algorithmCycleInstructions + "\n" + fpsInstructions, drawer.width/5, drawer.height/10);

		for (int i = 0; i < lines.length; i++)
		{
			if (swaps != null && swaps.size() > 1 && (i == swaps.get(0) || i == swaps.get(1)))
			{
				lines[i].setColor(Color.RED);
			} else
			{
				lines[i].setColor(Color.BLACK);
			}
			lines[i].draw(drawer);
		}
	}

	// GETTERS & SETTERS

	public int getAlgorithmIndex()
	{
		return algorithmIndex;
	}

	public Line[] getBoard()
	{
		return lines;
	}

	public void clearSwaps()
	{
		if (swaps != null) {
			swaps.clear();
		}
	}
}
