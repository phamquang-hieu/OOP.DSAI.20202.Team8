package selectionsort;

public class SelectionSort {
	private int[] arr;
	private int[][] steps;
	private int[][] minIndex;
	private int size;
	
	public int[] getArr() {
		return arr;
	}

	public int[][] getSteps() {
		return steps;
	}

	public int[][] getMinIndex() {
		return minIndex;
	}

	public int getSize() {
		return size;
	}

	public SelectionSort(int[] arr) {
		this.arr = arr;
		this.size = arr.length;
		this.steps = new int[size][size];
		this.steps[0] = arr.clone();
		this.minIndex = new int[size-1][size];

	}
	
	public void Sort() {
		int currentMinIndex;
		
		for (int i = 0; i < size-1; i ++) {
			currentMinIndex = i;
			minIndex[i][i] = currentMinIndex;

			
			for (int j = i+1; j < size; j++) {
				if (arr[j] < arr[currentMinIndex]) {
					currentMinIndex = j;
				}
				minIndex[i][j] = currentMinIndex;
			}
			
			int tmp = arr[currentMinIndex];
			arr[currentMinIndex] = arr[i];
			arr[i] = tmp;
			steps[i+1]=arr.clone();
		}
	}
	
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
	
	public static void main(String args[])
    {
        int[] arr1 = {3,1,2,5,4};
		SelectionSort ob = new SelectionSort(arr1);
        ob.Sort();
        ob.printArray(arr1);
        for (int i =0; i < arr1.length-1; i ++) { 
        	System.out.println("Step " + i);
        	ob.printArray(ob.getSteps()[i]);
        	ob.printArray(ob.getMinIndex()[i]);
        }
        ob.printArray(ob.getSteps()[arr1.length-1]);


    }
}
