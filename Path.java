/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EphemeralDS;

import java.util.ArrayList;

/**
 *
 * @author Sya Fadz
 */
public class Path{
    static int curr;
    private int parent[];

    public Path(int given) {
        curr = given;
        parent = new int[given];
    }

    //to find shortest count of input count =(distance or time)
    public int countlow(ArrayList<Integer[]> graph, int root, int target){ 
        // The output array. count[target] will hold 
	// the lowest count from root to target
	int count[] = new int[curr];
        
	// flag[target] will true if target is included in lowest 
	// lowest count from root to target is finalized 
	Boolean flag[] = new Boolean[curr]; 

	// Initialize all count as MAX and flag[] as false 
	for (int i = 0; i < curr; i++) {
            parent[root] = -1;
            count[i] = Integer.MAX_VALUE; 
            flag[i] = false; 
	} 

	// count of root from itself will always be 0 
	count[root] = 0; 

	// Find lowest count for all 
	for (int temp = 0; temp < curr - 1; temp++) { 
            // Pick the minimum count from the set 
            // not yet processed. count is always equal to root in first 
            // iteration. 
            int x = minimum(count, flag); 

            // Mark the chosen as processed 
            flag[x] = true; 

            // Update count value of the adjacent vertices of the 
            // picked vertex. 
            for (int y = 0; y < curr; y++){
		// Update count[y] only if is not in flag, there is an 
		// edge from x to y, and total weight of path from root to 
		// y through x is smaller than current value of count[y] 
		if (!flag[y] && graph.get(x)[y] != 0 && count[x] != Integer.MAX_VALUE && count[x] + graph.get(x)[y] < count[y]){
                    parent[y] = x;
                    count[y] = count[x] + graph.get(x)[y]; 
                }
            } 
        } 
	if(count[target] == Integer.MAX_VALUE){
            return -1;
        }else{
            return count[target];
        }
    }
    
    public int minimum(int count[], Boolean flag[]){ 
	// Initialize min value 
	int min = Integer.MAX_VALUE, min_index = -1; 
	for (int i = 0; i < curr; i++) 
            if (flag[i] == false && count[i] <= min) { 
		min = count[i]; 
		min_index = i; 
		} 
	return min_index; 
    }
    
    public int getDistance(ArrayList<Integer[]> graph, int target){
        return addPath(graph,parent,target, 0);
    }
    
    public int getTime(ArrayList<Integer[]> graph, int target){
        return addPath(graph,parent,target, 0);
    }
    
    public int addPath(ArrayList<Integer[]> graph, int parent[], int j, int sum){
        if(parent[j] == -1){
            return sum;
        }
        sum = sum + graph.get(parent[j])[j];
        return addPath(graph, parent, parent[j], sum);
    }
}
