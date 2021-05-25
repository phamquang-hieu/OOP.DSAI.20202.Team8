package datastructure;
import java.util.Random;

public class Array {
	public int[] data = new int[8];
	private int length;
	
	public Array(String s) throws Exception{
		// create array from textField input
		if(s.isBlank()) {
			throw new NullPointerException("Please input 5->8 element or switch to randomize mode");
		}
		String[] arrString = s.split(",");
		for(String x: arrString){
			try{
				this.data[this.length] = Integer.parseInt(x.trim());
	      		this.length++;
	      	} catch(NumberFormatException e){
	      		continue;
	      	}
	    }
		if(this.length > 8 || this.length < 5) {
			System.err.println("Your array length: " + this.length);
			throw new Exception("Please input 5->8 element only");
		}
	}
	
	public Array(int length) {
		//this is to randomize an array with given length
		Random rand = new Random();
		this.length = length;
		this.data = new int[length];
    	for(int i = 0; i< length; i++) {
    		this.data[i] = rand.nextInt(100);
    	}
	}
	
	public Array clone() {
		return this.clone();
	}
	
	public int getLength() {
		return this.length;
	}
}