import java.util.*;

public class PolygonSet{

public static long count(int[] S){

	int nmax = 100;
	int smax = 50;

	Arrays.sort(S);

	int [][] A = new int[S.length][nmax * S.length];

	for(int i = 0; i < S.length; i++){
		for(int j = 0; j < nmax * S.length; j++){
			A[i][j] = 0;
		}
	}

	long counter = 0;

	for(int i = 0; i < S.length; i++){

		for(int j = S[i] + 1; i > 0 && j < nmax * S.length; j++){
			// sum possible combinations in which this is the largest side
			counter += A[i - 1][j];
		}

		A[i][S[i]]++;

		for(int j = 0; i > 0 && j < nmax * S.length; j++){

			// check if there is any combination using the past values
			if(A[i-1][j] != 0){
				A[i][j] += A[i-1][j];
				A[i][j + S[i]] += A[i-1][j];
			}
		}

	}

	return counter;

}

public static void main(String[] args){

	int [] S = {1,2,3,4,5,6,7,8,9,100};
	System.out.println("total:" + count(S));

}

}