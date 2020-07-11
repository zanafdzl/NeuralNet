/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EphemeralDS;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sya Fadz
 */
public class UserInput {
    public int nummsg, numneu;
    Scanner s = new Scanner(System.in);
    Neuron id[] = new Neuron[20];
    ArrayList <Integer[]> msgid = new ArrayList<>();
    ArrayList <Integer[]> dMatrix = new ArrayList<>();
    ArrayList <Integer[]> tMatrix = new ArrayList<>();

    public void input(int x){  
        numneu = x;
        for (int i=0; i<x; i++){
            //Initialize ArrayList all to 0
            dMatrix.add(new Integer[x]);
            tMatrix.add(new Integer[x]);
            for(int j = 0; j < x; j++){
                dMatrix.get(i)[j] = 0;
                tMatrix.get(i)[j] = 0;
            }  
        }
        //displaymatrix(); 
        //^ checking purposes only
        System.out.println("Unidirectional(U) or Bidirectional with Optimization(B)?");
        String choice = s.next();
        if(choice.equalsIgnoreCase("b")){
            for( int i = 0; i<x; i++){     
                System.out.println("\nEnter neuron " + (i+1) );        
                id[i+1] = new Neuron(s.nextInt(), s.nextInt());   
                opt(id[i+1].getCurrentid(), id[i+1].getEdge());
            }
            System.out.println("\nEnter number of messages to pass through: ");
            int msg = s.nextInt();
            message(msg);
        }
        else if(choice.equalsIgnoreCase("u")){
            for( int i = 0; i<x; i++){     
                System.out.println("\nEnter neuron " + (i+1) );        
                id[i+1] = new Neuron(s.nextInt(), s.nextInt());   
                uni(id[i+1].getCurrentid(), id[i+1].getEdge());
            }
            System.out.println("\nEnter number of messages to pass through: ");
            int msg = s.nextInt();
            message(msg);
        }
        else{
            System.out.println("Choice unavailable");
        }
    }
    
    public void uni(int ID, int edge){
        if(edge!= 0){
            for(int i=0; i<edge; i++){
                System.out.println("Enter edge " + (i+1));
                int target = s.nextInt();
                int d = s.nextInt();
                int t = s.nextInt();
                dMatrix.get(ID-1)[target-1] = d;
                tMatrix.get(ID-1)[target-1] = t;
                id[ID].setthem(ID, target, d , t);
            }
        }
    }
    
    public void opt(int ID, int edge){
        if(edge!= 0){
            for(int i=0; i<edge; i++){
                System.out.println("Enter edge " + (i+1));
                int target = s.nextInt();
                int d = s.nextInt();
                int t = s.nextInt();
                dMatrix.get(ID-1)[target-1] = d;
                dMatrix.get(target-1)[ID-1] = d;
                tMatrix.get(ID-1)[target-1] = t;
                tMatrix.get(target-1)[ID-1] = t;
                id[ID].setthem(ID, target, d , t);
            }
        }
    }

    public void message(int msg) {
        //displaymatrix(); 
        //^ checking purposes only
        System.out.println("Enter in form of (x y) where x is start and y is end.");
        nummsg = msg;
        for(int i=1; i<=msg; i++){
            msgid.add(new Integer[]{s.nextInt(), s.nextInt()});
        }
        getmessage();
    }
    
    public void getmessage(){
        System.out.println("Output in form of (x y) where x total distance and y total time:");
        boolean found;
        for(int x = 0; x<nummsg; x++){
            int origin = msgid.get(x)[0];
            int target = msgid.get(x)[1];
            
            Path t = new Path(numneu);
            Path d = new Path(numneu);
            int stime = t.countlow(tMatrix, origin-1, target-1);
            if(stime != -1){
                int dist = t.getDistance(dMatrix,target-1);
                int sdist = d.countlow(dMatrix, origin-1, target-1);
                int time = d.getTime(tMatrix, target-1);
                if(stime < time){
                    System.out.println(dist + " " + stime);
                }else{
                    System.out.println(sdist + " " + time);
                }
            }else{
                System.out.println("No path available.");
            }
        }
    }
    
    //display matrix of distance and time for checking
    public void displaymatrix(){
        System.out.println("Distance matrix");
        for(int i = 0; i<numneu; i++){
            for(int j = 0; j<numneu; j++){
                System.out.print(dMatrix.get(i)[j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nTime matrix");
        for(int i = 0; i<numneu; i++){
            for(int j = 0; j<numneu; j++){
                System.out.print(tMatrix.get(i)[j]+ " ");
            }
            System.out.println();
        }
    }
}
