import java.util.*;

public class TheSwapsDivTwo{

	public static int find(int[] s){

		Arrays.sort(s);

		int flag = 0;

		int n = s.length;
		int [] x = new int[n];
		x[0] = 1;

		for(int i = 1; i < n; i++){
			if(s[i] == s[i-1]){
				x[i] = x[i-1] + 1;
				flag = 1;
			}else{
				x[i] = 1;
			}
		}

		int counter = flag;
		for(int i = (n-1); i >= 0; i--){
			// sum the number of different numbers
			counter += (i - x[i] + 1);
		}

		return counter;
	}

	public static void main(String [] args){
		int [] sequence = 	{1, 2, 1, 1, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 2};
		System.out.println(find(sequence));


	}
}