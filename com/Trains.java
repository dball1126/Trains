import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Trains {

    
    public static void main(String[] args) throws Exception{
        // Read data file
        File file = new File("/Users/danielball/Desktop/Trains/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = "";
        String[] graph = new String[1]; 
        while ((st = br.readLine()) != null) graph[0] = st; // Read first line
        if (graph[0] == null) System.out.println("No input data"); //If first line is null print out not input
        
        String[] nodes = new String[graph[0].length()/2]; // Array of nodes with minimal memory allocation
        nodes = graph[0].split(","); // All nodes in an array

        for (int i = 0; i < nodes.length; i++) {
            
            System.out.println(nodes[i]);
        }

    }
}