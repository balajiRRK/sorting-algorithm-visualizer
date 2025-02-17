package board;

import java.util.ArrayList;

public class Algorithms {

	private String[] algorithmsList;
	
	public Algorithms()
	{
		algorithmsList = new String[] {"Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Selection Sort"};
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

	public ArrayList<Integer> mergeSort(Line[] board, int left, int right) {
		ArrayList<Integer> swaps = new ArrayList<>();
		
		if (left < right) {
			int mid = (left + right) / 2;
			// Recursively sort left half and record its swaps
			swaps.addAll(mergeSort(board, left, mid));
			// Recursively sort right half and record its swaps
			swaps.addAll(mergeSort(board, mid + 1, right));
			
			// Now merge the two sorted halves in-place using shifting.
			// 'start' points into the left sorted portion,
			// 'start2' points into the right sorted portion.
			int start = left;
			int start2 = mid + 1;
			
			// While both subarrays have elements...
			while (start <= mid && start2 <= right) {
				// If the current element in the left part is already <= the element in the right,
				// it’s in order.
				if (board[start].getY() > board[start2].getY()) {
					start++;
				} else {
					// board[start2] is smaller and needs to be moved into the left portion.
					// Save the index where the right element starts.
					int index = start2;
					
					// Shift all elements from board[start] up to board[start2-1] one position to the right.
					// Each adjacent swap is recorded.
					while (index > start) {
						// Record the swap of indices (index-1, index)
						swaps.add(index - 1);
						swaps.add(index);
						
						Line.swapLines(board[index], board[index-1]);
						
						index--;
					}
					// After shifting, the element originally at board[start2] is now at board[start].
					// Update pointers:
					start++;      // left portion has grown by one element
					mid++;        // mid increases because the left part now covers one more element
					start2++;     // right portion's start moves right by one
				}
			}
		}
		
		return swaps;
	}

	public ArrayList<Integer> quickSort(Line[] board, int low, int high) {
        ArrayList<Integer> swaps = new ArrayList<>();

        if (low < high) {
            int pivotIndex = partition(board, low, high, swaps);

            swaps.addAll(quickSort(board, low, pivotIndex - 1));
            swaps.addAll(quickSort(board, pivotIndex + 1, high));
        }

        return swaps;
    }

    private int partition(Line[] board, int low, int high, ArrayList<Integer> swaps) {
        int pivot = board[high].getY();  // Using last element as pivot
        int leftWall = low;

        for (int i = low; i < high; i++) {
            if (board[i].getY() > pivot) {
                swaps.add(i);
                swaps.add(leftWall);
                Line.swapLines(board[i], board[leftWall]); // Swap to the correct position
                leftWall++;
            }
        }

        // Final swap to put pivot in the correct place
        swaps.add(leftWall);
        swaps.add(high);
        Line.swapLines(board[leftWall], board[high]);

        return leftWall; // Return pivot's final position
    }

	public ArrayList<Integer> selectionSort(Line[] board)
	{
        ArrayList<Integer> swaps = new ArrayList<>();

		for (int j = 0; j < board.length - 1; j++)
		{
			int iMin = j;

			for (int i = j + 1; i < board.length; i++)
			{
				if (board[i].getY() > board[iMin].getY())
				{
					iMin = i;
				}
			}

			if (iMin != j)
			{
				swaps.add(j);
				swaps.add(iMin);
				Line.swapLines(board[j], board[iMin]);
			}
		}

		return swaps;
	}

	// GETTERS & SETTERS

	public String[] getAlgorithmsList() {
		return algorithmsList;
	}
}
