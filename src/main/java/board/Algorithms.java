package board;

import java.awt.Color;
import java.util.ArrayList;

public class Algorithms {

	private String[] algorithmsList;
	
	public Algorithms()
	{
		algorithmsList = new String[] {"Bubble Sort", "Insertion Sort"};
	}

	public ArrayList<Integer> bubbleSort(Line[] board)
	{
		ArrayList<Integer> swaps = new ArrayList<Integer>();

		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board.length - i - 1; j++)
			{
				// smaller Y-val means taller line
				// so if left line is smaller Y-val then its taller so needs to be swapped
				// System.out.println(board[j].getY());
				// System.out.println(board[j+1].getY());
				// System.out.println(board[j].getY() < board[j+1].getY());
				if (board[j].getY() < board[j+1].getY())
				{
					Line.swapLines(board[j], board[j+1]);
					swaps.add(j);
					swaps.add(j+1);
				}
			}
		}

		return swaps;
	}
	
	public ArrayList<Integer> insertionSort(Line[] board)
	{
		ArrayList<Integer> swaps = new ArrayList<Integer>();

		for (int i = 1; i < board.length; i++)
		{
			int j = i;
			while (j > 0 && board[j-1].getY() < board[j].getY())
			{
				Line.swapLines(board[j-1], board[j]);
				swaps.add(j-1);
				swaps.add(j);
				j--;
			}
		}

		return swaps;
	}

	// public ArrayList<Integer> mergeSort(Line[] board)
	// {
	// 	if (board.length == 1)
	// 	{
	// 		return board;
	// 	}
	// }

	// public ArrayList<Integer> quickSort(Line[] board)
	// {

	// }

	// GETTERS & SETTERS

	public String[] getAlgorithmsList() {
		return algorithmsList;
	}
}
