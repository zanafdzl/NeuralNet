/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EphemeralDS;

import java.util.Scanner;

/**
 *
 * @author Sya Fadz
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        UserInput A = new UserInput();
        System.out.print("Enter number of neurons: ");
        int neurons = s.nextInt();
        System.out.println("First line: (a m) where a is ID of the neuron and m is number of edges" +
                "\nNext lines: (A d t) where A is ID of destination neuron, d is distance of the edge"
                + " and t is time needed for message to pass through\n");
        A.input(neurons);   
    }
}
