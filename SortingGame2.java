import java.util.*;

public class SortingGame2{

	public static void reverse(int [] board, int i, int k){
		int [] tmp = new int[k];

		int t = 0;
		for(int j = i+k-1; j >= i; j--){
			tmp[t] = board[j];
			t++;
		}

		t = 0;
		for(int j = i; j < i+k; j++){
			board[j] = tmp[t];
			t++;
		}
	}

	public static boolean isSorted(int [] board){
		for(int i = 0; i < board.length; i++){
			if(board[i] != i+1){
				return false;
			}
		}

		return true;
	}

	public static int fewestMoves(int[] board, int k){
		Set<String> states = new HashSet<String>();
		List<int []> queue = new LinkedList<int []>();
		List<Integer> deep = new LinkedList<Integer>();


		queue.add(board);
		deep.add(0);

		while(queue.size() > 0){

			int [] current = queue.remove(0);
			int height = deep.remove(0);

			// try only new states
			if(!states.contains(Arrays.toString(current))){
				states.add(Arrays.toString(current));

				// base case
				if(isSorted(current)){
					return height;
				}

				for(int i = 0; i <= board.length - k; i++){
					int [] copy = Arrays.copyOf(current, board.length);
					reverse(copy, i, k);

					queue.add(copy);
					deep.add(height + 1);
				}

			}

		}

		return -1;
	}

	public static void main(String [] args){
		int [] x = {7,2,1,6,8,4,3,5};
		System.out.println(fewestMoves(x, 4));
	}


}