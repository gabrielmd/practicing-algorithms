import java.util.*;
public class FryingHamburgersOld {
	public int howLong(int panSize, int hamburgers) {

		int total = 0;

		int firstSide = hamburgers;
		int secondSide = 0;

		while(firstSide + secondSide > 0){
			int thisTurn = 0;

			int cookingFirstSide = 0;

			thisTurn = Math.min(firstSide, panSize);
			firstSide -= thisTurn;
			cookingFirstSide = thisTurn;

			if(thisTurn < panSize){
				secondSide -= Math.min(secondSide, panSize - thisTurn);
			}

			secondSide += cookingFirstSide;
			total+=5;
		}

		return total;
	}
}


// Powered by FileEdit
// Powered by TZTester 1.01 [25-Feb-2003]
// Powered by CodeProcessor