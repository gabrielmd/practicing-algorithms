import java.lang.*;
public class FryingHamburgers{

	/*
		each hamburger: 5 min on each side
	*/
	public static int howLong(int panSize, int hamburgers){

		// basic: no hamburgers --> no time
		if(hamburgers == 0){
			return 0;
		}

		// case 1: all hamburgers fit --> time = 10 min
		if(panSize >= hamburgers){
			return 10;
		}

		// case 2: more than a half fit --> time = 15 min
		if((float)panSize >= (float)hamburgers / 2){
			return 15;
		}

		// case 3: less than a half fit --> time = ceil(2*hamburgers / panSize) * 5
		return (int)(Math.ceil((2F*hamburgers) / (float)panSize) * 5);
	}


}