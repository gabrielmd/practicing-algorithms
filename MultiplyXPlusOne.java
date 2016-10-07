/*
Problem Statement
    	There is a card and at the beginning there is a number s on it, in each step
    	you can do one of this operation:
Suppose the number on this card is x, change it into 2x+1.
Suppose the number on this card is x, change it into 3x+1.
Please compute and return the minimal number of operations to change the number of your
card into t. If that is impossible, return -1.

-	s will be between 0 and 1,000,000, inclusive.
-	t will be between 0 and 1,000,000, inclusive.

Source: https://community.topcoder.com/stat?c=problem_statement&pm=14346
*/

public class MultiplyXPlusOne{

	public static int minimalSteps(int s, int t){

		if(s > t){
			return -1;
		}

		// create array with t+1 positions
		int [] response = new int[t+1];

		// fill with -1
		for(int i = 0; i <= t; i++){
			response[i] = -1;
		}
		response[s] = 0;

		// fill the array with the minimum number of operations if both are
		// available
		for(int i = 0; i <= t; i++){

			if(response[i] > -1){
				// 2x + 1
				if((2 * i) + 1 <= t){
					if(response[(2 * i) + 1] > -1){ // already "taken"
						response[(2 * i) + 1] = Math.min(response[(2 * i) + 1], response[i] + 1);
					}else{
						response[(2 * i) + 1] = response[i] + 1;
					}
				}

				// 3x + 1
				if((3 * i) + 1 <= t){
					if(response[(3 * i) + 1] > -1){ // already "taken"
						response[(3 * i) + 1] = Math.min(response[(3 * i) + 1], response[i] + 1);
					}else{
						response[(3 * i) + 1] = response[i] + 1;
					}
				}
			}

		}

		// return last position ==> answer
		return response[t];
	}

	public static void main(String [] args){
		System.out.println(minimalSteps(1, 22) + " - correct answer:"+ 3);
		System.out.println(minimalSteps(1, 31) + " - correct answer:"+ 3);
		System.out.println(minimalSteps(100, 99) + " - correct answer:"+ (-1));
		System.out.println(minimalSteps(55555, 1000000) + " - correct answer:"+ 3);
		System.out.println(minimalSteps(1, 1) + " - correct answer:"+ 0);
		System.out.println(minimalSteps(2, 342783) + " - correct answer:"+ 14);

	}

}