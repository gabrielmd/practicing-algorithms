public class RevealTriangle{

	public static String[] calcTriangle(String[] questionMarkTriangle){

		int height = questionMarkTriangle.length;

		// start squared array of ints
		int [][] T = new int[height][height];

		// initialize array
		for(int i = 0; i < height; i++){
			for(int j = 0; j < height - i; j++){
				if(questionMarkTriangle[i].charAt(j) == '?'){
					T[i][j] = -1;
				}else{
					T[i][j] = Integer.parseInt(questionMarkTriangle[i].charAt(j)+"");
				}
			}
		}

		// fill the triangle from bottom to top
		for(int h = height - 1; h >= 0; h--){

			int i = 0;
			while(i < (height - h)){

				if(T[h][i] > -1){
					// this element is filled, go next
					i++;
				}else{
					// case 1: right element is filled
					if(((i + 1) < (height - h)) && (T[h][i+1] > -1)){
						int val = T[h+1][i];
						if(val < T[h][i+1]){
							val += 10;
						}
						T[h][i] = val - T[h][i+1];
						i = 0; // restart row
					}
					// case 2: left element is filled
					else if((i > 0) && (T[h][i-1] > -1)){
						int val = T[h+1][i-1];
						if(val < T[h][i-1]){
							val += 10;
						}
						T[h][i] = val - T[h][i-1];
						i = 0; // restart row

					}
					// case 3: no neighbor is filled
					else{
						i++; // try next
					}

				}
			}
		}

		String [] response = new String[height];
		// generate response (string [])
		for(int h = height - 1; h >= 0; h--){
			response[h] = "";
			for(int i = 0; i < height - h; i++){
				response[h] = response[h] + T[h][i];
			}
		}

		// return
		return response;
	}

	public static void main(String [] args){
		String [] response;
		String [] questionMarkTriangle = {"???2", "??2", "?2", "2"};
		response = calcTriangle(questionMarkTriangle);

		for(String s : response){
			System.out.println(s);
		}
	}

}