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
public class Neuron {
    public int currentid, edge, goid, dist, time;
    ArrayList <Integer[]> in = new ArrayList<>();

    public Neuron(int currentid, int edge) {
        this.currentid = currentid;
        this.edge = edge;
    }

    public int getCurrentid() {
        return currentid;
    }

    
    public int getEdge() {
        return edge;
    }
    
    public void setthem(int ID, int goid, int dist, int time){
        in.add(new Integer[]{ID, goid, dist, time});
    }
    
    public Integer[] getEdge(int i){
        Integer[] input;
        input = in.get(i);
        return input;
    }
}
