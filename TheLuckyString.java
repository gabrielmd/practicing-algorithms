import java.util.*;

public class TheLuckyString{

public static void switchMethod(char [] a, int i, int j){
	char x = a[i];
	a[i] = a[j];
	a[j] = x;
}

public static boolean lucky(char [] a){
	for(int i = 0; i < a.length - 1; i++){
		if(a[i] == a[i+1]){
			return false;
		}
	}

	// did not find consecutive chars
	return true;
}

public static void countRec(Set<String> set, char [] originalArray, String strTmp, int i){
	if(i == originalArray.length){
		//if(lucky(cArray)){
			//set.add(String.valueOf(cArray));
			set.add(strTmp);
		//}
		return;
	}

	for(int j = 0; j < originalArray.length; j++){
		if(originalArray[j] != '\0'){
			if(i == 0 || strTmp.charAt(i - 1) != originalArray[i]){
				char c = originalArray[j];
				String str2 = strTmp + c;
				originalArray[j] = '\0';
				countRec(set, originalArray, str2, i+1);
				originalArray[j] = c;
			}
		}
	}
}

public static int count(String s){

	Set <String> set = new HashSet<String>();

	countRec(set, s.toCharArray(), "", 0);

	return set.size();

}

public static void main(String [] args){
	System.out.println(count(args[0]));

}

}