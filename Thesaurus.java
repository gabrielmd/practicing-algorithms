import java.lang.*;
import java.util.*;

public class Thesaurus{


public static String[] edit(String[] entry){

	Map<Integer, List<String>> entryMap = new HashMap<Integer, List<String>>();
	ArrayList<String> synonym = new ArrayList<String>();

	// maintain an array with n values to show which of them are still "active"
	int n = entry.length;
	Integer [] active = new Integer[n];

	// read each entry -- put them in a hashmap with their respective synonyms
	int m = 0;
	for(int i = 0; i < entry.length; i++){

		// for each synonym, put in a hashmap with their indices -- they will be the "y" axis
		String [] words = entry[i].split(" ");

		entryMap.put(i, new LinkedList<String>());
		for(int j = 0; j < words.length; j++){
			entryMap.get(i).add(words[j]);
		}

		active[i] = i;

		for(int j = 0; j < words.length; j++){
			if(!synonym.contains(words[j])){
				synonym.add(words[j]);
				m++;
			}
		}
	}


	// declare a matrix n x m, where n = number of entries and m = number of synonyms

	boolean [][] ctrl = new boolean[n][m]; // define size
	for(int i = 0; i < n; i++){
		for(int j = 0; j < m; j++){
			ctrl[i][j] = false;
		}
	}

	// fill the matrix -- iterate the key set and put "1" in the synonyms
	for(int i = 0; i < n; i++){
		List<String> listSyn = entryMap.get(active[i]);

		for(String s : listSyn){
			ctrl[i][synonym.indexOf(s)] = true;
		}
	}

	// now, check every pair of columns to see if there is more than one synonym in common
	for(int i = 0; i < n - 1; i++){

		if(active[i] != null){

			for(int i2 = i+1; i2 < n; i2++){

				if(active[i2] != null){
					int sum = 0;

					for(int j = 0; j < m; j++){

						if(ctrl[i][j] && ctrl[i2][j]){
							sum++;

							if(sum == 2){
								break;
							}
						}
					}

					if(sum == 2){ //found 2!
						// merge all in the first (left) column
						for(int j = 0; j < m; j++){
							ctrl[i][j] |= ctrl[i2][j];
						}
						active[i2] = null;

						// restart i to 0
						i = -1;
						break;
					}
				}
			}
		}
	}

	int finalActive = 0;
	for(int i = 0; i < n; i++){
		if(active[i] != null){
			finalActive++;
		}
	}

	// generate the response array with final words only
	String [] response = new String[finalActive];
	int responseIndex = 0;
	for(int i = 0; i < n; i++){
		if(active[i] != null){
			List<String> listSyn = new LinkedList<String>();
			// listSyn.add(active[i]);
			for(int j = 0; j < m; j++){
				if(ctrl[i][j]){
					listSyn.add(synonym.get(j));
				}
			}

			Collections.sort(listSyn);

			response[responseIndex] = listSyn.get(0);
			for(int li = 1; li < listSyn.size(); li++){
				response[responseIndex] += " " + listSyn.get(li);
			}

			responseIndex++;
		}
	}

	Arrays.sort(response);

	return response;

}

public static void main(String [] args){
	String [] a = {"point run score","point dot","cut run tear score","cut valley","cute pretty"};
	String [] resp = edit(a);
	System.out.println(Arrays.toString(resp));

}

}