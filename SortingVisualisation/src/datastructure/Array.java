package datastructure;

import java.util.Random;

public class Array {
	public int[] data;
	private int length;

	public static void main(String args[]) {
		Array arr = null;
		try {
			arr = new Array("1,2,3,4,5a,6,7,8");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("After catch");
		}
		for (int x : arr.data) {
			System.out.println(x);
		}
	}

	public Array(String s) throws Exception {
		if (s.isEmpty()) {
			throw new NullPointerException("Please input 5->8 element or switch to randomize mode");
		}
		String[] arrString = s.split(",");
		data = new int[arrString.length];
		for (length = 0; length < arrString.length; ++length) {
			try {
				data[length] = Integer.parseInt(arrString[length].trim());
			} catch (NumberFormatException e) {
				data[length] = 0;
				continue;
			}
		}
		if (this.length > 8 || this.length < 5) {
			System.err.println("Your array length: " + this.length);
			throw new Exception("Please input 5->8 element only");
		}
	}

	public Array(int n) {
		Random rand = new Random();
		length = n;
		data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = rand.nextInt(100);
		}
	}

	public Array clone() {
		return this.clone();
	}

	public int getLength() {
		return this.length;
	}
}