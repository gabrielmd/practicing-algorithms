import java.util.*;
import java.lang.*;

public class SortingGame{

	static int minMovesSoFar = 50;

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

	public static int recFewest(Map<String, Integer[]> states, int [] board, int k, int moves){
		//System.out.println(Arrays.toString(board));
		// many moves
		if(minMovesSoFar != -1 && minMovesSoFar < moves){
			return -1;
		}

		// base case: is sorted
		boolean isSorted = true;
		for(int i = 0; i < board.length; i++){
			if(board[i] != i+1){
				isSorted = false;
				break;
			}
		}
		if(isSorted){
			return moves;
		}

		// case 1: already visited this state
		if(states.get(Arrays.toString(board)) != null){

			Integer [] tmpArr = states.get(Arrays.toString(board));
			if(tmpArr.length == 2){
				// we have been to this state and found a solution with tmpArr[1] moves
				if(moves < tmpArr[0]){
					tmpArr[1] = tmpArr[1] - (tmpArr[0] - moves);
					tmpArr[0] = moves;
					states.put(Arrays.toString(board), tmpArr);
				}
				return tmpArr[1];
			}else{
				return -1;
			}
		}

		Integer [] tmpArr = new Integer[1];
		tmpArr[0] = moves;
		states.put(Arrays.toString(board), tmpArr);

		// case 2: try changes
		int minMoves = -1;
		for(int i = 0; i <= board.length - k; i++){
			reverse(board, i, k);
			int m = recFewest(states, board, k, moves + 1);
			reverse(board, i, k);

			if(m != -1){
				if(minMoves == -1){
					minMoves = m;
				}else{
					minMoves = Math.min(m, minMoves);
				}

				if(minMovesSoFar == -1){
					minMovesSoFar = m;
				}else{
					minMovesSoFar = Math.min(m, minMovesSoFar);
				}
			}
		}

		return minMoves;
	}

	public static int fewestMoves(int[] board, int k){
		Map<String, Integer[]> map = new HashMap<String, Integer[]>();
		return recFewest(map, board, k, 0);
	}

	public static void main(String [] args){

		int [] x = {7,2,1,6,8,4,3,5};
		System.out.println(fewestMoves(x, 4));
	}

}