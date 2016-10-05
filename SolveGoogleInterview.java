import java.util.*;
import java.lang.*;

public class SolveGoogleInterview{


// return the last index of the number in "search"
public static int binarySearch(int search, int [] ages, int start, int end){

	// base case -- ages[start:end] has only this age
	if(ages[start] == search && ages[start] == ages[end]){
		return end;
	}

	// calculate the middle
	int middle;
	if(end - start % 2 == 1){
		middle = start + ((end - start + 1) / 2);
	}else{
		middle = start + ((end - start) / 2);
	}

	// case 1: middle is the last one
	if(ages[middle] > search && ages[middle - 1] == search){
		return middle - 1;
	}

	// case 2: go to the left
	if(ages[middle] > search){
		return binarySearch(search, ages, start, middle - 1);
	}

	// case 3: go to the right
	return binarySearch(search, ages, middle+1, end);
}

public static int [] checkLastIndices(int [] ages){

	int max = ages[ages.length-1];
	int [] lastIndices = new int[max+1];

	// for each age, check the last index
	lastIndices[0] = binarySearch(0, ages, 0, ages.length-1);

	for(int i = 1; i<=max; i++){
		lastIndices[i] = binarySearch(i, ages, lastIndices[i-1]+1, ages.length - 1);
	}

	return lastIndices;
}

// input: all ages in the world, e.g., [0,0,0,...,0,0,1,1....,1,1,2,2....,2,2....150]
// output: count of each age, e.g., [0] = 130, [1]=200, [2] = 300, [150] = 1
public static int [] histogram(int [] ages){

	int max = ages[ages.length-1];
	int [] response = new int[max+1];

	// check last indices
	int [] lastIndices = checkLastIndices(ages);

	// count ages for histogram
	response[0] = lastIndices[0] + 1;
	for(int i = 1; i<= max; i++){
		response[i] = lastIndices[i] - lastIndices[i-1];
	}

	return response;
}

public static int [] histogramLinear(int[]ages){

	int max = ages[ages.length-1];
	int [] response = new int[max+1];

	for(int i = 0; i < response.length; i++){
		response[i] = 0;
	}

	for(int i = 0; i < ages.length; i++){
		response[ages[i]] ++;
	}

	return response;

}

public static void main(String [] args){
	int size = 500000000;
	int [] ages = new int [size];
	int last = 0;
	int last_i = 0;

	Random rnd = new Random();
 	rnd.setSeed(Integer.parseInt(args[0]));


	for(int i = 0; i<size; i++){
		if(rnd.nextFloat() > 0.999999){
			//System.out.println("["+last+"] => "+(i - last_i));
			last++;
			last_i = i;
		}
		ages[i] = last;
	}
	//System.out.println("["+last+"] => "+(size - last_i));

	// time to run optimized method
	long startTime = System.nanoTime();
	int [] h = histogram(ages);
	long stopTime = System.nanoTime();
	System.out.println("optimized method runtime: \t"+ (stopTime - startTime));


	// time to run linear method
	startTime = System.nanoTime();
	int [] h2 = histogramLinear(ages);
	stopTime = System.nanoTime();
	System.out.println("naive method runtime: \t\t"+ (stopTime - startTime));

	//System.out.println(Arrays.toString(h));
	//System.out.println(Arrays.toString(h2));
}

}