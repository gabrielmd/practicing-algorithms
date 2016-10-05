public class AccessChanger{

public String[] convert(String[] program){

	int n = program.length;
	String [] response = new String[n];

	// for each line
	for(int i = 0; i < n; i++){

		String read = program[i];
		String newLine = "";

		// for each char
		for(int j=0; j < read.length() - 1; j++){

			// check if it is ->
			if(read.charAt(j) == '-' && read.charAt(j+1) == '>'){
				newLine += ".";
				j++;
			}else if(read.charAt(j) == '/' && read.charAt(j+1) == '/'){
				// check if it is //
				// if yes, ignore the rest of the line
				newLine += read.substring(j);
				j = read.length();
			}else if(j == read.length() - 2){
				// it is in the second last char
				newLine += read.substring(j);
			}else{
				newLine += read.charAt(j);
			}

		}

		response[i] = newLine;
	}

	// return
	return response;
}


}