import java.lang.*;

public class FoxAndMp3Easy{

	public static int tryRec(int n, int i, String [] vals, int rIndex){
		if(i > n) return rIndex;

		for(int j = 0; j < 10 && rIndex < n && rIndex < 50; j++){
			if((i * 10) + j > 0){
				if ((i * 10) + j <= n){
					vals[rIndex] = (i * 10) + j + ".mp3";
					rIndex++;
				}
				rIndex = tryRec(n, (i * 10) + j, vals, rIndex);
			}
		}
		return rIndex;
	}

	public static String[] playList(int n){

		String [] response = new String[Math.min(50, n)];

		tryRec(n, 0, response, 0);

		return response;
	}

	public static void main(String[] args){
		String [] vals = playList(30);
		for(String v : vals){
			System.out.println(v);
		}
	}

}
