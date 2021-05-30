package algorithms;

public class MergeSort {
	private int[] arr;
	private double[][] steps = new double[6][100];
	private int step = 0;

	public MergeSort(int[] arr) {
		this.arr = arr;
	}

	public double[][] getSteps() {
		return this.steps;
	}

	public int getNumStep() {
		return this.step;
	}

	private void merge(int start, int end, int mid, double midX, double startY, double startLeftX, double startRightX) {
		int[] aux = new int[end - start + 1];

		int left_index = start;
		int right_index = mid + 1;
		assignSteps(start, end, midX, startY, 3, 3);

		for (int i = 0; i < (end - start + 1); ++i) {
			if (left_index > mid) {
				// all element from left array has been taken.
				aux[i] = this.arr[right_index++];
				assignSteps(-1, -1, startRightX, startY + 80, 3, 4);
				assignSteps(-1, aux[i], midX + (i - (end - start + 1) / 2) * 50, startY, 2, 4);
				if (right_index < end + 1) {
					startRightX += 50;
					assignSteps(-1, this.arr[right_index], startRightX, startY + 80, 1, 4);
				}
			} else if (right_index > end) {
				// all element from right array has been taken.
				aux[i] = this.arr[left_index++];
				assignSteps(-1, -1, startLeftX, startY + 80, 3, 5);
				assignSteps(-1, aux[i], midX + (i - (end - start + 1) / 2) * 50, startY, 2, 5);
				if (left_index < mid + 1) {
					startLeftX += 50;
					assignSteps(-1, this.arr[left_index], startLeftX, startY + 80, 1, 5);
				}
			} else if (this.arr[left_index] < this.arr[right_index]) {
				aux[i] = this.arr[left_index++];
				assignSteps(-1, -1, startLeftX, startY + 80, 3, 3);
				assignSteps(-1, aux[i], midX + (i - (end - start + 1) / 2) * 50, startY, 2, 3);
				if (left_index < mid + 1) {
					startLeftX += 50;
					assignSteps(-1, this.arr[left_index], startLeftX, startY + 80, 1, 3);
				}
			} else if (this.arr[left_index] >= this.arr[right_index]) {
				aux[i] = this.arr[right_index++];
				assignSteps(-1, -1, startRightX, startY + 80, 3, 3);
				assignSteps(-1, aux[i], midX + (i - (end - start + 1) / 2) * 50, startY, 2, 3);
				if (right_index < end + 1) {
					startRightX += 50;
					assignSteps(-1, this.arr[right_index], startRightX, startY + 80, 1, 3);
				}
			}
		}
		int j = 0;

		for (int i = start; i <= end; ++i)
			this.arr[i] = aux[j++];

	}

	public void merge_sort(int start, int end, double startX, double endX, double startY) {
		if (start < end) {
			int mid = (int) (start + end) / 2;
			double midX = (startX + endX) / 2;

			assignSteps(start, end, midX, startY, 4, 0);
			assignSteps(start, mid, (startX + midX) / 2, startY + 80, 0, 0);
			assignSteps(mid + 1, end, (midX + endX) / 2, startY + 80, 0, 0);
			merge_sort(start, mid, startX, midX, startY + 80);

			merge_sort(mid + 1, end, midX, endX, startY + 80);

			double startLeftX = (startX + midX) / 2 - (mid - start + 1) / 2 * 50;
			double startRightX = (midX + endX) / 2 - (end - mid) / 2 * 50;
			assignSteps(-1, this.arr[start], startLeftX, startY + 80, 1, 1); // select and ready to merge
			assignSteps(-1, this.arr[mid + 1], startRightX, startY + 80, 1, 2); // select and ready to merge

			merge(start, end, mid, midX, startY, startLeftX, startRightX);
		}
	}

	private void assignSteps(double args1, double args2, double args3, double args4, int color, int instruction) {
		// color:
		// 0: yellowgreen
		// 1: red: selected and ready to merge
		// 2: blueviolet: merged
		// 3: white: vanished
		// 4: yellow: selected and ready to split
		this.steps[0][this.step] = args1;
		this.steps[1][this.step] = args2;
		this.steps[2][this.step] = args3;
		this.steps[3][this.step] = args4;
		this.steps[4][this.step] = color;
		this.steps[5][this.step] = instruction;
		this.step++;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 4, 1, 2, 7, 1, 2 };
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
