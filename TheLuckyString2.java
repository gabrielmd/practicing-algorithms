import java.util.*;

public class TheLuckyString2{

	public static int count(String s){

		List<char []> list = new LinkedList<char []>();
		Set<String> set = new HashSet<String>();
		Set<String> setVisited = new HashSet<String>();

		list.add(s.toCharArray());
		setVisited.add(Arrays.toString(s.toCharArray()));

		int n = s.length();

		int count = 0;

		// check queue
		while(list.size() > 0){
			char[] str = list.remove(0);

			for(int i2 = 0; i2 < n-1; i2++){
				if(str[i2] == str[i2+1]){
					break;
				}else if(i2 == n-2){ // finished comparing
					// new lucky combination
					count++;
				}
			}

			for(int i = 0; i < n - 1; i++){
				for(int j = i+1; j < n; j++){

					char [] copy = Arrays.copyOf(str, n);

					// swap i and j
					char tmp = copy[i];
					copy[i] = copy[j];
					copy[j] = tmp;

					if(!setVisited.contains(Arrays.toString(copy))){
						list.add(copy);
						setVisited.add(Arrays.toString(copy));
					}
				}
			}
		}

		return count;
	}

	public static void main(String [] args){
		System.out.println(count(args[0]));
	}

}