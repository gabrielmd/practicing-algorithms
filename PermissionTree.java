import java.util.*;

public class PermissionTree{

Map<String, Integer> mapUsers;
Map<Integer, int[]> mapFolders;
Map<Integer, List<Integer>> mapTree;

public int[] findHome(String[] folders, String[] users){

	mapUsers = new HashMap<String, Integer>();
	mapFolders = new HashMap<Integer, int[]>();
	mapTree = new HashMap<Integer, List<Integer>>();

	for(int i = 0; i < folders.length; i++){
		String [] info = folders[i].split(" ");

		// 	add each folder in hash
		int [] parentDepth = {Integer.parseInt(info[0]), 0};
		mapFolders.put(i, parentDepth);
		// tree nodes
		mapTree.put(i, new LinkedList<Integer>());
	}


	for(Integer node : mapFolders.keySet()){
		if(node != 0){
			// fill children
			mapTree.get(mapFolders.get(node)[0]).add(node);
		}
	}

	int root = 0;
	List<Integer> queue = new LinkedList<Integer>();
	List<Integer> depth = new LinkedList<Integer>();
	queue.add(root);
	depth.add(0);
	// BFS
	while(queue.size() > 0){
		Integer node = queue.remove(0);
		Integer depthNode = depth.remove(0);

		// fill info of this node
		mapFolders.get(node)[1] = depthNode;

		// add to queue
		for(Integer child : mapTree.get(node)){
			queue.add(child);
			depth.add(depthNode + 1);
		}
	}


	for(int i = 0; i < folders.length; i++){
		String [] info = folders[i].split(" ");

		String [] authors = info[1].split(",");
		for(int j = 0; j < authors.length; j++){
			// case 1: new name
			if(mapUsers.get(authors[j]) == null){
				mapUsers.put(authors[j], i);
			}else{
				int[] f1 = mapFolders.get(mapUsers.get(authors[j]));
				int[] f2 = mapFolders.get(i);

				int c1 = mapUsers.get(authors[j]);
				int c2 = i;

				while(c1 != c2){
					if(f2[1] > f1[1]){
						c2 = f2[0];
						f2 = mapFolders.get(f2[0]);
					}else{
						c1 = f1[0];
						f1 = mapFolders.get(f1[0]);
					}
				}

				mapUsers.put(authors[j], c1);
			}

		}
	}

	int [] response = new int[users.length];
	for(int j = 0; j < users.length; j++){
		if(mapUsers.get(users[j]) != null){
			response[j] = mapUsers.get(users[j]);
		}else{
			response[j] = -1;
		}
	}

	return response;
}

}