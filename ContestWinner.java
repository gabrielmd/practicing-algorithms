public class ContestWinner{

	public static int getWinner(int[] events){

		int n = 1000000;
		int [][] participants = new int[n][2];

		// initialization
		for(int i = 0; i < n; i++){
			participants[i][0] = 0;
			participants[i][1] = 0;
		}

		// check events, fill array
		for(int i = 0; i < events.length; i++){
			participants[events[i]][0]++;
			participants[events[i]][1] = i;
		}

		int max = 0;
		int winner = -1;
		int lastEvent = 0;

		// check winner
		for(int i = 0; i < n; i++){
			if(participants[i][0] > max){
				max = participants[i][0];
				lastEvent = participants[i][1];
				winner = i;
			}else if(participants[i][0] == max && participants[i][1] < lastEvent){
				lastEvent = participants[i][1];
				winner = i;
			}
		}

		return winner;
	}


	public static void main(String [] args){
		int [] events = {123,123,456,456,456,123};
		System.out.println(getWinner(events));
	}
}