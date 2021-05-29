package algorithms;

public class ShellSort {
	private int n, numSteps;
	private int[] arr;
	private int[][] steps, arrState;
	private int[] flags;

	public int[] getArrState(int i) {
		return arrState[i];
	}

	public int getNumSteps() {
		return numSteps;
	}

	public int[] getSteps(int i) {
		return steps[i];
	}

	public int getFlags(int i) {
		return flags[i];
	}

	public int getN() {
		return n;
	}

	public ShellSort(int[] inputArr) {
		arr = inputArr.clone();
		n = arr.length;
		steps = new int[4 * n * n][2];
		arrState = new int[4 * n * n][n];
		flags = new int[4 * n * n];
		numSteps = 0;
	}

	private void swap(int posi, int posj) {
		int tmp = arr[posi];
		arr[posi] = arr[posj];
		arr[posj] = tmp;
	}

	public void Sort() {
		for (int gap = n / 2; gap > 0; gap /= 2)
			for (int pos = gap; pos < n; ++pos) {
				int j = pos;
				while (j >= gap) {
					int i = j - gap;
					steps[numSteps][0] = i;
					steps[numSteps][1] = j;
					arrState[numSteps] = arr.clone();
					flags[numSteps] = 1;
					++numSteps;

					if (arr[i] <= arr[j]) {
						steps[numSteps][0] = i;
						steps[numSteps][1] = j;
						arrState[numSteps] = arr.clone();
						flags[numSteps] = 2;
						++numSteps;
						break;
					}

					swap(i, j);
					steps[numSteps][0] = i;
					steps[numSteps][1] = j;
					arrState[numSteps] = arr.clone();
					flags[numSteps] = 3;
					++numSteps;

					steps[numSteps][0] = i;
					steps[numSteps][1] = j;
					arrState[numSteps] = arr.clone();
					flags[numSteps] = 2;
					++numSteps;

					j -= gap;
				}
			}
		arrState[numSteps] = arr.clone();
	}

	void printArray() {
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		int[] testarr = { 3, 1, 2, 5, 4 };
		ShellSort shellsort = new ShellSort(testarr);
		shellsort.Sort();
		shellsort.printArray();
	}
}
