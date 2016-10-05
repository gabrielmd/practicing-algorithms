public class TheCowDivTwo{

	 public static int find(int N, int K){

		 int maxSum = ((N + (N-K)) * K) / 2;


		 // last item considered x sum x number of used items
	 	long [][][] A = new long[2][maxSum][K];

	 	for(int i = 0; i < 2; i++){
	 		for(int j = 0; j < maxSum; j++){
	 			for(int k = 0; k < K; k++){
	 				A[i][j][k] = 0;
	 			}
	 		}
	 	}
	 	A[0][0][0] = 1;

	 	for(int i = 1; i < N; i++){
	 		for(int j = 0; j < maxSum; j++){
	 			for(int k = 0; k < K; k++){
		 			A[i%2][j][k] = A[(i - 1)%2][j][k];
				}
			}
 			A[i%2][i][0] = 1;

	 		for(int j = i; j < maxSum; j++){
	 			for(int k = 1; k < K; k++){
		 			if(k > 0 && j - i >= 0){
 						A[i%2][j][k] = (A[i%2][j][k] + A[(i - 1)%2][j - i][k - 1]) % 1000000007;
 					}
	 			}
	 		}
	 	}

	 	long sum = 0;
	 	for(int j = 0; j < maxSum; j += N){
		 	//System.out.println("j="+j+ " " +A[(N-1) % 2][j][K-1]);
	 		sum = (sum + A[(N-1) % 2][j][K-1])% 1000000007;
	 	}

	 	return (int)(sum);
	 }

	public static void main(String [] args){
		System.out.println(find(1000, 47));
	}
}