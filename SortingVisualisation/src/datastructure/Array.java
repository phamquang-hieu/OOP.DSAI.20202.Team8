package datastructure;

import java.util.Random;

public class Array {
	public int[] data;
	private int length;

	public Array(String s) throws Exception {
		s = s.trim();
		if (s.isEmpty()) {
			throw new NullPointerException("Please input 5->8 element or switch to randomize mode");
		}
		String[] arrString = s.split(",");
		data = new int[arrString.length];
		for (length = 0; length < arrString.length; ++length) {
			try {
				data[length] = Integer.parseInt(arrString[length].trim());
				if(data[length] < 0) throw new NumberFormatException("Array element have to be positive!");
			} catch (NumberFormatException e) {
				throw e;
			}
		}
		if (this.length > 8 || this.length < 5) {
			throw new Exception("Your array length: " + this.length+". Please input 5->8 element only");
		}
	}

	public Array(int n) {
		Random rand = new Random();
		length = n;
		data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = rand.nextInt(80);
		}
	}

	public Array clone() {
		return this.clone();
	}

	public int getLength() {
		return this.length;
	}
}